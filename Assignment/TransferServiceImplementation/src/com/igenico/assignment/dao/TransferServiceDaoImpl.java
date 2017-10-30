package com.igenico.assignment.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.igenico.assignment.model.Account;
import com.igenico.assignment.model.ErrorMessages;
import com.igenico.assignment.model.TransferRequest;
import com.igenico.assignment.model.TransferServiceException;

/**
 * This class creates new account and transfers amounts from one account to
 * another.
 * 
 * @author Meenakshi
 * 
 */
public class TransferServiceDaoImpl implements TransferServiceDao {

	/**
	 * Instantiate the Logger
	 */
	private static final Log logger = LogFactory
			.getLog(TransferServiceDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.igenico.assignment.dao.TransferServiceDao#createAccount(com.igenico
	 * .assignment.model.Account)
	 */
	public boolean createAccount(Account account)
			throws TransferServiceException {

		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		int noOfRowsUpdated = 0;
		boolean isAccCreated = false;
		// check if account already exists or not

		Account accountDB = getAccountDetails(account.getAccountNumber());
		if (null != accountDB) {
			throw new TransferServiceException(
					ErrorMessages.ACCOUNT_ALREADY_PRESENT_EXCEPTION);
		}
		try {
			TransactionManager txnManager = new TransactionManager();
			dbConnection = txnManager.getConnection();

			if (null != dbConnection) {
				preparedStatement = dbConnection
						.prepareStatement(TransferServiceDaoConstants.CREATE_ACCOUNT);
				preparedStatement.setString(TransferServiceDaoConstants.ONE,
						account.getAccountNumber());
				preparedStatement.setInt(TransferServiceDaoConstants.TWO,
						account.getCustomerId());
				preparedStatement.setBigDecimal(
						TransferServiceDaoConstants.THREE,
						account.getOpeningBalance());
				preparedStatement.setBigDecimal(4, account.getOpeningBalance());

				noOfRowsUpdated = preparedStatement.executeUpdate();
				dbConnection.commit();

				if (noOfRowsUpdated >= 1) {
					isAccCreated = true;
				}
			}
		} catch (SQLException exception) {

			logger.debug(exception.getMessage(), exception);
			logger.error(exception.getMessage(), exception);

			throw new TransferServiceException(
					ErrorMessages.TECHNICAL_EXCEPTIONINSERVICE);

		} finally {
			try {
				preparedStatement.close();
				dbConnection.close();

			} catch (SQLException sqlexception) {
				logger.debug(sqlexception.getMessage(), sqlexception);
				logger.error(sqlexception.getMessage(), sqlexception);
			}

		}
		return isAccCreated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.igenico.assignment.dao.TransferServiceDao#transferBalance(com.igenico
	 * .assignment.model.TransferRequest)
	 */
	public boolean transferBalance(TransferRequest transferRequest)
			throws TransferServiceException {

		Account sourceAccountDetails;
		Account destinationAccountDetails;
		boolean isUpdated = false;
		boolean isUpdatedDestination = false;
		boolean isUpdatedSource = false;
		try {
			sourceAccountDetails = getAccountDetails(transferRequest
					.getSourceAccountNumber());
			destinationAccountDetails = getAccountDetails(transferRequest
					.getDestinationAccountNumber());
			BigDecimal totalBalSourceAcc = null;
			if (null != sourceAccountDetails && null != transferRequest) {
				totalBalSourceAcc = sourceAccountDetails.getTotalBalance()
						.subtract(transferRequest.getTransferBalance());
				if (totalBalSourceAcc.intValue() < 0) {
					throw new TransferServiceException(
							ErrorMessages.TOTALBAL_NEGATIVE_EXCEPTION);
				} else {
					sourceAccountDetails.setTotalBalance(totalBalSourceAcc);
				}
			}
			

			isUpdatedSource = updateAccountDetails(sourceAccountDetails);
			BigDecimal totalBalDestAcc = null;
			if (null != destinationAccountDetails && null != transferRequest) {
				totalBalDestAcc = destinationAccountDetails.getTotalBalance()
						.add(transferRequest.getTransferBalance());
				destinationAccountDetails.setTotalBalance(totalBalDestAcc);
				isUpdatedDestination = updateAccountDetails(destinationAccountDetails);
			}
			
		} catch (SQLException exception) {
			logger.debug(exception.getMessage(), exception);
			logger.error(exception.getMessage(), exception);

			throw new TransferServiceException(
					ErrorMessages.TECHNICAL_EXCEPTIONINSERVICE);

		} catch (ClassNotFoundException exception) {
			logger.debug(exception.getMessage(), exception);
			logger.error(exception.getMessage(), exception);

			throw new TransferServiceException(
					ErrorMessages.TECHNICAL_EXCEPTIONINSERVICE);

		}

		if (isUpdatedSource && isUpdatedDestination) {
			isUpdated = true;
		}
		return isUpdated;
	}

	/**
	 * This method updates account details of given account in input.
	 * 
	 * @param sourceAccountDetails
	 * @return true or false as per updation status
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws TransferServiceException
	 */
	private boolean updateAccountDetails(Account sourceAccountDetails)
			throws ClassNotFoundException, SQLException,
			TransferServiceException {

		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		int noOfRowsUpdated = 0;
		boolean isAccUpdated = false;
		try {

			TransactionManager txnManager = new TransactionManager();
			dbConnection = txnManager.getConnection();
			if (null != dbConnection) {
				preparedStatement = dbConnection
						.prepareStatement(TransferServiceDaoConstants.UPDATE_ACCOUNT);
				preparedStatement.setBigDecimal(
						TransferServiceDaoConstants.ONE,
						sourceAccountDetails.getTotalBalance());
				preparedStatement.setString(TransferServiceDaoConstants.TWO,
						sourceAccountDetails.getAccountNumber());

				noOfRowsUpdated = preparedStatement.executeUpdate();

				if (noOfRowsUpdated >= 1) {
					isAccUpdated = true;
				}
			}
		} catch (SQLException exception) {
			logger.debug(exception.getMessage(), exception);
			logger.error(exception.getMessage(), exception);

			throw new TransferServiceException(
					ErrorMessages.TECHNICAL_EXCEPTIONINSERVICE);

		} finally {
			preparedStatement.close();
			dbConnection.close();

		}

		return isAccUpdated;

	}

	/**
	 * This method retrieves account details of given input account number
	 * 
	 * @param accountNumber
	 * @return account details
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws TransferServiceException
	 */
	private Account getAccountDetails(String accountNumber)
			throws TransferServiceException {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = null;
		Account accountDetails = null;
		ResultSet rs = null;
		try {

			TransactionManager txnManager = new TransactionManager();
			dbConnection = txnManager.getConnection();

			if (null != dbConnection) {
				preparedStatement = dbConnection
						.prepareStatement(TransferServiceDaoConstants.SELECT_ACCOUNT);
				preparedStatement.setString(TransferServiceDaoConstants.ONE,
						accountNumber);
				rs = preparedStatement.executeQuery();

				if (rs.next()) {
					// Retrieve by column name

					accountDetails = new Account();
					accountDetails.setAccountNumber(rs
							.getString(TransferServiceDaoConstants.ACCNUMBER));
					accountDetails.setCustomerId(rs
							.getInt(TransferServiceDaoConstants.CUSTOMERID));
					accountDetails
							.setOpeningBalance(rs
									.getBigDecimal(TransferServiceDaoConstants.OPENINIGBALANCE));
					accountDetails
							.setTotalBalance(rs
									.getBigDecimal(TransferServiceDaoConstants.TOTALBALANCE));

				}
			}
		} catch (SQLException exception) {
			logger.debug(exception.getMessage(), exception);
			logger.error(exception.getMessage(), exception);
			throw new TransferServiceException(
					ErrorMessages.TECHNICAL_EXCEPTIONINSERVICE);

		} finally {
			if (rs != null) {
				try {
					rs.close();
					preparedStatement.close();
					dbConnection.close();

				} catch (SQLException sqlexception) {
					logger.debug(sqlexception.getMessage(), sqlexception);
					logger.error(sqlexception.getMessage(), sqlexception);
				}

			}

		}

		return accountDetails;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.igenico.assignment.dao.TransferServiceDao#getBalance(java.lang.String
	 * )
	 */
	public Account getBalance(String accountNumber)
			throws TransferServiceException {

		return getAccountDetails(accountNumber);

	}
}

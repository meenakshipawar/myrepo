package com.igenico.assignment.service;

import com.igenico.assignment.dao.TransferServiceDao;
import com.igenico.assignment.dao.TransferServiceDaoImpl;
import com.igenico.assignment.model.Account;
import com.igenico.assignment.model.TransferRequest;
import com.igenico.assignment.model.TransferServiceException;

public class AccountAdministrationService {

	/**
	 * This method creates new account
	 * 
	 * @param account
	 * @return true/false
	 * @throws TransferServiceException
	 */
	public boolean createAccount(Account account)
			throws TransferServiceException {
		boolean isAccCreated = false;

		if (null != account) {
			validateDataACCNumber(account.getAccountNumber());
			TransferServiceDao transferServiceDao = new TransferServiceDaoImpl();

			isAccCreated = transferServiceDao.createAccount(account);
		}
		return isAccCreated;

	}

	/**
	 * This method validates account number
	 * @param accountNumber
	 * @throws TransferServiceException
	 */
	private void validateDataACCNumber(String accountNumber)
			throws TransferServiceException {

		if ((accountNumber.length()==0) || accountNumber.length() > 18) {
			throw new TransferServiceException(
					TransferServiceErrorMessages.INVALID_ACC_NUMBER);
		}
	}

	/**
	 * This method transfers balance from source account to destination account
	 * @param transferRequest
	 * @return true/false
	 * @throws TransferServiceException
	 */
	public boolean transferBalance(TransferRequest transferRequest)
			throws TransferServiceException {
		boolean isAccUpdated = false;
		if (null != transferRequest) {
			validateDataACCNumber(transferRequest.getSourceAccountNumber());
			validateDataACCNumber(transferRequest.getDestinationAccountNumber());

			TransferServiceDao transferServiceDao = new TransferServiceDaoImpl();

			isAccUpdated = transferServiceDao.transferBalance(transferRequest);
		}
		return isAccUpdated;

	}

	/**
	 * This method retrieves account details for account number
	 * @param accountNumber
	 * @return Acount details
	 * @throws TransferServiceException
	 */
	public Account getBalance(String accountNumber)
			throws TransferServiceException {
		validateDataACCNumber(accountNumber);

		TransferServiceDao transferServiceDao = new TransferServiceDaoImpl();
		return transferServiceDao.getBalance(accountNumber);
		
	}

}

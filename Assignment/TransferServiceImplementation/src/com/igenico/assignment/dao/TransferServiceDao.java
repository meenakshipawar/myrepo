package com.igenico.assignment.dao;

import com.igenico.assignment.model.Account;
import com.igenico.assignment.model.TransferRequest;
import com.igenico.assignment.model.TransferServiceException;

/**
 * Interface class for TransferService.
 * @author Meenakshi
 *
 */
public interface TransferServiceDao {
	
	/**
	 * This method creates accounts with input accound details
	 * @param account
	 * @return true/false
	 * @throws TransferServiceException
	 */
	public boolean createAccount(Account account) throws TransferServiceException;
	
	/**
	 * This method transfers balance from one account to other
	 * @param transferRequest
	 * @return true/false
	 * @throws TransferServiceException
	 */
	public boolean transferBalance(TransferRequest transferRequest) throws TransferServiceException;

	/**
	 * This method returns account details of given accountnumber
	 * @param accountNumber
	 * @return account details
	 * @throws TransferServiceException
	 */
	public Account getBalance(String accountNumber)throws TransferServiceException;
		
}

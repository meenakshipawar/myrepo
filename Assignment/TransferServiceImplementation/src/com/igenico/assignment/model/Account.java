package com.igenico.assignment.model;

import java.math.BigDecimal;


/**
 * This class contains Accounts basic information.
 * 
 * @author Meenakshi
 *
 */
public class Account {
	
	String accountNumber;
	
	int customerId;
	
	BigDecimal openingBalance;
	
	BigDecimal totalBalance;

	public BigDecimal getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}

	public BigDecimal getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	

	
}

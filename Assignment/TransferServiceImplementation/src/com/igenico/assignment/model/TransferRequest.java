package com.igenico.assignment.model;

import java.math.BigDecimal;

public class TransferRequest {

	String sourceAccountNumber;
	
	String destinationAccountNumber;
	
	BigDecimal transferBalance;

	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public String getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(String destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}

	public BigDecimal getTransferBalance() {
		return transferBalance;
	}

	public void setTransferBalance(BigDecimal transferBalance) {
		this.transferBalance = transferBalance;
	}	
	
	
}

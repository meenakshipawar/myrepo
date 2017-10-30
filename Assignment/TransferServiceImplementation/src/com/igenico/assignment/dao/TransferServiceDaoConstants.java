package com.igenico.assignment.dao;


public class TransferServiceDaoConstants {

	
   public static final String INSERT_STATEMENT="INSERT INTO ";
	
	public static final String OPEN_BRACES=" ( ";
	
	public static final String COMMA=" , ";
	
	public static final String CLOSE_BRACES=" ) ";
	
	public static final String ACCOUNTS= " ACCOUNTADMIN.ACCOUNTS ";
			
	public static final String ACCNUMBER ="accountNumber";	
	public static final String CUSTOMERID ="customerId";	
	public static final String OPENINIGBALANCE ="openingbalance";	
	public static final String TOTALBALANCE ="totalBalance";	
	
	public static final String CREATE_ACCOUNT = INSERT_STATEMENT
			 + ACCOUNTS + " VALUES (?,?,?,?)";

	public static final int ONE = 1;

	public static final int TWO = 2;

	public static final int THREE = 3;
	
	
	public static final String SELECT_ACCOUNT = "SELECT accountNumber,customerId,openingbalance,totalBalance FROM "
			 + ACCOUNTS + " WHERE accountNumber = ?";

	public static final String UPDATE_ACCOUNT = "Update "+ACCOUNTS+ "set totalBalance = ? where accountNumber=? ";

	
}

package com.igenico.assignment.model;


/**
 * The TransferServiceException class extends Exception class.
 *
 * @author Meenakshi
 * @version 1.0
 * @since 2017-05-31
 */
public class TransferServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2495633334119853615L;
	/**
	 * Association to the messages component that will handle error, warning and
	 * info messages.
	 */
	private String messages;

	/**
	 * getMessages - standard java bean getter/setter
	 * 
	 * @return
	 */
	public String getMessages() {
		return messages;
	}

	// ==================== Constructors ====================

	/**
	 * Default constructor
	 */
	protected TransferServiceException() {
		super();
		
	}

	/**
	 * Constructor that will also set messages on the exception
	 * 
	 * @param inMessages
	 */
	public TransferServiceException(String inMessages) {
		super();
		// Set messages
		messages = inMessages;
	}

	/**
	 * Constructor that takes an existing APIException. This will move any
	 * messages into the new exception.
	 * 
	 * @param e
	 */
	protected TransferServiceException(TransferServiceException e) {
		super();
		// Copy over messages
		if (e != null) {
			messages = e.getMessages();
		} else {
			messages = "";
		}
	}


}

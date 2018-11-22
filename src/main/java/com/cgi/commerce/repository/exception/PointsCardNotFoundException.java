package com.cgi.commerce.repository.exception;

import atg.repository.RepositoryException;

public class PointsCardNotFoundException extends RepositoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7248489265445057713L;

	private final String cardNumber;

	public PointsCardNotFoundException(String cardNumber) {
		this(String.format("No points card with number %s was found.", cardNumber),
				cardNumber);
	}

	public PointsCardNotFoundException(String message, String cardNumber) {
		super(message);
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

}

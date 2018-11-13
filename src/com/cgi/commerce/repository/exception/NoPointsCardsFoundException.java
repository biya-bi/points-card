package com.cgi.commerce.repository.exception;

import java.util.Arrays;

import atg.repository.RepositoryException;

public class NoPointsCardsFoundException extends RepositoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7329548070118527924L;

	private final String[] cardNumbers;

	public NoPointsCardsFoundException(String[] cardNumbers) {
		this(String.format("No points card with number in the range %s was found.",
				Arrays.toString(cardNumbers)), cardNumbers);
	}

	public NoPointsCardsFoundException(String message, String[] cardNumbers) {
		super(message);
		this.cardNumbers = cardNumbers;
	}

	public String[] getCardNumbers() {
		return cardNumbers;
	}

}

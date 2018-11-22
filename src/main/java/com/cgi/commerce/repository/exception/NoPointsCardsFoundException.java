package com.cgi.commerce.repository.exception;

import java.util.ArrayList;
import java.util.List;

import atg.repository.RepositoryException;

public class NoPointsCardsFoundException extends RepositoryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7329548070118527924L;

	private final List<String> cardNumbers;

	public NoPointsCardsFoundException(List<String> cardNumbers) {
		this(String.format("No points card with number in the range %s was found.", cardNumbers), cardNumbers);
	}

	public NoPointsCardsFoundException(String message, List<String> cardNumbers) {
		super(message);
		this.cardNumbers = cardNumbers;
	}

	public List<String> getCardNumbers() {
		return new ArrayList<>(cardNumbers);
	}

}

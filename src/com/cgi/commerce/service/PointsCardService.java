package com.cgi.commerce.service;

import atg.repository.Query;
import atg.repository.QueryBuilder;
import atg.repository.QueryExpression;
import atg.repository.Repository;
import atg.repository.RepositoryException;
import atg.repository.RepositoryItem;
import atg.repository.RepositoryView;
import atg.service.actor.ActorChainService;

import com.cgi.commerce.dto.PointsCardDto;
import com.cgi.commerce.repository.exception.NoPointsCardsFoundException;
import com.cgi.commerce.repository.exception.PointsCardNotFoundException;

public class PointsCardService extends ActorChainService {
	private Repository pointsCardRepository;

	public Repository getPointsCardRepository() {
		return pointsCardRepository;
	}

	public void setPointsCardRepository(Repository pointsCardRepository) {
		this.pointsCardRepository = pointsCardRepository;
	}

	public Integer getPoints(String cardNumber) throws RepositoryException {
		RepositoryView view = pointsCardRepository.getView("pointsCard");
		QueryBuilder queryBuilder = view.getQueryBuilder();
		QueryExpression cardNumberQueryExpr = queryBuilder
				.createPropertyQueryExpression("number");
		QueryExpression cardNumberValueExpr = queryBuilder
				.createConstantQueryExpression(cardNumber);
		Query query = queryBuilder.createComparisonQuery(cardNumberQueryExpr,
				cardNumberValueExpr, QueryBuilder.EQUALS);
		RepositoryItem[] items = view.executeQuery(query);
		if (items == null || items.length == 0)
			throw new PointsCardNotFoundException(cardNumber);
		return (Integer) items[0].getPropertyValue("points");
	}

	public PointsCardDto[] getPoints(String[] cardNumbers) throws RepositoryException {
		RepositoryView view = pointsCardRepository.getView("pointsCard");
		QueryBuilder queryBuilder = view.getQueryBuilder();
		QueryExpression cardNumberQueryExpr = queryBuilder
				.createPropertyQueryExpression("number");
		QueryExpression cardNumberValueExpr = queryBuilder
				.createConstantQueryExpression(cardNumbers);
		Query query = queryBuilder.createIncludesQuery(cardNumberValueExpr,
				cardNumberQueryExpr);
		RepositoryItem[] items = view.executeQuery(query);
		if (items == null || items.length == 0)
			throw new NoPointsCardsFoundException(cardNumbers);
		PointsCardDto[] dtos = new PointsCardDto[items.length];
		for (int i = 0; i < items.length; i++) {
			dtos[i] = new PointsCardDto(
					(String) items[0].getPropertyValue("number"),
					(Integer) items[0].getPropertyValue("points"));
		}
		return dtos;
	}
}

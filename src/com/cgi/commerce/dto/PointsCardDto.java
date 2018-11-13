package com.cgi.commerce.dto;

public class PointsCardDto {
	private String number;
	private Integer points;

	public PointsCardDto() {
		super();
	}

	public PointsCardDto(String number, Integer points) {
		super();
		this.number = number;
		this.points = points;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

}

package com.heb.groceries.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {

	private long		id;
	private String		description;
	private LocalDate	lastSold;
	private int			shelfLifeDays;
	private String		department;
	private BigDecimal	price;
	private String		unit;
	private int			xFor;
	private BigDecimal	cost;

	public Product() {

	}

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public LocalDate getLastSold() {
		return this.lastSold;
	}

	public void setLastSold(final LocalDate lastSold) {
		this.lastSold = lastSold;
	}

	public int getShelfLifeDays() {
		return this.shelfLifeDays;
	}

	public void setShelfLifeDays(final int numberOfDays) {
		this.shelfLifeDays = numberOfDays;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(final String department) {
		this.department = department;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(final String unit) {
		this.unit = unit;
	}

	public int getXFor() {
		return this.xFor;
	}

	public void setXFor(final int xFor) {
		this.xFor = xFor;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

}

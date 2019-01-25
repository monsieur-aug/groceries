package com.heb.groceries.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;

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
		if (id <= 0) {
			throw new IllegalArgumentException("");
		}

		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		if (StringUtils.isBlank(description)) {
			throw new IllegalArgumentException();
		}

		if (!StringUtils.isAlphaSpace(description)) {
			throw new IllegalArgumentException();
		}

		this.description = StringUtils.normalizeSpace(description);
	}

	public LocalDate getLastSold() {
		return this.lastSold;
	}

	public void setLastSold(final LocalDate lastSold) {
		if (lastSold == null) {
			throw new IllegalArgumentException();
		}

		this.lastSold = lastSold;
	}

	public int getShelfLifeDays() {
		return this.shelfLifeDays;
	}

	public void setShelfLifeDays(final int numberOfDays) {
		if (numberOfDays < 0) {
			throw new IllegalArgumentException();
		}

		this.shelfLifeDays = numberOfDays;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(final String department) {
		if (!StringUtils.isAlpha(department)) {
			throw new IllegalArgumentException();
		}

		this.department = StringUtils.normalizeSpace(department);
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(final BigDecimal price) {
		if (price == null) {
			throw new IllegalArgumentException();
		}

		if (price.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException();
		}

		this.price = price;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(final String unit) {
		if (!StringUtils.isAlpha(unit)) {
			throw new IllegalArgumentException();
		}

		this.unit = StringUtils.normalizeSpace(unit);
	}

	public int getXFor() {
		return this.xFor;
	}

	public void setXFor(final int xFor) {
		if (xFor < 0) {
			throw new IllegalArgumentException();
		}

		this.xFor = xFor;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		if (cost == null) {
			throw new IllegalArgumentException();
		}

		if (cost.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException();
		}

		this.cost = cost;
	}

}

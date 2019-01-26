package com.heb.groceries.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;

import com.heb.groceries.error.Error;

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
			throw new IllegalArgumentException(Error.PRODUCT_ID_NEGATIVE_OR_ZERO.toString());
		}

		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		if (StringUtils.isBlank(description)) {
			throw new IllegalArgumentException(Error.PRODUCT_DESCRIPTION_NULL_BLANK_EMPTY.toString());
		}

		if (!StringUtils.isAlphaSpace(description)) {
			throw new IllegalArgumentException(Error.PRODUCT_DESCRIPTION_NOT_ALPHA_SPACE.toString());
		}

		this.description = StringUtils.normalizeSpace(description);
	}

	public LocalDate getLastSold() {
		return this.lastSold;
	}

	public void setLastSold(final LocalDate lastSold) {
		if (lastSold == null) {
			throw new IllegalArgumentException(Error.PRODUCT_LAST_SOLD_DATE_NULL.toString());
		}

		this.lastSold = lastSold;
	}

	public int getShelfLifeDays() {
		return this.shelfLifeDays;
	}

	public void setShelfLifeDays(final int numberOfDays) {
		if (numberOfDays <= 0) {
			throw new IllegalArgumentException(Error.PRODUCT_SHELF_LIFE_NEGATIVE_OR_ZERO.toString());
		}

		this.shelfLifeDays = numberOfDays;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(final String department) {
		if (StringUtils.isBlank(department)) {
			throw new IllegalArgumentException(Error.PRODUCT_DEPARTMENT_NULL_BLANK_EMPTY.toString());
		}

		if (!StringUtils.isAlphaSpace(department)) {
			throw new IllegalArgumentException(Error.PRODUCT_DEPARTMENT_NOT_ALPHA_SPACE.toString());
		}

		this.department = StringUtils.normalizeSpace(department);
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(final BigDecimal price) {
		if (price == null) {
			throw new IllegalArgumentException(Error.PRODUCT_PRICE_NULL.toString());
		}

		if (price.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException(Error.PRODUCT_PRICE_NEGATIVE.toString());
		}

		this.price = price;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(final String unit) {
		if (StringUtils.isBlank(unit)) {
			throw new IllegalArgumentException(Error.PRODUCT_UNIT_NULL_BLANK_EMPTY.toString());
		}

		if (!StringUtils.isAlphaSpace(unit)) {
			throw new IllegalArgumentException(Error.PRODUCT_UNIT_NOT_ALPHA_SPACE.toString());
		}

		this.unit = StringUtils.normalizeSpace(unit);
	}

	public int getXFor() {
		return this.xFor;
	}

	public void setXFor(final int xFor) {
		if (xFor < 0) {
			throw new IllegalArgumentException(Error.PRODUCT_XFOR_NEGATIVE.toString());
		}

		this.xFor = xFor;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		if (cost == null) {
			throw new IllegalArgumentException(Error.PRODUCT_COST_NULL.toString());
		}

		if (cost.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException(Error.PRODUCT_COST_NEGATIVE.toString());
		}

		this.cost = cost;
	}

}

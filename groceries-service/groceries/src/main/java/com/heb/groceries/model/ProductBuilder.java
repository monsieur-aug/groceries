package com.heb.groceries.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.heb.groceries.error.Error;

public class ProductBuilder {

	private Product product;

	public ProductBuilder() {
		this.product = new Product();
	}

	public Product build() {
		if (idIsSet() && descriptionIsSet() && lastSoldIsSet() && shelfLifeDaysIsSet() && departmentIsSet() && priceIsSet() && unitIsSet() && xForIsSet()
						&& costIsSet()) {
			return this.product;
		}

		throw new IllegalStateException(Error.PRODUCT_BUILDER_INCOMPLETE.toString());
	}

	public ProductBuilder id(final Long id) {
		this.product.setId(id);

		return this;
	}

	public ProductBuilder description(final String description) {
		this.product.setDescription(description);

		return this;
	}

	public ProductBuilder lastSold(final LocalDate lastSold) {
		this.product.setLastSold(lastSold);

		return this;
	}

	public ProductBuilder shelfLifeDays(final Integer numberOfDays) {
		this.product.setShelfLifeDays(numberOfDays);

		return this;
	}

	public ProductBuilder department(final String department) {
		this.product.setDepartment(department);

		return this;
	}

	public ProductBuilder price(final BigDecimal price) {
		this.product.setPrice(price);

		return this;
	}

	public ProductBuilder unit(final String unit) {
		this.product.setUnit(unit);

		return this;
	}

	public ProductBuilder xFor(final int xFor) {
		this.product.setXFor(xFor);

		return this;
	}

	public ProductBuilder cost(final BigDecimal cost) {
		this.product.setCost(cost);

		return this;
	}

	private boolean idIsSet() {
		/*
		 * The ID value is considered set if the current ID value passed the input
		 * validation of the ID setter. If it didn't pass validation, then it wasn't
		 * set.
		 */
		try {
			this.product.setId(this.product.getId());
		} catch (IllegalArgumentException iae) {
			return false;
		}

		return true;
	}

	private boolean descriptionIsSet() {
		return isSet(this.product.getDescription());
	}

	private boolean lastSoldIsSet() {
		return isSet(this.product.getLastSold());
	}

	private boolean shelfLifeDaysIsSet() {
		/*
		 * The shelf life days value is considered set if the current shelf life days
		 * value passed the input validation of the shelf life days setter. If it didn't
		 * pass validation, then it wasn't set.
		 */
		try {
			this.product.setShelfLifeDays(this.product.getShelfLifeDays());
		} catch (IllegalArgumentException iae) {
			return false;
		}

		return true;
	}

	private boolean departmentIsSet() {
		return isSet(this.product.getDepartment());
	}

	private boolean priceIsSet() {
		return isSet(this.product.getPrice());
	}

	private boolean unitIsSet() {
		return isSet(this.product.getUnit());
	}

	private boolean xForIsSet() {
		/*
		 * The xFor value is considered set if the current xFor value passed the input
		 * validation of the xFor setter. If it didn't pass validation, then it wasn't
		 * set.
		 */
		try {
			this.product.setXFor(this.product.getXFor());
		} catch (IllegalArgumentException iae) {
			return false;
		}

		return true;
	}

	private boolean costIsSet() {
		return isSet(this.product.getCost());
	}

	private boolean isSet(Object object) {
		return object != null;
	}

}

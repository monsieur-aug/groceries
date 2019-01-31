package com.heb.groceries.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.heb.groceries.error.Error;

/**
 * Builds a <code>Product</code> object and enforces the presence of all
 * mandatory fields.
 */
public class ProductBuilder {

	private Product product;

	public ProductBuilder() {
		this.product = new Product();
	}

	/**
	 * Builds the product. Throws an <cod>IllegalStateException</code> if one or
	 * more mandatory attributes are not set.
	 * 
	 * @return a <code>Product</code> instance with the specified attributes
	 */
	public Product build() {
		if (isSet(this.product.getId()) && isSet(this.product.getDescription()) && isSet(this.product.getLastSold()) && isSet(this.product.getShelfLifeDays())
						&& isSet(this.product.getDepartment()) && isSet(this.product.getPrice()) && isSet(this.product.getUnit())
						&& isSet(this.product.getXFor()) && isSet(this.product.getCost())) {
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

	public ProductBuilder xFor(final Integer xFor) {
		this.product.setXFor(xFor);

		return this;
	}

	public ProductBuilder cost(final BigDecimal cost) {
		this.product.setCost(cost);

		return this;
	}

	private boolean isSet(Object object) {
		return object != null;
	}

}

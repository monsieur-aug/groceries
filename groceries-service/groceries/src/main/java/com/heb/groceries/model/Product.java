package com.heb.groceries.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.heb.groceries.error.Error;

/**
 * Represents a product in the inventory.
 */
public class Product implements Serializable {

	private static final long	serialVersionUID	= 709110542198236024L;

	private Long				id;
	private String				description;
	private LocalDate			lastSold;
	private Integer				shelfLifeDays;
	private String				department;
	private BigDecimal			price;
	private String				unit;
	private Integer				xFor;
	private BigDecimal			cost;

	public Product() {

	}

	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the product id. Throws an <code>IllegalArgumentException</code> if the
	 * id is null, negative or zero.
	 * 
	 * @param id the product id
	 */
	public void setId(final Long id) {
		if (id == null) {
			throw new IllegalArgumentException(Error.PRODUCT_ID_NULL.toString());
		}

		if (id <= 0L) {
			throw new IllegalArgumentException(Error.PRODUCT_ID_NEGATIVE_OR_ZERO.toString());
		}

		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the product description. Throws an <code>IllegalArgumentException</code>
	 * if the description is null; blank; empty; or contains characters other than
	 * letters and spaces.
	 * 
	 * @param description the product description
	 */
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

	/**
	 * Sets the product's last sold date. Throws an
	 * <code>IllegalArgumentException</code> if the date is null.
	 * 
	 * @param lastSold the date the product was last sold
	 */
	public void setLastSold(final LocalDate lastSold) {
		if (lastSold == null) {
			throw new IllegalArgumentException(Error.PRODUCT_LAST_SOLD_DATE_NULL.toString());
		}

		this.lastSold = lastSold;
	}

	public Integer getShelfLifeDays() {
		return this.shelfLifeDays;
	}

	/**
	 * Sets the product's shelf life, represented by a number of days. Throws an
	 * <code>IllegalArgumentException</code> if the number of days is null,
	 * negative, or zero.
	 * 
	 * @param numberOfDays the product's shelf life expressed as a whole number of
	 *                     days
	 */
	public void setShelfLifeDays(final Integer numberOfDays) {
		if (numberOfDays == null) {
			throw new IllegalArgumentException(Error.PRODUCT_SHELF_LIFE_NULL.toString());
		}

		if (numberOfDays <= 0) {
			throw new IllegalArgumentException(Error.PRODUCT_SHELF_LIFE_NEGATIVE_OR_ZERO.toString());
		}

		this.shelfLifeDays = numberOfDays;
	}

	public String getDepartment() {
		return this.department;
	}

	/**
	 * Sets the product department. Throws an <code>IllegalArgumentException</code>
	 * if the department is null; blank; empty; or contains characters other than
	 * letters and spaces.
	 * 
	 * @param department the product's department
	 */
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

	/**
	 * Sets the product's price. Throw's an <code>IllegalArgumentException</code> if
	 * the price is null or negative.
	 * 
	 * @param price the product's price
	 */
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

	/**
	 * Sets the product unit (e.g., "lb" to represent pounds or "Each" to represent
	 * individual units). Throws an <code>IllegalArgumentException</code> if the
	 * unit is null; blank; empty; or contains characters other than letters and
	 * spaces.
	 * 
	 * @param unit the product's unit
	 */
	public void setUnit(final String unit) {
		if (StringUtils.isBlank(unit)) {
			throw new IllegalArgumentException(Error.PRODUCT_UNIT_NULL_BLANK_EMPTY.toString());
		}

		if (!StringUtils.isAlphaSpace(unit)) {
			throw new IllegalArgumentException(Error.PRODUCT_UNIT_NOT_ALPHA_SPACE.toString());
		}

		this.unit = StringUtils.normalizeSpace(unit);
	}

	public Integer getXFor() {
		return this.xFor;
	}

	/**
	 * Sets the product's xFor value. Throws an
	 * <code>IllegalArgumentException</code> if the xFor is null, negative or zero.
	 * 
	 * @param xFor the product's xfor factor value
	 */
	public void setXFor(final Integer xFor) {
		if (xFor == null) {
			throw new IllegalArgumentException(Error.PRODUCT_XFOR_NULL.toString());
		}

		if (xFor <= 0) {
			throw new IllegalArgumentException(Error.PRODUCT_XFOR_NEGATIVE_OR_ZERO.toString());
		}

		this.xFor = xFor;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * Sets the product's cost. Throws and <code>IllegalArgumentException</code> if
	 * the cost is null or negative.
	 * 
	 * @param cost the product's cost
	 */
	public void setCost(final BigDecimal cost) {
		if (cost == null) {
			throw new IllegalArgumentException(Error.PRODUCT_COST_NULL.toString());
		}

		if (cost.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException(Error.PRODUCT_COST_NEGATIVE.toString());
		}

		this.cost = cost;
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}

		if (other == null) {
			return false;
		}

		if (this.getClass() != other.getClass()) {
			return false;
		}

		final Product otherProduct = (Product) other;

		return id.equals(otherProduct.id) && description.equals(otherProduct.description) && lastSold.equals(otherProduct.lastSold)
						&& shelfLifeDays.equals(otherProduct.shelfLifeDays) && department.contentEquals(otherProduct.department)
						&& price.equals(otherProduct.price) && unit.equals(otherProduct.unit) && xFor.equals(otherProduct.xFor)
						&& cost.equals(otherProduct.cost);
	}

	@Override
	public int hashCode() {
		return 7 * Long.hashCode(id) + 11 * Objects.hashCode(description) + 13 * Objects.hashCode(lastSold) + 17 * Integer.hashCode(shelfLifeDays)
						+ 19 * Objects.hashCode(department) + 23 * Objects.hashCode(price) + 29 * Objects.hashCode(unit) + 31 * Integer.hashCode(xFor)
						+ 37 * Objects.hashCode(cost);
	}

}

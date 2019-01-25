package com.heb.groceries.error;

public enum Error {
	PRODUCT_ID_NEGATIVE_OR_ZERO("The product ID must be greater than zero."),
	PRODUCT_DESCRIPTION_NULL_BLANK_EMPTY("The product description may not be null, blank, or empty."),
	PRODUCT_DESCRIPTION_NOT_ALPHA_SPACE("The product description may only contain letters and spaces."),
	PRODUCT_LAST_SOLD_DATE_NULL("The last sold date may not be null."),
	PRODUCT_SHELF_LIFE_NEGATIVE("The shelf life must be a non-negative number of days."),
	PRODUCT_DEPARTMENT_NULL_BLANK_EMPTY("The product department may not be null, blank, or empty."),
	PRODUCT_DEPARTMENT_NOT_ALPHA_SPACE("The product department may only contain letters and spaces."),
	PRODUCT_PRICE_NULL("The product price may not be null."),
	PRODUCT_PRICE_NEGATIVE("The product price may not be negative."),
	PRODUCT_UNIT_NULL_BLANK_EMPTY("The product unit may not be null, blank, or empty."),
	PRODUCT_UNIT_NOT_ALPHA_SPACE("The product unit may only contain letters and spaces."),
	PRODUCT_XFOR_NEGATIVE("The product xFor must be a non-negative number of units."),
	PRODUCT_COST_NULL("The product cost may not be null."),
	PRODUCT_COST_NEGATIVE("The product cost may not be negative."),
	;

	private final String message;

	private Error(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return getMessage();
	}
}

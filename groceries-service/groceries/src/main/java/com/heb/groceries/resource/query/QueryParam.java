package com.heb.groceries.resource.query;

public enum QueryParam {
	DESCRIPTION("descriptionContains"),
	DEPARTMENT("departmentContains"),
	SHELF_LIFE_DAYS_MIN("shelfLifeMin"),
	SHELF_LIFE_DAYS_MAX("shelfLifeMax");

	private final String key;

	private QueryParam(final String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

	@Override
	public String toString() {
		return getKey();
	}
}

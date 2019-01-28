package com.heb.groceries.resource.query;

import java.util.ArrayList;
import java.util.List;

public enum QueryParam {
	DESCRIPTION("descriptionContains"),
	DEPARTMENT("department"),
	SHELF_LIFE_DAYS_MIN("shelfLifeMin"),
	SHELF_LIFE_DAYS_MAX("shelfLifeMax"),
	UNIT("unit"),
	XFOR_MIN("xForMin"),
	XFOR_MAX("xForMax"),
	PRICE_MIN("priceMin"),
	PRICE_MAX("priceMax"),
	COST_MIN("costMin"),
	COST_MAX("costMax"),
	LAST_SOLD_DATE_START("lastSoldDateFrom"),
	LAST_SOLD_DATE_END("lastSoldDateUntil")
	;

	public static List<String>	ALL_PARAMS	= new ArrayList<String>();

	private final String		key;

	static {
		for (final QueryParam param : values()) {
			ALL_PARAMS.add(param.getKey());
		}
	}
	
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

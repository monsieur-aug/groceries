package com.heb.groceries.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

public class ProductBuilderTest {

	private final long			validId				= 1L;
	private final String		validDescription	= "Tasty Cakes";
	private final LocalDate		validLastSold		= LocalDate.of(1970, 1, 1);
	private final String		validDepartment		= "Produce";
	private final BigDecimal	validPrice			= new BigDecimal("5.99");
	private final String		validUnit			= "Each";
	private final int			validXFor			= 1;
	private final BigDecimal	validCost			= new BigDecimal("1.20");

	@Test
	public void testProductBuilderBuildsProductGivenValidValues() {
		final ProductBuilder testBuilder = new ProductBuilder();

		testBuilder.id(this.validId).description(this.validDescription).lastSold(this.validLastSold).department(this.validDepartment).price(this.validPrice)
						.unit(this.validUnit).xFor(this.validXFor).cost(this.validCost);

		final Product testProduct = testBuilder.build();

		assertEquals("The id should be the same as the one specified", this.validId, testProduct.getId());
		assertEquals("The description should be the same as the one specified", this.validDescription, testProduct.getDescription());
		assertEquals("The last sold date should be the same as the one specified", this.validLastSold, testProduct.getLastSold());
		assertEquals("The department should be the same as the one specified", this.validDepartment, testProduct.getDepartment());
		assertEquals("The price should be the same as the one specified", this.validPrice, testProduct.getPrice());
		assertEquals("The unit should be the same as the one specified", this.validUnit, testProduct.getUnit());
		assertEquals("The xFor should be the same as the one specified", this.validXFor, testProduct.getXFor());
		assertEquals("The cost should be the same as the one specified", this.validCost, testProduct.getCost());
	}
}

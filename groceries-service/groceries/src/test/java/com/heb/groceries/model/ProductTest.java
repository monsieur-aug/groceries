package com.heb.groceries.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import com.heb.groceries.model.Product;

public class ProductTest {

	private final long			validId				= 1L;
	private final String		validDescription	= "Tasty Cakes";
	private final LocalDate		validLastSold		= LocalDate.of(1970, 1, 1);
	private final String		validDepartment		= "Produce";
	private final BigDecimal	validPrice			= new BigDecimal("5.99");
	private final String		validUnit			= "Each";
	private final int			validXFor			= 1;
	private final BigDecimal	validCost			= new BigDecimal("1.20");

	@Test
	public void testProductZeroParameters() {
		final long expectedId = 0L;
		final String expectedDescription = null;
		final LocalDate expectedLastSold = null;
		final int expectedShelfLifeDays = 0;
		final String expectedDepartment = null;
		final BigDecimal expectedPrice = null;
		final String expectedUnit = null;
		final int expectedXFor = 0;
		final BigDecimal expectedCost = null;

		final Product testProduct = new Product();

		assertEquals("The id should be initialized to the default value", expectedId, testProduct.getId());
		assertEquals("The description should be initialized to the default value", expectedDescription, testProduct.getDescription());
		assertEquals("The last sold date should be initialized to the default value", expectedLastSold, testProduct.getLastSold());
		assertEquals("The shelf life days should be initialized to the default value", expectedShelfLifeDays, testProduct.getShelfLifeDays());
		assertEquals("The department should be initialized to the default value", expectedDepartment, testProduct.getDepartment());
		assertEquals("The price should be initialized to the default value", expectedPrice, testProduct.getPrice());
		assertEquals("The unit should be initialized to the default value", expectedUnit, testProduct.getUnit());
		assertEquals("The xFor should be initialized to the default value", expectedXFor, testProduct.getXFor());
		assertEquals("The cost should be initialized to the default value", expectedCost, testProduct.getCost());
	}

	@Test
	public void testSetIdSetsValidId() {
		final Product testProduct = new Product();
		testProduct.setId(this.validId);

		assertEquals("The id should be the same as the one specified by the setter", this.validId, testProduct.getId());
	}

	@Test
	public void testSetDescriptionSetsValidDescription() {
		final Product testProduct = new Product();
		testProduct.setDescription(this.validDescription);

		assertEquals("The description should be the same as the one specified by the setter", this.validDescription, testProduct.getDescription());
	}

	@Test
	public void testSetLastSoldSetsValidLastSold() {
		final Product testProduct = new Product();
		testProduct.setLastSold(this.validLastSold);

		assertEquals("The last sold date should be the same as the one specified by the setter", this.validLastSold, testProduct.getLastSold());
	}

	@Test
	public void testSetDepartmentSetsValidDepartment() {
		final Product testProduct = new Product();
		testProduct.setDepartment(this.validDepartment);

		assertEquals("The department should be the same as the one specified by the setter", this.validDepartment, testProduct.getDepartment());
	}

	@Test
	public void testSetPriceSetsValidPrice() {
		final Product testProduct = new Product();
		testProduct.setPrice(this.validPrice);

		assertEquals("The price should be the same as the one specified by the setter", this.validPrice, testProduct.getPrice());
	}

	@Test
	public void testSetUnitSetsValidUnit() {
		final Product testProduct = new Product();
		testProduct.setUnit(this.validUnit);

		assertEquals("The unit should be the same as the one specified by the setter", this.validUnit, testProduct.getUnit());
	}

	@Test
	public void testSetXForSetsValidXFor() {
		final Product testProduct = new Product();
		testProduct.setXFor(this.validXFor);

		assertEquals("The xFor should be the same as the one specified by the setter", this.validXFor, testProduct.getXFor());
	}

	@Test
	public void testSetCostSetsValidCost() {
		final Product testProduct = new Product();
		testProduct.setCost(this.validCost);

		assertEquals("The cost should be the same as the one specified by the setter", this.validCost, testProduct.getCost());
	}

}

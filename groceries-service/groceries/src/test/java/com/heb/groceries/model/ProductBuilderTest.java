package com.heb.groceries.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.heb.groceries.error.Error;

public class ProductBuilderTest {

	private final long			validId				= 1L;
	private final String		validDescription	= "Tasty Cakes";
	private final LocalDate		validLastSold		= LocalDate.of(1970, 1, 1);
	private final int			validShelfLifeDays	= 30;
	private final String		validDepartment		= "Produce";
	private final BigDecimal	validPrice			= new BigDecimal("5.99");
	private final String		validUnit			= "Each";
	private final int			validXFor			= 1;
	private final BigDecimal	validCost			= new BigDecimal("1.20");

	@Rule
	public ExpectedException	expectedException	= ExpectedException.none();

	@Test
	public void testProductBuilderBuildsProductGivenValidValues() {
		final ProductBuilder testBuilder = new ProductBuilder();

		testBuilder.id(this.validId).description(this.validDescription).lastSold(this.validLastSold).shelfLifeDays(this.validShelfLifeDays)
						.department(this.validDepartment).price(this.validPrice).unit(this.validUnit).xFor(this.validXFor).cost(this.validCost);

		final Product testProduct = testBuilder.build();

		assertEquals("The id should be the same as the one specified", this.validId, testProduct.getId());
		assertEquals("The description should be the same as the one specified", this.validDescription, testProduct.getDescription());
		assertEquals("The last sold date should be the same as the one specified", this.validLastSold, testProduct.getLastSold());
		assertEquals("The shelf life days should be the same as the one specified", this.validShelfLifeDays, testProduct.getShelfLifeDays());
		assertEquals("The department should be the same as the one specified", this.validDepartment, testProduct.getDepartment());
		assertEquals("The price should be the same as the one specified", this.validPrice, testProduct.getPrice());
		assertEquals("The unit should be the same as the one specified", this.validUnit, testProduct.getUnit());
		assertEquals("The xFor should be the same as the one specified", this.validXFor, testProduct.getXFor());
		assertEquals("The cost should be the same as the one specified", this.validCost, testProduct.getCost());
	}

	@Test
	public void testProductBuilderThrowsIllegalStateExceptionGivenUnsetIdDuringBuildAttempt() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Error.PRODUCT_BUILDER_INCOMPLETE.toString());

		final ProductBuilder testBuilderWithoutId = new ProductBuilder();
		testBuilderWithoutId.description(this.validDescription).lastSold(this.validLastSold).shelfLifeDays(this.validShelfLifeDays)
						.department(this.validDepartment).price(this.validPrice).unit(this.validUnit).xFor(this.validXFor).cost(this.validCost);

		testBuilderWithoutId.build();
	}

	@Test
	public void testProductBuilderThrowsIllegalStateExceptionGivenUnsetDescriptionDuringBuildAttempt() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Error.PRODUCT_BUILDER_INCOMPLETE.toString());

		final ProductBuilder testBuilderWithoutDescription = new ProductBuilder();
		testBuilderWithoutDescription.id(this.validId).lastSold(this.validLastSold).shelfLifeDays(this.validShelfLifeDays).department(this.validDepartment)
						.price(this.validPrice).unit(this.validUnit).xFor(this.validXFor).cost(this.validCost);

		testBuilderWithoutDescription.build();
	}

	@Test
	public void testProductBuilderThrowsIllegalStateExceptionGivenUnsetLastSoldDateDuringBuildAttempt() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Error.PRODUCT_BUILDER_INCOMPLETE.toString());

		final ProductBuilder testBuilderWithoutLastSoldDate = new ProductBuilder();
		testBuilderWithoutLastSoldDate.id(this.validId).description(this.validDescription).shelfLifeDays(this.validShelfLifeDays)
						.department(this.validDepartment).price(this.validPrice).unit(this.validUnit).xFor(this.validXFor).cost(this.validCost);

		testBuilderWithoutLastSoldDate.build();
	}

	@Test
	public void testProductBuilderThrowsIllegalStateExceptionGivenUnsetShelfLifeDaysDuringBuildAttempt() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Error.PRODUCT_BUILDER_INCOMPLETE.toString());

		final ProductBuilder testBuilderWithoutShelfLifeDays = new ProductBuilder();
		testBuilderWithoutShelfLifeDays.id(this.validId).description(this.validDescription).lastSold(this.validLastSold).department(this.validDepartment)
						.price(this.validPrice).unit(this.validUnit).xFor(this.validXFor).cost(this.validCost);

		testBuilderWithoutShelfLifeDays.build();
	}

	@Test
	public void testProductBuilderThrowsIllegalStateExceptionGivenUnsetDepartmentDuringBuildAttempt() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Error.PRODUCT_BUILDER_INCOMPLETE.toString());

		final ProductBuilder testBuilderWithoutDepartment = new ProductBuilder();
		testBuilderWithoutDepartment.id(this.validId).description(this.validDescription).lastSold(this.validLastSold).shelfLifeDays(this.validShelfLifeDays)
						.price(this.validPrice).unit(this.validUnit).xFor(this.validXFor).cost(this.validCost);

		testBuilderWithoutDepartment.build();
	}

	@Test
	public void testProductBuilderThrowsIllegalStateExceptionGivenUnsetPriceDuringBuildAttempt() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Error.PRODUCT_BUILDER_INCOMPLETE.toString());

		final ProductBuilder testBuilderWithoutPrice = new ProductBuilder();
		testBuilderWithoutPrice.id(this.validId).description(this.validDescription).lastSold(this.validLastSold).shelfLifeDays(this.validShelfLifeDays)
						.department(this.validDepartment).unit(this.validUnit).xFor(this.validXFor).cost(this.validCost);

		testBuilderWithoutPrice.build();
	}

	@Test
	public void testProductBuilderThrowsIllegalStateExceptionGivenUnsetUnitDuringBuildAttempt() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Error.PRODUCT_BUILDER_INCOMPLETE.toString());

		final ProductBuilder testBuilderWithoutUnit = new ProductBuilder();
		testBuilderWithoutUnit.id(this.validId).description(this.validDescription).lastSold(this.validLastSold).shelfLifeDays(this.validShelfLifeDays)
						.department(this.validDepartment).price(this.validPrice).xFor(this.validXFor).cost(this.validCost);

		testBuilderWithoutUnit.build();
	}

	@Test
	public void testProductBuilderThrowsIllegalStateExceptionGivenUnsetXForDuringBuildAttempt() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Error.PRODUCT_BUILDER_INCOMPLETE.toString());

		final ProductBuilder testBuilderWithoutXFor = new ProductBuilder();
		testBuilderWithoutXFor.id(this.validId).description(this.validDescription).lastSold(this.validLastSold).shelfLifeDays(this.validShelfLifeDays)
						.department(this.validDepartment).price(this.validPrice).unit(this.validUnit).cost(this.validCost);

		testBuilderWithoutXFor.build();
	}

	@Test
	public void testProductBuilderThrowsIllegalStateExceptionGivenUnsetCostDuringBuildAttempt() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(Error.PRODUCT_BUILDER_INCOMPLETE.toString());

		final ProductBuilder testBuilderWithoutCost = new ProductBuilder();
		testBuilderWithoutCost.id(this.validId).description(this.validDescription).lastSold(this.validLastSold).shelfLifeDays(this.validShelfLifeDays)
						.department(this.validDepartment).price(this.validPrice).unit(this.validUnit).xFor(this.validXFor);

		testBuilderWithoutCost.build();
	}

}

package com.heb.groceries.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.heb.groceries.error.Error;
import com.heb.groceries.model.Product;

public class ProductTest {

	private final Long			validId				= new Long(1L);
	private final String		validDescription	= "Tasty Cakes";
	private final LocalDate		validLastSold		= LocalDate.of(1970, 1, 1);
	private final Integer		validShelfLifeDays	= new Integer(30);
	private final String		validDepartment		= "Produce";
	private final BigDecimal	validPrice			= new BigDecimal("5.99");
	private final String		validUnit			= "Each";
	private final Integer		validXFor			= new Integer(1);
	private final BigDecimal	validCost			= new BigDecimal("1.20");

	@Rule
	public ExpectedException	expectedException	= ExpectedException.none();

	@Test
	public void testProductZeroParameters() {
		final Product testProduct = new Product();

		assertNull("The id should be initialized to the default value", testProduct.getId());
		assertNull("The description should be initialized to the default value", testProduct.getDescription());
		assertNull("The last sold date should be initialized to the default value", testProduct.getLastSold());
		assertNull("The shelf life days should be initialized to the default value", testProduct.getShelfLifeDays());
		assertNull("The department should be initialized to the default value", testProduct.getDepartment());
		assertNull("The price should be initialized to the default value", testProduct.getPrice());
		assertNull("The unit should be initialized to the default value", testProduct.getUnit());
		assertNull("The xFor should be initialized to the default value", testProduct.getXFor());
		assertNull("The cost should be initialized to the default value", testProduct.getCost());
	}

	@Test
	public void testSetIdSetsValidId() {
		final Product testProduct = new Product();
		testProduct.setId(this.validId);

		assertEquals("The id should be the same as the one specified by the setter", this.validId, testProduct.getId());
	}

	@Test
	public void testSetIdThrowsIllegalArgumentExceptionGivenNullId() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_ID_NULL.toString());

		final Long nullId = null;
		final Product testProduct = new Product();
		testProduct.setId(nullId);
	}

	@Test
	public void testSetIdThrowsIllegalArgumentExceptionGivenNegativeId() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_ID_NEGATIVE_OR_ZERO.toString());

		final Long negativeId = new Long(-1L);
		final Product testProduct = new Product();
		testProduct.setId(negativeId);
	}

	@Test
	public void testSetIdThrowsIllegalArgumentExceptionGivenZeroedId() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_ID_NEGATIVE_OR_ZERO.toString());

		final Long zeroedId = new Long(0L);
		final Product testProduct = new Product();
		testProduct.setId(zeroedId);
	}

	@Test
	public void testSetDescriptionSetsValidDescription() {
		final Product testProduct = new Product();
		testProduct.setDescription(this.validDescription);

		assertEquals("The description should be the same as the one specified by the setter", this.validDescription, testProduct.getDescription());
	}

	@Test
	public void testSetDescriptionThrowsIllegalArgumentExceptionGivenNullDescription() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_DESCRIPTION_NULL_BLANK_EMPTY.toString());

		final String nullDescription = null;
		final Product testProduct = new Product();
		testProduct.setDescription(nullDescription);
	}

	@Test
	public void testSetDescriptionThrowsIllegalArgumentExceptionGivenBlankDescription() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_DESCRIPTION_NULL_BLANK_EMPTY.toString());

		final String blankDescription = "  ";
		final Product testProduct = new Product();
		testProduct.setDescription(blankDescription);
	}

	@Test
	public void testSetDescriptionThrowsIllegalArgumentExceptionGivenEmptyDescription() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_DESCRIPTION_NULL_BLANK_EMPTY.toString());

		final String emptyDescription = StringUtils.EMPTY;
		final Product testProduct = new Product();
		testProduct.setDescription(emptyDescription);
	}

	@Test
	public void testSetDescriptionThrowsIllegalArgumentExceptionGivenNonAlphaSpaceDescription() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_DESCRIPTION_NOT_ALPHA_SPACE.toString());

		final String nonAlphaSpaceDescription = "de$criptionWithNumb3rAndSymbol%";
		final Product testProduct = new Product();
		testProduct.setDescription(nonAlphaSpaceDescription);
	}

	@Test
	public void testSetLastSoldSetsValidLastSold() {
		final Product testProduct = new Product();
		testProduct.setLastSold(this.validLastSold);

		assertEquals("The last sold date should be the same as the one specified by the setter", this.validLastSold, testProduct.getLastSold());
	}

	@Test
	public void testSetLastSoldThrowsIllegalArgumentExceptionGivenNullLastSoldDate() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_LAST_SOLD_DATE_NULL.toString());

		final LocalDate nullLastSoldDate = null;
		final Product testProduct = new Product();
		testProduct.setLastSold(nullLastSoldDate);
	}

	@Test
	public void testSetShelfLifeDaysSetsValidShelfLifeDays() {
		final Product testProduct = new Product();
		testProduct.setShelfLifeDays(this.validShelfLifeDays);

		assertEquals("The shelf life days should be the same as the one specified by the setter", this.validShelfLifeDays, testProduct.getShelfLifeDays());
	}

	@Test
	public void testSetShelfLifeDaysThrowsIllegalArgumentExceptionGivenNullNumberOfDays() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_SHELF_LIFE_NULL.toString());

		final Integer nullShelfLifeDays = null;
		final Product testProduct = new Product();
		testProduct.setShelfLifeDays(nullShelfLifeDays);
	}

	@Test
	public void testSetShelfLifeDaysThrowsIllegalArgumentExceptionGivenNegativeNumberOfDays() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_SHELF_LIFE_NEGATIVE_OR_ZERO.toString());

		final Integer negativeShelfLifeDays = new Integer(-1);
		final Product testProduct = new Product();
		testProduct.setShelfLifeDays(negativeShelfLifeDays);
	}

	@Test
	public void testSetShelfLifeDaysThrowsIllegalArgumentExceptionGivenZeroedNumberOfDays() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_SHELF_LIFE_NEGATIVE_OR_ZERO.toString());

		final Integer zeroedShelfLifeDays = new Integer(0);
		final Product testProduct = new Product();
		testProduct.setShelfLifeDays(zeroedShelfLifeDays);
	}

	@Test
	public void testSetDepartmentSetsValidDepartment() {
		final Product testProduct = new Product();
		testProduct.setDepartment(this.validDepartment);

		assertEquals("The department should be the same as the one specified by the setter", this.validDepartment, testProduct.getDepartment());
	}

	@Test
	public void testSetDepartmentThrowsIllegalArgumentExceptionGivenNullDepartment() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_DEPARTMENT_NULL_BLANK_EMPTY.toString());

		final String nullDepartment = null;
		final Product testProduct = new Product();
		testProduct.setDepartment(nullDepartment);
	}

	@Test
	public void testSetDepartmentThrowsIllegalArgumentExceptionGivenBlankDepartment() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_DEPARTMENT_NULL_BLANK_EMPTY.toString());

		final String blankDepartment = "  ";
		final Product testProduct = new Product();
		testProduct.setDepartment(blankDepartment);
	}

	@Test
	public void testSetDepartmentThrowsIllegalArgumentExceptionGivenEmptyDepartment() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_DEPARTMENT_NULL_BLANK_EMPTY.toString());

		final String emptyDepartment = StringUtils.EMPTY;
		final Product testProduct = new Product();
		testProduct.setDepartment(emptyDepartment);
	}

	@Test
	public void testSetDepartmentThrowsIllegalArgumentExceptionGivenNonAlphaSpaceDepartment() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_DEPARTMENT_NOT_ALPHA_SPACE.toString());

		final String nonAlphaSpaceDepartment = "d3partm#ntWithNumb3rs&Symbols";
		final Product testProduct = new Product();
		testProduct.setDepartment(nonAlphaSpaceDepartment);
	}

	@Test
	public void testSetPriceSetsValidPrice() {
		final Product testProduct = new Product();
		testProduct.setPrice(this.validPrice);

		assertEquals("The price should be the same as the one specified by the setter", this.validPrice, testProduct.getPrice());
	}

	@Test
	public void testSetPriceThrowsIllegalArgumentExceptionGivenNullPrice() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_PRICE_NULL.toString());

		final BigDecimal nullPrice = null;
		final Product testProduct = new Product();
		testProduct.setPrice(nullPrice);
	}

	@Test
	public void testSetPriceThrowsIllegalArgumentExceptionGivenNegativePrice() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_PRICE_NEGATIVE.toString());

		final BigDecimal negativePrice = new BigDecimal("-1.99");
		final Product testProduct = new Product();
		testProduct.setPrice(negativePrice);
	}

	@Test
	public void testSetUnitSetsValidUnit() {
		final Product testProduct = new Product();
		testProduct.setUnit(this.validUnit);

		assertEquals("The unit should be the same as the one specified by the setter", this.validUnit, testProduct.getUnit());
	}

	@Test
	public void testSetUnitThrowsIllegalArgumentExceptionGivenNullUnit() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_UNIT_NULL_BLANK_EMPTY.toString());

		final String nullUnit = null;
		final Product testProduct = new Product();
		testProduct.setUnit(nullUnit);
	}

	@Test
	public void testSetUnitThrowsIllegalArgumentExceptionGivenBlankUnit() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_UNIT_NULL_BLANK_EMPTY.toString());

		final String blankUnit = "  ";
		final Product testProduct = new Product();
		testProduct.setUnit(blankUnit);
	}

	@Test
	public void testSetUnitThrowsIllegalArgumentExceptionGivenEmptyUnit() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_UNIT_NULL_BLANK_EMPTY.toString());

		final String emptyUnit = StringUtils.EMPTY;
		final Product testProduct = new Product();
		testProduct.setUnit(emptyUnit);
	}

	@Test
	public void testSetUnitThrowsIllegalArgumentExceptionGivenNonAlphaSpaceUnit() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_UNIT_NOT_ALPHA_SPACE.toString());

		final String nonAlphaSpaceUnit = "unitW1thNumb3rs&Symb*ls";
		final Product testProduct = new Product();
		testProduct.setUnit(nonAlphaSpaceUnit);
	}

	@Test
	public void testSetXForSetsValidXFor() {
		final Product testProduct = new Product();
		testProduct.setXFor(this.validXFor);

		assertEquals("The xFor should be the same as the one specified by the setter", this.validXFor, testProduct.getXFor());
	}

	@Test
	public void testSetXForThrowsIllegalArgumentExceptionGivenNullXFor() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_XFOR_NULL.toString());

		final Integer nullXFor = null;
		final Product testProduct = new Product();
		testProduct.setXFor(nullXFor);
	}

	@Test
	public void testSetXForThrowsIllegalArgumentExceptionGivenNegativeXFor() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_XFOR_NEGATIVE_OR_ZERO.toString());

		final Integer negativeXFor = new Integer(-1);
		final Product testProduct = new Product();
		testProduct.setXFor(negativeXFor);
	}

	@Test
	public void testSetXForThrowsIllegalArgumentExceptionGivenZeroedXFor() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_XFOR_NEGATIVE_OR_ZERO.toString());

		final Integer zeroedXFor = new Integer(0);
		final Product testProduct = new Product();
		testProduct.setXFor(zeroedXFor);
	}

	@Test
	public void testSetCostSetsValidCost() {
		final Product testProduct = new Product();
		testProduct.setCost(this.validCost);

		assertEquals("The cost should be the same as the one specified by the setter", this.validCost, testProduct.getCost());
	}

	@Test
	public void testSetCostThrowsIllegalArgumentExceptionGivenNullCost() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_COST_NULL.toString());

		final BigDecimal nullCost = null;
		final Product testProduct = new Product();
		testProduct.setCost(nullCost);
	}

	@Test
	public void testSetCostThrowsIllegalArgumentExceptionGivenNegativeCost() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage(Error.PRODUCT_COST_NEGATIVE.toString());

		final BigDecimal negativeCost = new BigDecimal("-2.50");
		final Product testProduct = new Product();
		testProduct.setCost(negativeCost);
	}

	@Test
	public void testEqualsReturnsTrueGivenEquivalentProduct() {
		final Product aProduct = createValidProduct();
		final Product anEquivalentProduct = createValidProduct();

		assertEquals("The products should be considered equal", aProduct, anEquivalentProduct);
	}

	@Test
	public void testEqualsReturnsFalseGivenProductWithDifferentId() {
		final Product aProduct = createValidProduct();
		final Product aProductWithDifferentId = createValidProduct();
		aProductWithDifferentId.setId(aProduct.getId() + 1L);

		assertNotEquals("The products should not be considered equal", aProduct, aProductWithDifferentId);
	}

	@Test
	public void testHashCodesAreTheSameForEquivalentProducts() {
		final Product aProduct = createValidProduct();
		final Product anEquivalentProduct = createValidProduct();

		final int aProductHashCode = aProduct.hashCode();
		final int anEquivalentProductHashCode = anEquivalentProduct.hashCode();

		assertEquals("The hash codes should be the same for equivalent products", aProductHashCode, anEquivalentProductHashCode);
	}

	@Test
	public void testHashCodesAreDifferentForDifferentProducts() {
		final Product aProduct = createValidProduct();
		final Product aProductWithDifferentId = createValidProduct();
		aProductWithDifferentId.setId(aProduct.getId() + 1L);

		final int aProductHashCode = aProduct.hashCode();
		final int aProductWithDifferentIdHashCode = aProductWithDifferentId.hashCode();

		assertNotEquals("The hash codes should be different for different products", aProductHashCode, aProductWithDifferentIdHashCode);
	}

	private Product createValidProduct() {
		final Product validProduct = new Product();

		validProduct.setId(this.validId);
		validProduct.setDescription(this.validDescription);
		validProduct.setLastSold(this.validLastSold);
		validProduct.setShelfLifeDays(this.validShelfLifeDays);
		validProduct.setDepartment(this.validDepartment);
		validProduct.setPrice(this.validPrice);
		validProduct.setUnit(this.validUnit);
		validProduct.setXFor(this.validXFor);
		validProduct.setCost(this.validCost);

		return validProduct;
	}

}

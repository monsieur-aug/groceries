package com.heb.groceries.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.heb.groceries.model.Product;
import com.heb.groceries.service.ProductService;

public class GroceriesDBInitializerTest {

	public static final String TEST_DATABASE_NAME = "groceriesdb_test.jdbc";

	@Test
	public void testInitialization() {
		final int expectedNumberOfItems = 20;
		GroceriesDBInitializer.initialize(TEST_DATABASE_NAME);

		ProductService service = new ProductService(TEST_DATABASE_NAME);
		List<Product> allProducts = service.getAllProducts();

		assertEquals("The expected number of items should be returned", expectedNumberOfItems, allProducts.size());
	}
}

package com.heb.groceries.resource;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.heb.groceries.model.Product;

public class ProductResourceFunctionalTest extends JerseyTest {

	public static final String PRODUCT_RESOURCE = "products";

	@Override
	protected Application configure() {
		final ResourceConfig rc = new ResourceConfig(ProductResource.class);

		return rc;
	}

	@Test
	public void testServiceReturnsOK200WithAllProducts() {
		final int expectedNumberOfProducts = 20;

		final Response response = target(PRODUCT_RESOURCE).request().get(Response.class);
		List<Product> returnedProducts = response.readEntity(new GenericType<List<Product>>() {});

		assertEquals("The response code should indicate OK (200)", Status.OK.getStatusCode(), response.getStatus());
		assertEquals("There should be the expected number of products returned", expectedNumberOfProducts, returnedProducts.size());
	}

}

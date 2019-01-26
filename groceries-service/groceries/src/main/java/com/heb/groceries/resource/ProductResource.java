package com.heb.groceries.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.heb.groceries.error.Error;
import com.heb.groceries.model.Product;
import com.heb.groceries.service.ProductService;

@Path("products")
public class ProductResource {

	private ProductService service;

	public ProductResource() {
		this(new ProductService());
	}

	public ProductResource(final ProductService service) {
		setService(service);
	}

	public ProductService getService() {
		return this.service;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProducts() {
		return this.service.getAllProducts();
	}

	@GET
	@Path("/{product-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductById(@PathParam("product-id") final long id) {
		return this.service.findProductWithId(id);
	}

	private void setService(final ProductService service) {
		if (service == null) {
			throw new IllegalArgumentException(Error.PRODUCT_SERVICE_NULL.toString());
		}

		this.service = service;
	}

}

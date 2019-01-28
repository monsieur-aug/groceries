package com.heb.groceries.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.heb.groceries.error.Error;
import com.heb.groceries.model.Product;
import com.heb.groceries.resource.query.QueryParam;
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
	public List<Product> getProducts(@Context final UriInfo uriInfo) {
		final MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		List<Product> retrievedProducts = null;

		if (!queryParams.isEmpty()) {
			if (isGetProductsByDescription(queryParams)) {
				retrievedProducts = getProductsByDescription(queryParams);
			} else if (isGetProductsByDepartment(queryParams)) {
				retrievedProducts = getProductsByDepartment(queryParams);
			} else if (isGetProductsByShelfLifeDays(queryParams)) {
				retrievedProducts = getProductsByShelfLife(queryParams);
			} else {
				// TODO: properly handle unrecognized query param by returning an error
			}
		} else {
			retrievedProducts = this.service.getAllProducts();
		}

		return retrievedProducts;
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

	private boolean isGetProductsByDescription(final MultivaluedMap<String, String> queryParams) {
		return queryParams.containsKey(QueryParam.DESCRIPTION.toString());
	}

	private boolean isGetProductsByDepartment(final MultivaluedMap<String, String> queryParams) {
		return queryParams.containsKey(QueryParam.DEPARTMENT.toString());
	}

	private boolean isGetProductsByShelfLifeDays(final MultivaluedMap<String, String> queryParams) {
		return queryParams.containsKey(QueryParam.SHELF_LIFE_DAYS_MIN.toString()) && queryParams.containsKey(QueryParam.SHELF_LIFE_DAYS_MAX.toString());
	}

	private List<Product> getProductsByDescription(final MultivaluedMap<String, String> queryParams) {
		final String description = queryParams.getFirst(QueryParam.DESCRIPTION.toString());

		final List<Product> retrievedProducts = this.service.findProductsWithDescription(description);

		return retrievedProducts;
	}

	private List<Product> getProductsByDepartment(final MultivaluedMap<String, String> queryParams) {
		final String department = queryParams.getFirst(QueryParam.DEPARTMENT.toString());

		final List<Product> retrievedProducts = this.service.findProductsWithDepartment(department);

		return retrievedProducts;
	}

	private List<Product> getProductsByShelfLife(final MultivaluedMap<String, String> queryParams) {
		final String minimum = queryParams.getFirst(QueryParam.SHELF_LIFE_DAYS_MIN.toString());
		final String maximum = queryParams.getFirst(QueryParam.SHELF_LIFE_DAYS_MAX.toString());

		final List<Product> retrievedProducts = this.service.findProductWithShelfLifeDays(minimum, maximum);

		return retrievedProducts;
	}

}

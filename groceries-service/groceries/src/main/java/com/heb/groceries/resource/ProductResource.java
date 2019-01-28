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

import org.apache.commons.collections4.CollectionUtils;

import com.heb.groceries.error.Error;
import com.heb.groceries.exception.UnsupportedQueryException;
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

		if (queryParams.isEmpty()) {
			retrievedProducts = this.service.getAllProducts();
		} else if (isQuerySupported(queryParams)) {
			if (isGetProductsByDescription(queryParams)) {
				retrievedProducts = merge(retrievedProducts, getProductsByDescription(queryParams));
			}

			if (isGetProductsByDepartment(queryParams)) {
				retrievedProducts = merge(retrievedProducts, getProductsByDepartment(queryParams));
			}

			if (isGetProductsByShelfLifeDays(queryParams)) {
				retrievedProducts = merge(retrievedProducts, getProductsByShelfLife(queryParams));
			}

			if (isGetProductsByUnit(queryParams)) {
				retrievedProducts = merge(retrievedProducts, getProductsByUnit(queryParams));
			}

			if (isGetProductsByXFor(queryParams)) {
				retrievedProducts = merge(retrievedProducts, getProductsByXFor(queryParams));
			}

			if (isGetProductsByPrice(queryParams)) {
				retrievedProducts = merge(retrievedProducts, getProductsByPrice(queryParams));
			}

			if (isGetProductsByCost(queryParams)) {
				retrievedProducts = merge(retrievedProducts, getProductsByCost(queryParams));
			}

			if (isGetProductsByLastSoldDate(queryParams)) {
				retrievedProducts = merge(retrievedProducts, getProductsByLastSoldDate(queryParams));
			}
		} else {
			throw new UnsupportedQueryException("The provided query param combination is invalid.");
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
	
	private boolean isQuerySupported(final MultivaluedMap<String, String> queryParams) {
		for (final String queryParam : queryParams.keySet()) {
			if (!QueryParam.ALL_PARAMS.contains(queryParam)) {
				return false;
			}
		}
		
		return true;
	}

	private boolean isGetProductsByDescription(final MultivaluedMap<String, String> queryParams) {
		return queryParams.containsKey(QueryParam.DESCRIPTION.toString());
	}

	private boolean isGetProductsByDepartment(final MultivaluedMap<String, String> queryParams) {
		return queryParams.containsKey(QueryParam.DEPARTMENT.toString());
	}

	private boolean isGetProductsByShelfLifeDays(final MultivaluedMap<String, String> queryParams) {
		final boolean containsMinShelfLifeDays = queryParams.containsKey(QueryParam.SHELF_LIFE_DAYS_MIN.toString());
		final boolean containsMaxShelfLifeDays = queryParams.containsKey(QueryParam.SHELF_LIFE_DAYS_MAX.toString());

		if (containsMinShelfLifeDays ^ containsMaxShelfLifeDays) {
			throw new UnsupportedQueryException("Shelf life queries must specify minimum and maximum days.");
		}

		return containsMinShelfLifeDays && containsMaxShelfLifeDays;
	}

	private boolean isGetProductsByUnit(final MultivaluedMap<String, String> queryParams) {
		return queryParams.containsKey(QueryParam.UNIT.toString());
	}

	private boolean isGetProductsByXFor(final MultivaluedMap<String, String> queryParams) {
		final boolean containsMinXFor = queryParams.containsKey(QueryParam.XFOR_MIN.toString());
		final boolean containsMaxXFor = queryParams.containsKey(QueryParam.XFOR_MAX.toString());

		if (containsMinXFor ^ containsMaxXFor) {
			throw new UnsupportedQueryException("XFor queries must specify minimum and maximum values.");
		}

		return containsMinXFor && containsMaxXFor;
	}

	private boolean isGetProductsByPrice(final MultivaluedMap<String, String> queryParams) {
		final boolean containsMinPrice = queryParams.containsKey(QueryParam.PRICE_MIN.toString());
		final boolean containsMaxPrice = queryParams.containsKey(QueryParam.PRICE_MAX.toString());

		if (containsMinPrice ^ containsMaxPrice) {
			throw new UnsupportedQueryException("Price queries must specify minimum and maximum prices.");
		}

		return containsMinPrice && containsMaxPrice;
	}

	private boolean isGetProductsByCost(final MultivaluedMap<String, String> queryParams) {
		final boolean containsMinCost = queryParams.containsKey(QueryParam.COST_MIN.toString());
		final boolean containsMaxCost = queryParams.containsKey(QueryParam.COST_MAX.toString());

		if (containsMinCost ^ containsMaxCost) {
			throw new UnsupportedQueryException("Cost queries must specify minimum and maximum costs.");
		}

		return containsMinCost && containsMaxCost;
	}

	private boolean isGetProductsByLastSoldDate(final MultivaluedMap<String, String> queryParams) {
		final boolean containsLastSoldDateStart = queryParams.containsKey(QueryParam.LAST_SOLD_DATE_START.toString());
		final boolean containsLastSoldDateEnd = queryParams.containsKey(QueryParam.LAST_SOLD_DATE_END.toString());

		if (containsLastSoldDateStart ^ containsLastSoldDateEnd) {
			throw new UnsupportedQueryException("Last sold date queries must specify start and end dates.");
		}

		return containsLastSoldDateStart && containsLastSoldDateEnd;
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

	private List<Product> getProductsByUnit(final MultivaluedMap<String, String> queryParams) {
		final String unit = queryParams.getFirst(QueryParam.UNIT.toString());

		final List<Product> retrievedProducts = this.service.findProductsWithUnit(unit);

		return retrievedProducts;
	}

	private List<Product> getProductsByXFor(final MultivaluedMap<String, String> queryParams) {
		final String minimum = queryParams.getFirst(QueryParam.XFOR_MIN.toString());
		final String maximum = queryParams.getFirst(QueryParam.XFOR_MAX.toString());

		final List<Product> retrievedProducts = this.service.findProductsWithXFor(minimum, maximum);

		return retrievedProducts;
	}

	private List<Product> getProductsByPrice(final MultivaluedMap<String, String> queryParams) {
		final String minimum = queryParams.getFirst(QueryParam.PRICE_MIN.toString());
		final String maximum = queryParams.getFirst(QueryParam.PRICE_MAX.toString());

		final List<Product> retrievedProducts = this.service.findProductsWithPrice(minimum, maximum);

		return retrievedProducts;
	}

	private List<Product> getProductsByCost(final MultivaluedMap<String, String> queryParams) {
		final String minimum = queryParams.getFirst(QueryParam.COST_MIN.toString());
		final String maximum = queryParams.getFirst(QueryParam.COST_MAX.toString());

		final List<Product> retrievedProducts = this.service.findProductsWithCost(minimum, maximum);

		return retrievedProducts;
	}

	private List<Product> getProductsByLastSoldDate(final MultivaluedMap<String, String> queryParams) {
		final String startDate = queryParams.getFirst(QueryParam.LAST_SOLD_DATE_START.toString());
		final String endDate = queryParams.getFirst(QueryParam.LAST_SOLD_DATE_END.toString());

		final List<Product> retrievedProducts = this.service.findProductsWithLastSoldDate(startDate, endDate);

		return retrievedProducts;
	}

	private List<Product> merge(final List<Product> original, final List<Product> additional) {
		if (original == null) {
			return additional;
		}

		if (additional == null) {
			return original;
		}

		return (List<Product>) CollectionUtils.intersection(original, additional);
	}

}

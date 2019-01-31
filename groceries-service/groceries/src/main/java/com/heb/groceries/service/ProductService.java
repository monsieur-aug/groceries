package com.heb.groceries.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.heb.groceries.dao.DAOFactory;
import com.heb.groceries.dao.ProductDAO;
import com.heb.groceries.exception.ClientInputInvalidException;
import com.heb.groceries.exception.DataNotFoundException;
import com.heb.groceries.model.Product;

/**
 * Services requests for products based on various attributes.
 */
public class ProductService {

	private static final String	DEFAULT_DATABASE_NAME	= "groceriesdb.jdbc";

	private String				databaseName;
	private ProductDAO			dao;

	public ProductService() {
		this(DEFAULT_DATABASE_NAME);
	}

	/**
	 * Returns and instance of the service that will use the specified database
	 * 
	 * @see com.heb.groceries.dao.DAOProperties
	 * @param databaseName the name of the database as specified in the DAO config
	 *                     file
	 */
	public ProductService(final String databaseName) {
		setDatabaseName(databaseName);
		setDAO(databaseName);
	}

	public String getDatabaseName() {
		return this.databaseName;
	}

	public void setDatabaseName(final String databaseName) {
		if (StringUtils.isBlank(databaseName)) {
			throw new IllegalArgumentException();
		}

		this.databaseName = databaseName;
	}

	public ProductDAO getDAO() {
		return this.dao;
	}

	public void setDAO(final String databaseName) {
		final DAOFactory factory = DAOFactory.getInstance(databaseName);
		this.dao = factory.getProductDAO();
	}

	/**
	 * Retrieves a list of all products maintained by the service
	 * 
	 * @return a list of all products
	 */
	public List<Product> getAllProducts() {
		final List<Product> allProducts = this.dao.listAllProducts();

		return allProducts;
	}

	/**
	 * Retrieves a product with the specified id. Throws a
	 * <code>ClientInputInvalidException</code> if the specified id is invalid.
	 * Throws a <code>DataNotFoundException</code> if the product was not found.
	 * This method assumes that the product exists.
	 * 
	 * @param id the product id
	 * @return the product with the specified id
	 */
	public Product getProductWithId(final long id) {
		Product retrievedProduct = null;

		throwClientInputInvalidExceptionIfInvalidId(id);

		retrievedProduct = this.dao.findProductWithId(id);

		if (retrievedProduct == null) {
			throw new DataNotFoundException("Product with id " + id + " was not found.");
		}

		return retrievedProduct;
	}

	/**
	 * Retrieves products with the specified id.Throws a
	 * <code>ClientInputInvalidException</code> if the specified id is invalid. This
	 * method **does not** assume that the product exists.
	 * 
	 * @param id the product id
	 * @return a list of products with the specified id; may be empty
	 */
	public List<Product> findProductsWithId(final String id) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidId(id);

		final Long idAsLong = Long.parseLong(id);

		try {
			retrievedProducts.add(getProductWithId(idAsLong));
		} catch (DataNotFoundException dnfe) {
			return retrievedProducts;
		}

		return retrievedProducts;
	}

	/**
	 * Retrieves products with the a description similar to the specified
	 * description. Throws a <code>ClientInputInvalidException</code> if the
	 * specified description is invalid.
	 * 
	 * @param description the product description
	 * @return a list of products with a similar description; may be empty
	 */
	public List<Product> findProductsWithDescription(final String description) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidDescription(description);

		retrievedProducts = this.dao.findProductsWithDescription(description);

		return retrievedProducts;
	}

	/**
	 * Retrieves products with the specified department. Throws a
	 * <code>ClientInputInvalidException</code> if the specified department is
	 * invalid.
	 * 
	 * @param department a product department
	 * @return a list of products that have the specified department; may be empty
	 */
	public List<Product> findProductsWithDepartment(final String department) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidDepartment(department);

		retrievedProducts = this.dao.findProductsWithDepartment(department);

		return retrievedProducts;
	}

	/**
	 * Retrieves products with a shelf life that exists within the specified range
	 * of shelf life days (inclusive). Throws a
	 * <code>ClientInputInvalidException</code> if the range is invalid.
	 * 
	 * @param minimum the minimum shelf life
	 * @param maximum the maximum shelf life
	 * @return a list of products that have a shelf life within the specified range;
	 *         may be empty
	 */
	public List<Product> findProductsWithShelfLifeDays(final String minimum, final String maximum) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidShelfLifeRange(minimum, maximum);

		final Integer min = Integer.parseInt(minimum);
		final Integer max = Integer.parseInt(maximum);

		retrievedProducts = this.dao.findProductsWithShelfLife(min, max);

		return retrievedProducts;
	}

	/**
	 * Retrieves products with the specified unit. Throws a
	 * <code>ClientInputInvalidException</code> if the unit is invalid.
	 * 
	 * @param unit the product unit
	 * @return a list of products that have the specified unit; may be empty
	 */
	public List<Product> findProductsWithUnit(final String unit) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidUnit(unit);

		retrievedProducts = this.dao.findProductsWithUnit(unit);

		return retrievedProducts;
	}

	/**
	 * Retrieves products with an xFor factor that exists within the specified range
	 * (inclusive). Throws a <code>ClientInputInvalidException</code> if the range
	 * is invalid.
	 * 
	 * @param minimum the minimum xFor factor
	 * @param maximum the maximum xFor factor
	 * @return a list of products that have an xFor factor within the specified
	 *         range; may be empty
	 */
	public List<Product> findProductsWithXFor(final String minimum, final String maximum) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidXForRange(minimum, maximum);

		final Integer min = Integer.parseInt(minimum);
		final Integer max = Integer.parseInt(maximum);

		retrievedProducts = this.dao.findProductsWithXFor(min, max);

		return retrievedProducts;
	}

	/**
	 * Retrieves products with a price that exists within the specified range
	 * (inclusive). Throws a <code>ClientInputInvalidException</code> if the range
	 * is invalid.
	 * 
	 * @param minimum the minimum price
	 * @param maximum the maximum price
	 * @return a list of products that have a price within the specified range; may
	 *         be empty
	 */
	public List<Product> findProductsWithPrice(final String minimum, final String maximum) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidPriceRange(minimum, maximum);

		final BigDecimal min = new BigDecimal(minimum);
		final BigDecimal max = new BigDecimal(maximum);

		retrievedProducts = this.dao.findProductsWithPrice(min, max);

		return retrievedProducts;
	}

	/**
	 * Retrieves products with a cost that exists within the specified range
	 * (inclusive). Throws a <code>ClientInputInvalidException</code> if the range
	 * is invalid.
	 * 
	 * @param minimum the minimum cost
	 * @param maximum the maximum cost
	 * @return a list of products that have a cost within the specified range; may
	 *         be empty
	 */
	public List<Product> findProductsWithCost(final String minimum, final String maximum) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidCostRange(minimum, maximum);

		final BigDecimal min = new BigDecimal(minimum);
		final BigDecimal max = new BigDecimal(maximum);

		retrievedProducts = this.dao.findProductsWithCost(min, max);

		return retrievedProducts;
	}

	/**
	 * Retrieves products with a last sold date within the specified range
	 * (inclusive). Throws a <code>ClientInputInvalidException</code> if the range
	 * is invalid.
	 * 
	 * @param startDate a starting date in the format YYYY-MM-DD
	 * @param endDate   an ending date in the format YYYY-MM-DD
	 * @return a list of products that have a last sold date within the specified
	 *         range; may be empty
	 */
	public List<Product> findProductsWithLastSoldDate(final String startDate, final String endDate) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidLastSoldDateRange(startDate, endDate);

		final LocalDate start = LocalDate.parse(startDate);
		final LocalDate end = LocalDate.parse(endDate);

		retrievedProducts = this.dao.findProductsWithLastSoldDate(start, end);

		return retrievedProducts;
	}

	private void throwClientInputInvalidExceptionIfInvalidId(final long id) {
		try {
			final Product idValidationProduct = new Product();
			idValidationProduct.setId(id);
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

	private void throwClientInputInvalidExceptionIfInvalidId(final String id) {
		try {
			final Long idAsLong = Long.parseLong(id);

			final Product idValidationProduct = new Product();
			idValidationProduct.setId(idAsLong);
		} catch (NumberFormatException nfe) {
			throw new ClientInputInvalidException("The id must be a whole number.");
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

	private void throwClientInputInvalidExceptionIfInvalidDescription(final String description) {
		try {
			final Product descriptionValidationProduct = new Product();
			descriptionValidationProduct.setDescription(description);
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

	private void throwClientInputInvalidExceptionIfInvalidDepartment(final String department) {
		try {
			final Product departmentValidationProduct = new Product();
			departmentValidationProduct.setDepartment(department);
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

	private void throwClientInputInvalidExceptionIfInvalidShelfLifeRange(final String minimum, final String maximum) {
		try {
			final Integer min = Integer.parseInt(minimum);
			final Integer max = Integer.parseInt(maximum);

			final Product shelfLifeDaysValidationProduct = new Product();
			shelfLifeDaysValidationProduct.setShelfLifeDays(min);
			shelfLifeDaysValidationProduct.setShelfLifeDays(max);

			if (min > max) {
				throw new IllegalArgumentException("The minimum value must be less than or equal to the maximum value.");
			}
		} catch (NumberFormatException nfe) {
			throw new ClientInputInvalidException("The minimum and maximum shelf life values must be whole numbers.");
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

	private void throwClientInputInvalidExceptionIfInvalidUnit(final String unit) {
		try {
			final Product unitValidationProduct = new Product();
			unitValidationProduct.setUnit(unit);
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

	private void throwClientInputInvalidExceptionIfInvalidXForRange(final String minimum, final String maximum) {
		try {
			final Integer min = Integer.parseInt(minimum);
			final Integer max = Integer.parseInt(maximum);

			final Product xForValidationProduct = new Product();
			xForValidationProduct.setXFor(min);
			xForValidationProduct.setXFor(max);

			if (min > max) {
				throw new IllegalArgumentException("The minimum value must be less than or equal to the maximum value.");
			}
		} catch (NumberFormatException nfe) {
			throw new ClientInputInvalidException("The minimum and maximum xFor values must be whole numbers.");
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

	private void throwClientInputInvalidExceptionIfInvalidPriceRange(final String minimum, final String maximum) {
		try {
			final BigDecimal min = new BigDecimal(minimum);
			final BigDecimal max = new BigDecimal(maximum);

			final Product priceValidationProduct = new Product();
			priceValidationProduct.setPrice(min);
			priceValidationProduct.setPrice(max);

			if (min.compareTo(max) > 0) {
				throw new IllegalArgumentException("The minimum value must be less than or equal to the maximum value.");
			}
		} catch (NumberFormatException nfe) {
			throw new ClientInputInvalidException("The minimum and maximum price values must be whole or decimal numbers.");
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

	private void throwClientInputInvalidExceptionIfInvalidCostRange(final String minimum, final String maximum) {
		try {
			final BigDecimal min = new BigDecimal(minimum);
			final BigDecimal max = new BigDecimal(maximum);

			final Product costValidationProduct = new Product();
			costValidationProduct.setCost(min);
			costValidationProduct.setCost(max);

			if (min.compareTo(max) > 0) {
				throw new IllegalArgumentException("The minimum value must be less than or equal to the maximum value.");
			}
		} catch (NumberFormatException nfe) {
			throw new ClientInputInvalidException("The minimum and maximum cost values must be whole or decimal numbers.");
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

	private void throwClientInputInvalidExceptionIfInvalidLastSoldDateRange(final String startDate, final String endDate) {
		try {
			final LocalDate start = LocalDate.parse(startDate);
			final LocalDate end = LocalDate.parse(endDate);

			final Product lastSoldDateValidationProduct = new Product();
			lastSoldDateValidationProduct.setLastSold(start);
			lastSoldDateValidationProduct.setLastSold(end);

			if (start.isAfter(end)) {
				throw new IllegalArgumentException("The start date must occur earlier or at the same date as the end date.");
			}
		} catch (DateTimeParseException e) {
			throw new ClientInputInvalidException("The date must be in the format YYYY-MM-DD.");
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

}

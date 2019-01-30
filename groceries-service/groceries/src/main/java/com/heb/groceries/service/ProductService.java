package com.heb.groceries.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.heb.groceries.dao.ProductDAO;
import com.heb.groceries.dao.ProductDAOMySqlJDBC;
import com.heb.groceries.exception.ClientInputInvalidException;
import com.heb.groceries.exception.DataNotFoundException;
import com.heb.groceries.model.Product;

public class ProductService {

	public List<Product> getAllProducts() {
		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		final List<Product> allProducts = productDAO.listAllProducts();

		return allProducts;
	}

	public Product getProductWithId(final long id) {
		Product retrievedProduct = null;

		throwClientInputInvalidExceptionIfInvalidId(id);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProduct = productDAO.findProductWithId(id);

		if (retrievedProduct == null) {
			throw new DataNotFoundException("Product with id " + id + " was not found.");
		}

		return retrievedProduct;
	}

	public List<Product> findProductsWithId(final String id) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidId(id);

		final Long idAsLong = Long.parseLong(id);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProducts = Arrays.asList(productDAO.findProductWithId(idAsLong));

		return retrievedProducts;
	}

	public List<Product> findProductsWithDescription(final String description) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidDescription(description);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProducts = productDAO.findProductsWithDescription(description);

		return retrievedProducts;
	}

	public List<Product> findProductsWithDepartment(final String department) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidDepartment(department);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProducts = productDAO.findProductsWithDepartment(department);

		return retrievedProducts;
	}

	public List<Product> findProductsWithShelfLifeDays(final String minimum, final String maximum) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidShelfLifeRange(minimum, maximum);

		final Integer min = Integer.parseInt(minimum);
		final Integer max = Integer.parseInt(maximum);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProducts = productDAO.findProductsWithShelfLife(min, max);

		return retrievedProducts;
	}

	public List<Product> findProductsWithUnit(final String unit) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidUnit(unit);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProducts = productDAO.findProductsWithUnit(unit);

		return retrievedProducts;
	}

	public List<Product> findProductsWithXFor(final String minimum, final String maximum) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidXForRange(minimum, maximum);

		final Integer min = Integer.parseInt(minimum);
		final Integer max = Integer.parseInt(maximum);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProducts = productDAO.findProductsWithXFor(min, max);

		return retrievedProducts;
	}

	public List<Product> findProductsWithPrice(final String minimum, final String maximum) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidPriceRange(minimum, maximum);

		final BigDecimal min = new BigDecimal(minimum);
		final BigDecimal max = new BigDecimal(maximum);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProducts = productDAO.findProductsWithPrice(min, max);

		return retrievedProducts;
	}

	public List<Product> findProductsWithCost(final String minimum, final String maximum) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidCostRange(minimum, maximum);

		final BigDecimal min = new BigDecimal(minimum);
		final BigDecimal max = new BigDecimal(maximum);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProducts = productDAO.findProductsWithCost(min, max);

		return retrievedProducts;
	}

	public List<Product> findProductsWithLastSoldDate(final String startDate, final String endDate) {
		List<Product> retrievedProducts = new ArrayList<>();

		throwClientInputInvalidExceptionIfInvalidLastSoldDateRange(startDate, endDate);

		final LocalDate start = LocalDate.parse(startDate);
		final LocalDate end = LocalDate.parse(endDate);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProducts = productDAO.findProductsWithLastSoldDate(start, end);

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

package com.heb.groceries.service;

import java.util.ArrayList;
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

	public Product findProductWithId(final long id) {
		Product retrievedProduct = null;

		throwClientInputInvalidExceptionIfInvalidId(id);

		final ProductDAO productDAO = new ProductDAOMySqlJDBC();
		retrievedProduct = productDAO.findProductWithId(id);

		if (retrievedProduct == null) {
			throw new DataNotFoundException("Product with id " + id + " was not found.");
		}

		return retrievedProduct;
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

	private void throwClientInputInvalidExceptionIfInvalidId(final long id) {
		try {
			final Product idValidationProduct = new Product();
			idValidationProduct.setId(id);
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

}

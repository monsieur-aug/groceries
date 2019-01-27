package com.heb.groceries.service;

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

	private void throwClientInputInvalidExceptionIfInvalidId(final long id) {
		try {
			final Product idValidationProduct = new Product();
			idValidationProduct.setId(id);
		} catch (IllegalArgumentException iae) {
			throw new ClientInputInvalidException(iae.getMessage());
		}
	}

}

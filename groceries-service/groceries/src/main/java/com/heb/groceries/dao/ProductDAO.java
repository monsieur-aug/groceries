package com.heb.groceries.dao;

import java.util.List;

import com.heb.groceries.dao.exception.DAOException;
import com.heb.groceries.model.Product;

public interface ProductDAO {

	public List<Product> listAllProducts() throws DAOException;

	public Product findProductWithId(Long id) throws DAOException;

}

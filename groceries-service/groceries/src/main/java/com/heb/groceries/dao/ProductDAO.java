package com.heb.groceries.dao;

import java.math.BigDecimal;
import java.util.List;

import com.heb.groceries.dao.exception.DAOException;
import com.heb.groceries.model.Product;

public interface ProductDAO {

	public List<Product> listAllProducts() throws DAOException;

	public Product findProductWithId(Long id) throws DAOException;

	public List<Product> findProductsWithDescription(String description) throws DAOException;

	public List<Product> findProductsWithDepartment(String department) throws DAOException;

	public List<Product> findProductsWithShelfLife(Integer min, Integer max) throws DAOException;

	public List<Product> findProductsWithUnit(String unit) throws DAOException;

	public List<Product> findProductsWithXFor(Integer min, Integer max) throws DAOException;

	public List<Product> findProductsWithPrice(BigDecimal min, BigDecimal max) throws DAOException;

	public List<Product> findProductsWithCost(BigDecimal min, BigDecimal max) throws DAOException;

}

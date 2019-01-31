package com.heb.groceries.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.heb.groceries.dao.exception.DAOException;
import com.heb.groceries.model.Product;

/**
 * Represents the behaviors of any data access object (DAO) that specializes in
 * accessing <code>Product</code>s from a datastore.
 */
public interface ProductDAO {

	/**
	 * @return a list of all products in the datastore
	 * @throws DAOException if an error occurred while retrieving the products
	 */
	public List<Product> listAllProducts() throws DAOException;

	/**
	 * @param id the product id
	 * @return a product with the specified id
	 * @throws DAOException if an error occurred while retrieving the product
	 */
	public Product findProductWithId(Long id) throws DAOException;

	/**
	 * @param description the product description
	 * @return a list of products that have the specified description
	 * @throws DAOException if an error occurred while retrieving the products
	 */
	public List<Product> findProductsWithDescription(String description) throws DAOException;

	/**
	 * @param department the product department
	 * @return a list of products that have the specified department
	 * @throws DAOException if an error occurred while retrieving the products
	 */
	public List<Product> findProductsWithDepartment(String department) throws DAOException;

	/**
	 * @param min the minimum shelf life
	 * @param max the maximum shelf life
	 * @return a list of products that have a shelf life within the specified range
	 * @throws DAOException if an error occurred while retrieving the products
	 */
	public List<Product> findProductsWithShelfLife(Integer min, Integer max) throws DAOException;

	/**
	 * @param unit the product unit
	 * @return a list of products that have the specified unit
	 * @throws DAOException if an error occurred while retrieving the products
	 */
	public List<Product> findProductsWithUnit(String unit) throws DAOException;

	/**
	 * @param min the minimum xFor factor
	 * @param max the maximum xFor factor
	 * @return a list of products that have an xFor factor within the specified range
	 * @throws DAOException if an error occurred while retrieving the products
	 */
	public List<Product> findProductsWithXFor(Integer min, Integer max) throws DAOException;

	/**
	 * @param min the minimum price
	 * @param max the maximum price
	 * @return a list of products that have a price within the specified range
	 * @throws DAOException if an error occurred while retrieving the products
	 */
	public List<Product> findProductsWithPrice(BigDecimal min, BigDecimal max) throws DAOException;

	/**
	 * @param min the minimum cost
	 * @param max the maximum cost
	 * @return a list of products that have a cost within the specified range
	 * @throws DAOException if an error occurred while retrieving the products
	 */
	public List<Product> findProductsWithCost(BigDecimal min, BigDecimal max) throws DAOException;

	/**
	 * @param startDate the start of the last sold date range
	 * @param endDate the end of the last sold date range
	 * @return a list of products that have a last sold date within the specified range
	 * @throws DAOException if an error occurred while retrieving the products
	 */
	public List<Product> findProductsWithLastSoldDate(LocalDate startDate, LocalDate endDate) throws DAOException;

}

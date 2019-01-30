package com.heb.groceries.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.heb.groceries.dao.exception.DAOException;
import com.heb.groceries.model.Product;
import com.heb.groceries.model.ProductBuilder;

public class ProductDAOJDBC implements ProductDAO {

	private static final String	SQL_LIST_ALL				= "SELECT id, description, last_sold_date, shelf_life_days, department, price, unit, xfor, cost FROM Product";
	private static final String	SQL_FIND_BY_ID				= SQL_LIST_ALL + " WHERE id = ?";
	private static final String	SQL_FIND_BY_DESCRIPTION		= SQL_LIST_ALL + " WHERE description LIKE ?";
	private static final String	SQL_FIND_BY_DEPARTMENT		= SQL_LIST_ALL + " WHERE department LIKE ?";
	private static final String	SQL_FIND_BY_SHELF_LIFE		= SQL_LIST_ALL + " WHERE shelf_life_days >= ? AND shelf_life_days <= ?";
	private static final String	SQL_FIND_BY_UNIT			= SQL_LIST_ALL + " WHERE unit LIKE ?";
	private static final String	SQL_FIND_BY_XFOR			= SQL_LIST_ALL + " WHERE xfor >= ? AND xfor <= ?";
	private static final String	SQL_FIND_BY_PRICE			= SQL_LIST_ALL + " WHERE price >= ? AND price <= ?";
	private static final String	SQL_FIND_BY_COST			= SQL_LIST_ALL + " WHERE cost >= ? AND cost <= ?";
	private static final String	SQL_FIND_BY_LAST_SOLD_DATE	= SQL_LIST_ALL + " WHERE last_sold_date >= ? AND last_sold_date <= ?";

	private DAOFactory			daoFactory;

	public ProductDAOJDBC(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Product> listAllProducts() throws DAOException {
		final List<Product> allProducts = new ArrayList<>();

		try (final Connection connection = this.daoFactory.getConnection();
						final PreparedStatement statement = connection.prepareStatement(SQL_LIST_ALL);
						final ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				allProducts.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return allProducts;
	}

	@Override
	public Product findProductWithId(Long id) throws DAOException {
		return find(SQL_FIND_BY_ID, id);
	}

	@Override
	public List<Product> findProductsWithDescription(String description) throws DAOException {
		return list(SQL_FIND_BY_DESCRIPTION, "%" + description + "%");
	}

	@Override
	public List<Product> findProductsWithDepartment(String department) throws DAOException {
		return list(SQL_FIND_BY_DEPARTMENT, department);
	}

	@Override
	public List<Product> findProductsWithShelfLife(Integer min, Integer max) throws DAOException {
		final Object[] shelfLifeRange = { min, max };
		return list(SQL_FIND_BY_SHELF_LIFE, shelfLifeRange);
	}

	@Override
	public List<Product> findProductsWithUnit(String unit) throws DAOException {
		return list(SQL_FIND_BY_UNIT, unit);
	}

	@Override
	public List<Product> findProductsWithXFor(Integer min, Integer max) throws DAOException {
		final Object[] xForRange = { min, max };
		return list(SQL_FIND_BY_XFOR, xForRange);
	}

	@Override
	public List<Product> findProductsWithPrice(BigDecimal min, BigDecimal max) throws DAOException {
		final Object[] priceRange = { min, max };
		return list(SQL_FIND_BY_PRICE, priceRange);
	}

	@Override
	public List<Product> findProductsWithCost(BigDecimal min, BigDecimal max) throws DAOException {
		final Object[] costRange = { min, max };
		return list(SQL_FIND_BY_COST, costRange);
	}

	@Override
	public List<Product> findProductsWithLastSoldDate(LocalDate startDate, LocalDate endDate) throws DAOException {
		final Object[] dateRange = { startDate, endDate };
		return list(SQL_FIND_BY_LAST_SOLD_DATE, dateRange);
	}

	private Product find(final String sqlQuery, Object... values) throws DAOException {
		Product product = null;

		try (final Connection connection = this.daoFactory.getConnection();
						final PreparedStatement statement = prepareStatementWithValues(connection, sqlQuery, values);
						final ResultSet resultSet = statement.executeQuery();) {
			if (resultSet.next()) {
				product = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return product;
	}

	private List<Product> list(final String sql, Object... values) {
		final List<Product> products = new ArrayList<>();

		try (final Connection connection = this.daoFactory.getConnection();
						final PreparedStatement statement = prepareStatementWithValues(connection, sql, values);
						final ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				products.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}

		return products;
	}

	private PreparedStatement prepareStatementWithValues(final Connection connection, final String sqlQuery, Object... values) throws SQLException {
		final PreparedStatement statement = connection.prepareStatement(sqlQuery);
		setValuesInStatement(statement, values);

		return statement;
	}

	private void setValuesInStatement(final PreparedStatement statement, Object... values) throws SQLException {
		for (int i = 0; i < values.length; i++) {
			statement.setObject(i + 1, values[i]);
		}
	}

	private static Product map(ResultSet resultSet) throws SQLException {
		final ProductBuilder builder = new ProductBuilder();
		builder.id(resultSet.getLong("id"));
		builder.description(resultSet.getString("description"));
		builder.lastSold(LocalDate.from(resultSet.getDate("last_sold_date").toLocalDate()));
		builder.shelfLifeDays(resultSet.getInt("shelf_life_days"));
		builder.department(resultSet.getString("department"));
		builder.price(resultSet.getBigDecimal("price"));
		builder.unit(resultSet.getString("unit"));
		builder.xFor(resultSet.getInt("xfor"));
		builder.cost(resultSet.getBigDecimal("cost"));

		return builder.build();
	}

}

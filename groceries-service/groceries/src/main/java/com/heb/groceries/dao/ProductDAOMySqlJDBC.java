package com.heb.groceries.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.heb.groceries.dao.exception.DAOConfigurationException;
import com.heb.groceries.dao.exception.DAOException;
import com.heb.groceries.model.Product;
import com.heb.groceries.model.ProductBuilder;

public class ProductDAOMySqlJDBC implements ProductDAO {

	private static final String	MYSQL_JDBC_URL_PREFIX				= "jdbc:mysql:";
	private static final String	MYSQL_JDBC_DRIVER_QUALIFIED_NAME	= "com.mysql.cj.jdbc.Driver";

	private static final String	DEFAULT_DATABASE_NAME				= "groceriesdb";
	private static final String	DEFAULT_PRODUCT_TABLE_NAME			= "Product";
	private static final String	DEFAULT_DATABASE_URL				= MYSQL_JDBC_URL_PREFIX + "//localhost:3306/" + DEFAULT_DATABASE_NAME + "?useSSL=false";
	private static final String	DEFAULT_CREDENTIALS_FILEPATH		= System.getProperty("user.home") + "/groceriesdb_creds.txt";
	private static final String	DEFAULT_PRODUCTS_INVENTORY_FILEPATH	= System.getProperty("user.home") + "/products_inventory.txt";
	private static final String	FIELD_SEPARATOR						= ",";

	private static final String	SQL_LIST_ALL						= "SELECT id, description, last_sold_date, shelf_life_days, department, price, unit, xfor, cost FROM Product";
	private static final String	SQL_FIND_BY_ID						= SQL_LIST_ALL + " WHERE id = ?";
	private static final String	SQL_FIND_BY_DESCRIPTION				= SQL_LIST_ALL + " WHERE description LIKE ?";
	private static final String	SQL_FIND_BY_DEPARTMENT				= SQL_LIST_ALL + " WHERE department LIKE ?";
	private static final String	SQL_FIND_BY_SHELF_LIFE				= SQL_LIST_ALL + " WHERE shelf_life_days >= ? AND shelf_life_days <= ?";
	private static final String	SQL_FIND_BY_UNIT					= SQL_LIST_ALL + " WHERE unit LIKE ?";
	private static final String	SQL_FIND_BY_XFOR					= SQL_LIST_ALL + " WHERE xfor >= ? AND xfor <= ?";
	private static final String	SQL_FIND_BY_PRICE					= SQL_LIST_ALL + " WHERE price >= ? AND price <= ?";
	private static final String	SQL_FIND_BY_COST					= SQL_LIST_ALL + " WHERE cost >= ? AND cost <= ?";

	private String databaseUrl;
	private File credentialsFile;
	
	public ProductDAOMySqlJDBC() {
		this(DEFAULT_DATABASE_URL, DEFAULT_CREDENTIALS_FILEPATH);
	}
	
	public ProductDAOMySqlJDBC(final String databaseUrl, final String credentialsFilepath) {
		setDatabaseUrl(databaseUrl);
		setCredentialsFilepath(credentialsFilepath);
	}
	
	public void initialize() {
		verifyDriverExists();
		verifyDatabaseExists();
		removeExistingInventory();
		createNewInventoryTable();
		populateInventory();
	}
	
	public String getDatabaseUrl() {
		return this.databaseUrl;
	}

	public String getCredentialsFilepath() {
		return this.credentialsFile.getAbsolutePath();
	}

	@Override
	public List<Product> listAllProducts() throws DAOException {
		final List<Product> allProducts = new ArrayList<>();
	
		try (
			final Connection connection = openConnection();
			final PreparedStatement statement = connection.prepareStatement(SQL_LIST_ALL);
			final ResultSet resultSet = statement.executeQuery();
		) {
			while (resultSet.next()) {
				allProducts.add(map(resultSet));
			}
		} catch (IOException | SQLException e) {
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

	private void verifyDriverExists() {
		try {
			Class.forName(MYSQL_JDBC_DRIVER_QUALIFIED_NAME);
		} catch (ClassNotFoundException cnfe) {
			throw new DAOConfigurationException((Throwable) cnfe);
		}
	}
	
	private void verifyDatabaseExists() {
		Connection connection = null;
		
		try {
			connection = openConnection();
		} catch (SQLException | IOException e) {
			throw new DAOConfigurationException(e);
		} finally {
			closeConnection(connection);
		}
	}
	
	private Connection openConnection() throws SQLException, IOException {
		verifyDriverExists();
		
		final String username = readDatabaseUsername();
		final String password = readDatabasePassword();
		
		final Connection newConnection = DriverManager.getConnection(this.databaseUrl, username, password);
		
		return newConnection;
	}
	
	private void closeConnection(final Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	private String readDatabaseUsername() throws FileNotFoundException, IOException {
		String username = StringUtils.EMPTY;
	
		try (BufferedReader reader = new BufferedReader(new FileReader(this.credentialsFile))) {
			String nextLine;
	
			while ((nextLine = reader.readLine()) != null) {
				if (StringUtils.isBlank(nextLine)) {
					continue;
				}
	
				username = StringUtils.split(nextLine, ",")[0];
			}
		}
	
		return username;
	}

	private String readDatabasePassword() throws FileNotFoundException, IOException {
		String password = StringUtils.EMPTY;
	
		try (BufferedReader reader = new BufferedReader(new FileReader(this.credentialsFile))) {
			String nextLine;
	
			while ((nextLine = reader.readLine()) != null) {
				if (StringUtils.isBlank(nextLine)) {
					continue;
				}
	
				password = StringUtils.split(nextLine, ",")[1];
			}
		}
	
		return password;
	}

	private void removeExistingInventory() {
		Connection connection = null;
		Statement statement = null;
		final String dropInventoryCommand = "DROP TABLE " + DEFAULT_PRODUCT_TABLE_NAME;
		
		try {
			connection = openConnection();
			statement = connection.createStatement();
			statement.execute(dropInventoryCommand);
		} catch (IOException | SQLException e) {
			throw new DAOConfigurationException(e);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}
	
	private void closeStatement(final Statement statement) {
		try {
			statement.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private void createNewInventoryTable() {
		Connection connection = null;
		Statement statement = null;
		final String createInventoryCommand = "CREATE TABLE " + DEFAULT_PRODUCT_TABLE_NAME 
						+ " (id BIGINT UNSIGNED NOT NULL,"
						+ "description VARCHAR(32) NOT NULL,"
						+ "last_sold_date DATE NOT NULL,"
						+ "shelf_life_days INT NOT NULL,"
						+ "department VARCHAR(32) NOT NULL,"
						+ "price DOUBLE NOT NULL,"
						+ "unit VARCHAR(16) NOT NULL,"
						+ "xfor INT NOT NULL,"
						+ "cost DOUBLE NOT NULL)";
		
		try {
			connection = openConnection();
			statement = connection.createStatement();
			statement.execute(createInventoryCommand);
		} catch (IOException | SQLException e) {
			throw new DAOConfigurationException(e);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}
	
	private void populateInventory() {
		Connection connection = null;
		PreparedStatement insertStatement = null;
		final String insertTemplate = "INSERT INTO " + DEFAULT_PRODUCT_TABLE_NAME 
						+ " (id, description, last_sold_date, shelf_life_days, department, price, unit, xfor, cost)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = openConnection();
			
			final File inventoryDataFile = new File(DEFAULT_PRODUCTS_INVENTORY_FILEPATH);
			final BufferedReader reader = new BufferedReader(new FileReader(inventoryDataFile));
			
			String nextLine = null;
			List<String> productFields = new ArrayList<>();
			int totalRowsAffected = 0;
			
			while ((nextLine = reader.readLine()) != null) {
				if (StringUtils.isBlank(nextLine) || StringUtils.startsWith(nextLine, "ID")) {
					continue;
				}
				
				extractProductFields(nextLine, productFields);
				
				insertStatement = connection.prepareStatement(insertTemplate);
				insertStatement.setInt(1, Integer.parseInt(productFields.get(0)));
				insertStatement.setString(2, productFields.get(1));
				String[] dateFields = StringUtils.split(productFields.get(2), "/");
				insertStatement.setDate(3, Date.valueOf(LocalDate.of(Integer.parseInt(dateFields[2]), Integer.parseInt(dateFields[0]), Integer.parseInt(dateFields[1]))));
				insertStatement.setInt(4, Integer.parseInt(productFields.get(3).substring(0, productFields.get(3).indexOf("d"))));
				insertStatement.setString(5, productFields.get(4));
				insertStatement.setDouble(6, Double.parseDouble(StringUtils.split(productFields.get(5), "$")[0]));
				insertStatement.setString(7, productFields.get(6));
				insertStatement.setInt(8, Integer.parseInt(productFields.get(7)));
				insertStatement.setDouble(9, Double.parseDouble(StringUtils.split(productFields.get(8), "$")[0]));

				totalRowsAffected += insertStatement.executeUpdate();
			}

			reader.close();
			System.out.println("Total rows affected: " + totalRowsAffected);
		} catch (IOException | SQLException e) {
			throw new DAOConfigurationException(e);
		} finally {
		}
	}
	
	private void extractProductFields(final String line, final List<String> fields) {
		String[] parsedFields = StringUtils.split(line, FIELD_SEPARATOR);
		
		fields.clear();
		
		for (int i = 0; i < parsedFields.length; i++) {
			fields.add(StringUtils.trim(parsedFields[i]));
		}
	}

	private void setDatabaseUrl(final String url) {
		if (StringUtils.isBlank(url)) {
			throw new DAOConfigurationException("The database URL may not be null, blank, or empty.");
		}
		
		if (isNotSupportedDatabaseUrl(url)) {
			throw new DAOConfigurationException("The database URL must be in a form similar to " + MYSQL_JDBC_DRIVER_QUALIFIED_NAME + "//localhost:3306/<database_name>");
		}
		
		this.databaseUrl = url;
	}
	
	private void setCredentialsFilepath(final String filepath) {
		if (StringUtils.isBlank(filepath)) {
			throw new DAOConfigurationException("The credentials filepath may not be null, blank, or empty.");
		}
		
		final File file = new File(filepath);
		
		if (!file.exists() || !file.isFile() || !file.canRead()) {
			throw new DAOConfigurationException("An error occurred while trying to read the file: " + filepath);
		}
		
		this.credentialsFile = file;
	}
	
	private boolean isNotSupportedDatabaseUrl(final String url) {
		return !(StringUtils.startsWith(url, MYSQL_JDBC_URL_PREFIX));
	}
	
	private Product find(final String sqlQuery, Object... values) throws DAOException {
		Product product = null;

		try (
			final Connection connection = openConnection();
			final PreparedStatement statement = prepareStatementWithValues(connection, sqlQuery, values);
			final ResultSet resultSet = statement.executeQuery();
		) {
			if (resultSet.next()) {
				product = map(resultSet);
			}
		} catch (IOException | SQLException e) {
			throw new DAOException(e);
		}

		return product;
	}

	private List<Product> list(final String sql, Object... values) {
		final List<Product> products = new ArrayList<>();

		try (
			final Connection connection = openConnection();
			final PreparedStatement statement = prepareStatementWithValues(connection, sql, values);
			final ResultSet resultSet = statement.executeQuery();
		) {
			while (resultSet.next()) {
				products.add(map(resultSet));
			}
		} catch (IOException | SQLException e) {
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

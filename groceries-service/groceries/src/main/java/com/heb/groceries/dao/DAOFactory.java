package com.heb.groceries.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.heb.groceries.dao.exception.DAOConfigurationException;

/**
 * A factory for data access objects (DAOs). This class provides DAOs based on
 * the properties retrieved from the properties file. The properties are
 * retrieved according to the specified dbName, which is used as a prefix for
 * each property key.
 * 
 * @see com.heb.groceries.dao.DAOProperties
 */
public abstract class DAOFactory {

	private static final String	PROPERTY_URL		= "url";
	private static final String	PROPERTY_DRIVER		= "driver";
	private static final String	PROPERTY_USERNAME	= "username";
	private static final String	PROPERTY_PASSWORD	= "password";

	public static DAOFactory getInstance(String dbName) throws DAOConfigurationException {
		if (StringUtils.isBlank(dbName)) {
			throw new DAOConfigurationException("The database name may not be null, blank, or empty.");
		}

		final DAOProperties properties = new DAOProperties(dbName);
		final String url = properties.getProperty(PROPERTY_URL);
		final String driverClassName = properties.getProperty(PROPERTY_DRIVER);
		final String username = properties.getProperty(PROPERTY_USERNAME);
		final String password = properties.getProperty(PROPERTY_PASSWORD);

		DAOFactory newInstance;

		if (driverClassName != null) {
			try {
				Class.forName(driverClassName);
			} catch (ClassNotFoundException cnfe) {
				throw new DAOConfigurationException("The driver class (" + driverClassName + ") is missing from the classpath.", cnfe);
			}

			newInstance = new DriverManagerDAOFactory(url, username, password);
		} else {
			throw new DAOConfigurationException("A driver classname was not specified in the properties file.");
		}

		return newInstance;
	}

	/**
	 * @return a connection to the database
	 * @throws SQLException if a database connection error occurs
	 */
	abstract Connection getConnection() throws SQLException;

	public ProductDAO getProductDAO() {
		return new ProductDAOJDBC(this);
	}

	// Other DAOs may be specified here similar to the one above
}

/**
 * Provides a connection to the database using the supplied URL, username, and
 * password.
 */
class DriverManagerDAOFactory extends DAOFactory {
	private String	url;
	private String	username;
	private String	password;

	DriverManagerDAOFactory(final String url, final String username, final String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Override
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}

package com.heb.groceries.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.heb.groceries.dao.exception.DAOConfigurationException;

/**
 * Reads the datastore properties from the specified file using the supplied key
 * prefix. Entries in the file look similar to:
 * 
 * <pre>
 * groceriesdb.jdbc.driver = com.mysql.cj.jdbc.Driver
 * </pre>
 * 
 * where groceriesdb.jdbc represents the key prefix that must be supplied by the
 * dependent class.
 * 
 */
public class DAOProperties {

	private static final String		PROPERTIES_FILENAME	= "dao.properties";
	private static final String		PROPERTIES_FILEPATH	= System.getProperty("user.home") + "/" + PROPERTIES_FILENAME;
	private static final Properties	PROPERTIES			= new Properties();

	static {
		final File file = new File(PROPERTIES_FILEPATH);

		if (!file.exists() || !file.isFile() || !file.canRead()) {
			throw new DAOConfigurationException("An error occurred while trying to read properties file: " + PROPERTIES_FILEPATH);
		}

		try {
			final BufferedReader reader = new BufferedReader(new FileReader(file));
			PROPERTIES.load(reader);
		} catch (FileNotFoundException fnfe) {
			throw new DAOConfigurationException("An error occurred while trying to read properties file: " + PROPERTIES_FILEPATH, fnfe);
		} catch (IOException ioe) {
			throw new DAOConfigurationException("An error occurred while trying to load properties file: " + PROPERTIES_FILEPATH, ioe);
		}
	}

	private String keyPrefix;

	/**
	 * Creates a new instance of a datastore properties object using the specified
	 * key prefix
	 * 
	 * @param keyPrefix the prefix which identifies the properties for the DAO of
	 *                  interest
	 * @throws DAOConfigurationException if the specified prefix is null, blank, or
	 *                                   empty
	 */
	public DAOProperties(final String keyPrefix) throws DAOConfigurationException {
		if (StringUtils.isBlank(keyPrefix)) {
			throw new DAOConfigurationException("The key prefix may not be null, blank, or empty.");
		}

		this.keyPrefix = keyPrefix;
	}

	/**
	 * Reads the requested property from the properties file
	 * 
	 * @param propertyName the name of the property to read
	 * @return the value associated with the named property in the properties file
	 */
	public String getProperty(final String propertyName) {
		final String fullPropertyKey = keyPrefix + "." + propertyName;
		String propertyValue = PROPERTIES.getProperty(fullPropertyKey);

		if (StringUtils.isBlank(propertyValue)) {
			propertyValue = null;
		}

		return propertyValue;
	}
}

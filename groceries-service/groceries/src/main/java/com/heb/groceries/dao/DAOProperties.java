package com.heb.groceries.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.heb.groceries.dao.exception.DAOConfigurationException;

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

	public DAOProperties(final String keyPrefix) throws DAOConfigurationException {
		if (StringUtils.isBlank(keyPrefix)) {
			throw new DAOConfigurationException("The key prefix may not be null, blank, or empty.");
		}

		this.keyPrefix = keyPrefix;
	}

	public String getProperty(final String propertyName) throws DAOConfigurationException {
		final String fullPropertyKey = keyPrefix + "." + propertyName;
		String propertyValue = PROPERTIES.getProperty(fullPropertyKey);

		if (StringUtils.isBlank(propertyValue)) {
			propertyValue = null;
		}

		return propertyValue;
	}
}

package com.heb.groceries.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.heb.groceries.dao.exception.DAOConfigurationException;
import com.heb.groceries.dao.exception.DAOException;

public class GroceriesDBInitializer {

	private static final String PRODUCT_TABLE_NAME = "Product";
	private static final String SQL_DROP_PRODUCT_TABLE = "DROP TABLE " + PRODUCT_TABLE_NAME;
	private static final String SQL_CREATE_PRODUCT_TABLE = "CREATE TABLE " + PRODUCT_TABLE_NAME
					+ " (id BIGINT UNSIGNED NOT NULL, " 
					+ "description VARCHAR(32) NOT NULL,"
					+ "last_sold_date DATE NOT NULL,"
					+ "shelf_life_days INT NOT NULL,"
					+ "department VARCHAR(32) NOT NULL,"
					+ "price DOUBLE NOT NULL,"
					+ "unit VARCHAR(16) NOT NULL,"
					+ "xfor INT NOT NULL,"
					+ "cost DOUBLE NOT NULL)";
	private static final String SQL_TEMPLATE_POPULATE_PRODUCT_TABLE = "INSERT INTO " + PRODUCT_TABLE_NAME 
					+ " (id, description, last_sold_date, shelf_life_days, department, price, unit, xfor, cost)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String	DEFAULT_PRODUCTS_INVENTORY_FILEPATH	= System.getProperty("user.home") + "/products_inventory.txt";
	private static final String	FIELD_SEPARATOR	= ",";
	
	public static void initialize(final String databaseName) {
		final DAOFactory groceriesDb = DAOFactory.getInstance(databaseName);
		Connection conn;
		
		try {
			conn = groceriesDb.getConnection();
		} catch (SQLException sqle) {
			throw new DAOConfigurationException("The Groceries database doesn't exist. Please create it first!", sqle);
		}
		
		deleteProductTable(conn);
		createProductTable(conn);
		populateProductTable(conn);
	}

	private static void deleteProductTable(final Connection conn) {
		try {
			PreparedStatement statement = conn.prepareStatement(SQL_DROP_PRODUCT_TABLE);
			statement.execute();
		} catch (SQLException sqle) {
			/*
			 * This is OK. If the table doesn't exist, we will create it anyway. If some
			 * other error occurred then throw an new exception.
			 */
			if (sqle.getMessage().contains("Unknown table")) {
				return;
			}

			throw new DAOException(sqle);
		}
	}

	private static void createProductTable(final Connection conn) {
		try {
			PreparedStatement statement = conn.prepareStatement(SQL_CREATE_PRODUCT_TABLE);
			statement.execute();
		} catch (SQLException sqle) {
			throw new DAOException(sqle);
		}
	}

	private static void populateProductTable(final Connection conn) {
		try {
			final File inventoryDataFile = new File(DEFAULT_PRODUCTS_INVENTORY_FILEPATH);
			final BufferedReader reader = new BufferedReader(new FileReader(inventoryDataFile));
			
			String nextLine = null;
			List<String> productFields = new ArrayList<>();
			int totalRowsAffected = 0;
			
			PreparedStatement insertStatement = null;
			
			while ((nextLine = reader.readLine()) != null) {
				if (StringUtils.isBlank(nextLine) || StringUtils.startsWith(nextLine, "ID")) {
					continue;
				}
				
				extractProductFields(nextLine, productFields);
				
				insertStatement = conn.prepareStatement(SQL_TEMPLATE_POPULATE_PRODUCT_TABLE);
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
		} catch (Exception e) {
			
		}
	}
		
	private static void extractProductFields(final String line, final List<String> fields) {
		String[] parsedFields = StringUtils.split(line, FIELD_SEPARATOR);
		
		fields.clear();
		
		for (int i = 0; i < parsedFields.length; i++) {
			fields.add(StringUtils.trim(parsedFields[i]));
		}
	}
}

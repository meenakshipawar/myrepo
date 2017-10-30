package com.igenico.assignment.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Meenakshi
 * 
 */
public class TransactionManager {

	/**
	 * Instantiate the Logger
	 */
	private static final Log logger = LogFactory
			.getLog(TransactionManager.class);

	/**
	 * This method gets database connection
	 * 
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		} catch (ClassNotFoundException classnotexception) {
			logger.debug(classnotexception.getMessage(), classnotexception);
			logger.error(classnotexception.getMessage(), classnotexception);

		} catch (SQLException sqlexception) {
			if (logger.isDebugEnabled()) {
				logger.debug(sqlexception.getMessage(), sqlexception);
				logger.error(sqlexception.getMessage(), sqlexception);
			}
		}

		if (null != conn) {
			logger.info("connection done");
		}
		return conn;

	}

}

package com.yidigun.springquickstart.biz.common;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcUtils {
	
	private static Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.h2.Driver");
			return DriverManager.getConnection("jdbc:h2:/~/test", "sa", "");
		}
		catch (ClassNotFoundException e) {
			logger.error("JDBC 드라이버 망함", e);
			throw new SQLException(e.getMessage(), e);
		}
		catch (Exception e) {
			logger.error("DB 접속망함", e);
			throw e;
		}
	}
	
	public static void close(Object ... args) {
		if (args == null || args.length == 0)
			return;

		for (Object o: args) {
			if (o == null)
				continue;
			else if (o instanceof Connection) {
				try {
					((Connection) o).close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
			else if (o instanceof PreparedStatement) {
				try {
					((PreparedStatement) o).close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
			else if (o instanceof ResultSet) {
				try {
					((ResultSet) o).close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
			else if (o instanceof Closeable) {
				try {
					((Closeable) o).close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
}

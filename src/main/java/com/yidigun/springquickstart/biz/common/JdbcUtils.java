package com.yidigun.springquickstart.biz.common;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

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

	public static void bindParameters(PreparedStatement stmt, Object... params) throws SQLException {
	
		for (int i = 0; i < params.length; i++) {
			Object param = params[i];
			
			if (param == null) {
				stmt.setNull(i + 1, java.sql.Types.NULL);
			}
			else if (param instanceof java.util.Date) {
				stmt.setDate(i + 1, new java.sql.Date(((java.util.Date)param).getTime()));
			}
			else if (param instanceof java.sql.Date) {
				stmt.setDate(i + 1, (java.sql.Date)param);
			}
			else if (param instanceof Time) {
				stmt.setTime(i + 1, (Time)param);
			}
			else if (param instanceof Timestamp) {
				stmt.setTimestamp(i + 1, (Timestamp)param);
			}
			else if (param instanceof Float) {
				stmt.setFloat(i + 1, (Float)param);
			}
			else if (param instanceof Double) {
				stmt.setDouble(i + 1, (Double)param);
			}
			else if (param instanceof Boolean) {
				stmt.setBoolean(i + 1, (Boolean)param);
			}
			else if (param instanceof BigDecimal) {
				stmt.setBigDecimal(i + 1, (BigDecimal)param);
			}
			else if (param instanceof Long) {
				stmt.setLong(i + 1, (Long)param);
			}
			else if (param instanceof Integer) {
				stmt.setInt(i + 1, (Integer)param);
			}
			else if (param instanceof String) {
				stmt.setString(i + 1, (String)param);
			}
			else {
				stmt.setString(i + 1, param.toString());
			}
		}
	}
}

package com.yidigun.springquickstart.biz.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract public class AbstractDAO<T> {
	
	abstract protected T mapObject(ResultSet rs) throws SQLException;
	
	protected int executeStatement(String sql, Object... params) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = JdbcUtils.getConnection();
			stmt = conn.prepareStatement(sql);

			JdbcUtils.bindParameters(stmt, params);
			return stmt.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			JdbcUtils.close(conn, stmt);
		}
	}
	
	protected <V> V getValue(String sql, Class<V> type, Object... params) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			stmt = conn.prepareStatement(sql);

			JdbcUtils.bindParameters(stmt, params);
			rs = stmt.executeQuery();

			if (rs.next())
				return rs.getObject(1, type);
			else
				return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			JdbcUtils.close(conn, stmt, rs);
		}
	}

	protected T getOne(String sql, Object... params) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			stmt = conn.prepareStatement(sql);

			JdbcUtils.bindParameters(stmt, params);
			rs = stmt.executeQuery();

			if (rs.next())
				return mapObject(rs);
			else
				return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			JdbcUtils.close(conn, stmt, rs);
		}
	}

	protected List<T> getList(String sql, Object... params) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtils.getConnection();
			stmt = conn.prepareStatement(sql);

			JdbcUtils.bindParameters(stmt, params);
			rs = stmt.executeQuery();

			List<T> list = new ArrayList<T>();
			while (rs.next()) {
				list.add(mapObject(rs));
			}

			return list;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			JdbcUtils.close(conn, stmt, rs);
		}
	}

}

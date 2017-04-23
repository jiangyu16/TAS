package com.tas.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/*
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;*/

public class DBPool {
	// private String jndiName = "java:comp/env/jdbc/tas";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Connection getConnection() {
		  String DRIVER;
		  String URL;
		  String USER;
		  String PASSWORD;
			InputStream is = DBPool.class.getResourceAsStream(
			"jdbcinfo.properties");
			Properties pro = new Properties();
			try {
				pro.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			DRIVER = pro.getProperty("driver");
			URL = pro.getProperty("url");
			USER = pro.getProperty("user");
			PASSWORD = pro.getProperty("password");
		 
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);}
		  catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

	/*
	 * public Connection getConnection() { try { InitialContext context = new
	 * InitialContext(); DataSource ds = (DataSource) context.lookup(jndiName);
	 * conn = ds.getConnection(); } catch (NamingException e) {
	 * e.printStackTrace(); } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * return conn; }
	 */
	public void close() {
		try {
			if (rs != null) {//  
				rs.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			if (pstmt != null) {
				pstmt.close();// 
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

	}

	public ResultSet doQueryRS(String sql, Object[] params) throws SQLException {
		conn = this.getConnection();
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i + 1, params[i]);
		}
		rs = pstmt.executeQuery();

		return rs;
	}

	//  
	public int doUpdate(String sql, Object[] params) throws SQLException {
		conn = this.getConnection();
		pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i + 1, params[i]);
		}
		int res = pstmt.executeUpdate();
		 if (pstmt != null) {
			pstmt.close();//  
		}
		if (conn != null)
			conn.close(); 
		return res;
	}
}

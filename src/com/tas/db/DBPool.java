package com.tas.db;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
/*
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;*/

public class DBPool <T>{
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
		System.out.println(sql);
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
	public int doBatch(String sql,Class<T> c,T[] list,Map<Integer,String> methodMap,Map<Integer, Object> paramMap ) throws SQLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int []result = null;
		conn =this.getConnection();
		// conn.setAutoCommit(false); 
		pstmt = conn.prepareStatement(sql);
		for(T bean: list){
			Set<Map.Entry<Integer,String>> mmEntrySet =  methodMap.entrySet();
			Iterator<Map.Entry<Integer,String>> mmit = mmEntrySet.iterator();
			while(mmit.hasNext()){//通过bean的get方法，获取数据注入到sql语句中
				Map.Entry<Integer,String> entry = mmit.next();
			   Method method = c.getMethod(entry.getValue(), new Class[]{});
			   pstmt.setObject(entry.getKey(), method.invoke(bean,new Object[]{} ));
			}
			Set<Map.Entry<Integer, Object>> pmEntrySet =paramMap.entrySet();
			Iterator<Map.Entry<Integer, Object>> pmit = pmEntrySet.iterator();
			while(pmit.hasNext()){
				Map.Entry<Integer,Object> entry = pmit.next();
				pstmt.setObject(entry.getKey(), entry.getValue());
			}
			
			//for(int i=1;i<=methodNames.length;i++){
			//	Method method=c.getMethod(methodNames[i], new Class[]{});
			//	pstmt.setObject(i, method.invoke(bean,new Object[]{} ));
				
				// (c.getMethod(methodNames[i], new Class[]{})).invoke(bean, new Object[]{});
				
			//}
			pstmt.addBatch();
		}
		try{
		result=pstmt.executeBatch();
		}catch(SQLException e){
		//	System.out.println(result.length);
		}
		//conn.commit();
		//conn.setAutoCommit(true);
		// System.out.println("有错误");
		
//		for(int k:result){
//			System.out.println(k);
//		}
		if(result!=null) return result.length;
		else return 0;
		 
	}
}

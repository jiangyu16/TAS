package com.tas.db;

import java.sql.*;

public class TestDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBPool dbp= new DBPool();
		Connection dbConn=dbp.getConnection();
		dbp.close();
		

	/*	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=db_exam";

		String userName = "sa";

		String userPwd = "123";

		try

		{

			Class.forName(driverName);

			Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);

			System.out.println("连接数据库成功");

		}

		catch (Exception e)

		{

			e.printStackTrace();

			System.out.print("连接失败");

		}
*/
	}

}

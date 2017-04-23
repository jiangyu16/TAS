package com.tas.dao.impl;

import java.sql.Connection;
import java.sql.*;

import com.tas.bean.Teacher;
import com.tas.dao.TeacherDao;
import com.tas.db.DBPool;

public class TeacherDaoImpl implements TeacherDao {


	@Override
	public Teacher login(String teacherId, String password) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		//Connection conn=dbpool.getConnection();
		String sql="select * from T_Teacher where teacherId=? and password =?";
		ResultSet rs=null; 
		Teacher teacher=new Teacher();
		try {
			rs=dbpool.doQueryRS(sql, new Object[]{teacherId, password});
			if (rs.next()) {
				teacher.setTeacherId(rs.getString("teacherId"));
				teacher.setTeacherName(rs.getString("teacherName"));
				teacher.setAdmin(rs.getBoolean("admin"));
				teacher.setPassword(rs.getString("password"));
				
			} else {
				teacher = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		
		return teacher;
	}

}

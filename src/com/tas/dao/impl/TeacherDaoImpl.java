package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Teacher> getTeachers() {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        DBPool dbpool = new DBPool();
        String sql="select [teacherId],[password],[teacherName],[admin] from T_Teacher";
        ResultSet rs=null; 
        Teacher teacher = null;
           try {
               rs=dbpool.doQueryRS(sql, new Object[]{ });
               while (rs.next()) {
                   teacher = new Teacher();
                   teacher.setTeacherId(rs.getString("teacherId"));
                   teacher.setPassword(rs.getString("password"));
                   teacher.setTeacherName(rs.getString("teacherName"));
                   teacher.setAdmin(rs.getBoolean("admin"));
                   teacherList.add(teacher);
               } 
           } catch (SQLException e) {
               e.printStackTrace();
           }finally{
               dbpool.close();
           }
           return teacherList;
    }

}

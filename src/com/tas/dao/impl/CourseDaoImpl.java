package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tas.bean.Course;
import com.tas.dao.CourseDao;
import com.tas.db.DBPool;

public class CourseDaoImpl implements CourseDao {

	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		 List<Course> courseList= new  ArrayList<Course>();
		 DBPool dbpool = new DBPool();
			//Connection conn=dbpool.getConnection();
			String sql="select [courseId],[courseName],[language] from T_Course";
			ResultSet rs=null; 
			Course course=null;
			try {
				rs=dbpool.doQueryRS(sql, new Object[]{ });
				while (rs.next()) {
					course= new Course();
					course.setCourseId(rs.getInt("courseId"));
					course.setCourseName(rs.getString("courseName"));
					course.setLanguage(rs.getString("language"));
					courseList.add(course);
				} 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				dbpool.close();
			}
			
			return courseList;
	}

}

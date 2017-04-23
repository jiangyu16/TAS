package com.tas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.tas.bean.Course;
 
import com.tas.db.DBPool;

public interface CourseDao {
	public List<Course> getCourses();
	
}

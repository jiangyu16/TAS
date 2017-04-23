package com.tas.dao;

import com.tas.bean.Teacher;

public interface TeacherDao {
	public abstract Teacher login(String teacherId, String password);
}

package com.tas.dao;

import java.util.List;

import com.tas.bean.Teacher;

public interface TeacherDao {
	public abstract Teacher login(String teacherId, String password);
	public abstract List<Teacher> getTeachers();
}

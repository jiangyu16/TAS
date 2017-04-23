package com.tas.service.impl;

import com.tas.bean.Teacher;
import com.tas.dao.TeacherDao;
import com.tas.dao.impl.TeacherDaoImpl;
import com.tas.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
   TeacherDao teacherdao;
   
	public TeacherServiceImpl() {
		teacherdao=new TeacherDaoImpl();
   }

	@Override
	public Teacher login(String teacherId, String password) {
		// TODO Auto-generated method stub
		
		return teacherdao.login(teacherId, password);
	}

}

package com.tas.test;
import com.tas.dao.impl.*;
import com.tas.bean.Teacher;
import com.tas.dao.*;
public class TeacherTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TeacherDao teacherdao = new TeacherDaoImpl();
		Teacher teacher =teacherdao.login("yzchair", "1111");
		System.out.println(teacher.getTeacherName()+" "+teacher.isAdmin());
	}

}

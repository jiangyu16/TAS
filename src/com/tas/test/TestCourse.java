package com.tas.test;

import java.util.*;

import com.tas.bean.Course;
import com.tas.dao.CourseDao;
import com.tas.dao.impl.CourseDaoImpl;

public class TestCourse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CourseDao coursedao= new CourseDaoImpl();
		List<Course> courselist= coursedao.getCourses();
		Iterator<Course> it = courselist.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println("ewwe");
	}

}

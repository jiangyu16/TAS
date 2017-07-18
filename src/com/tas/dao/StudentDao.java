package com.tas.dao;

import java.util.List;
import com.tas.bean.Student;

public interface StudentDao {

	//public List<Student> getStudents(int classId);

	//public int getAllgetStudent(int courseId);

	//public List<Student> getAllgetStudent(int courseId, int curPage, int pageSize);
	public int insertStudent(Student student);
	public int insertStuents(Student[]students);
////
	public List<Student> getStudents(String classId);

	public int getAllgetStudent(String courseId);

	public List<Student> getAllgetStudent(String courseId, int curPage, int pageSize);
	
	public int delStudent(String studentId);
	
	public int resetPassword(String studentId);
	


}

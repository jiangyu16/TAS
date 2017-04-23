package com.tas.service;
import java.util.List;

import com.tas.bean.Student;
import com.tas.util.PageControl;

public interface StudentService {
	//public PageControl<Student> getStudentByClassId(int classId, int curPage, int pageSize);
	public void addExcelData(List<Student> stuList);
	public PageControl<Student> getStudentByClassId(String classId, int curPage, int pageSize);
}

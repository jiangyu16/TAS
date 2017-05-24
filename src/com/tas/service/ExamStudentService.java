package com.tas.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tas.bean.ExamStudent;
import com.tas.util.PageControl;

//考生
public interface ExamStudentService {
	public int addAllClassStudents(String examInfoId, String classId); 
	public PageControl<ExamStudent> getExamStudentsByExamInfoId(String examInfoId,int curPage, int pageSize);
	public int deleteExamStudents(HttpServletRequest request);
}

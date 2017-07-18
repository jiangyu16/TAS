package com.tas.dao;

import java.util.List;

import com.tas.bean.ExamStudent;

public interface ExamStudentDao {
	public int addAllClassStudents(String examInfoId, String classId );
	public List<ExamStudent> getExamStudentsByExamInfoId(String examInfoId,int offset, int fetch);
	public int getAllExamStudentNum(String examInfoId);
	public int deleteExamStudent(String examInfoId,String studentId);
	public int deleteExamStudents(ExamStudent[] examStudents,String examInfoId);//批处理删除
}

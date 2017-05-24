package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tas.bean.ExamStudent;
import com.tas.dao.ExamStudentDao;
import com.tas.db.DBPool;

public class ExamStudentDaoImpl implements ExamStudentDao {

	@Override
	public int addAllClassStudents(String examInfoId, String classId) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql;
	//	if(examInfo.getExamInfoId()==0)
		  sql = "INSERT INTO [dbo].[T_Exam] ([examInfoId],[studentId]) "
		  		+ "select ?,studentId from T_Student as A where classId = ?"
		  		+ " and not exists"
		  		+ "(select * from T_Exam where   T_Exam.studentId = A.studentId  and examInfoId = ?) ";
	//	else {
			
	//	}
		//System.out.println("examinfoid "+examInfoId+" "+ classId);
		int result=0;
		 			try {
						result = dbpool.doUpdate(sql, new Object[]{
								examInfoId,classId,examInfoId
								
						});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						dbpool.close();
					}
	 
		
		return result;
	}

	@Override
	public List<ExamStudent> getExamStudentsByExamInfoId(String examInfoId,int offset, int fetch) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql="select T_Exam.studentId,T_Exam.examInfoId, T_Exam.programScore,T_Exam.choiceScore,"
				+ "T_Student.stuName,T_Class.classId,T_Class.className, T_ExamInfo.examName ,"
				+ " ROW_NUMBER() OVER(ORDER BY T_Class.className asc,T_Exam.studentId asc) AS num "
				+ "from T_Exam,T_Student,T_Class,T_ExamInfo  where T_Exam.studentId=T_Student.studentId "
				+ " and T_Student.classId=T_Class.classId and T_exam.examInfoId=?"
				+ " and T_exam.examInfoId=T_ExamInfo.examInfoId"
				+ " ORDER BY num OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY ";
		
		List<ExamStudent> examStudentList = new ArrayList<ExamStudent>();
		try {
			ResultSet rs = dbpool.doQueryRS(sql, new Object[]{examInfoId,offset,fetch});
			
			while(rs.next()){
				ExamStudent es = new ExamStudent();
				es.setChoiceScore(rs.getInt("choiceScore"));
				es.setClassId(rs.getString("classId"));
				es.setClassName(rs.getString("className"));
				es.setExamInfoId(rs.getString("examInfoId"));
				es.setProgramScore(rs.getInt("programScore"));
				es.setStudentId(rs.getString("studentId"));
				es.setStudentName(rs.getString("stuName"));
				es.setExamName(rs.getString("examName"));
				
				examStudentList.add(es);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}

		return examStudentList;
	}

	@Override
	public int getAllExamStudentNum(String examInfoId) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql="select count(*) as examStudentNum from T_Exam where examInfoId=?";
		int result=0;
		try{
			ResultSet rs = dbpool.doQueryRS(sql, new Object[]{examInfoId});
			if(rs.next())result = rs.getInt("examStudentNum");
		}catch(SQLException e){
			
		}finally{
			dbpool.close();
		}
		return result;
	}

	@Override
	public int deleteExamStudent(String examInfoId, String studentId) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql="DELETE FROM [dbo].[T_Exam] WHERE examInfoId=? and studentId=? ";
		int result=0;
		try {
			result = dbpool.doUpdate(sql, new Object[]{
					examInfoId,studentId
					
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		return result;
	}

}

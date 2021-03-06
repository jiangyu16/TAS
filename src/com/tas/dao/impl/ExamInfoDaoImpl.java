package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tas.bean.Course;
import com.tas.bean.ExamInfo;
import com.tas.dao.ExamInfoDao;
import com.tas.db.DBPool;

public class ExamInfoDaoImpl implements ExamInfoDao {

	@Override
	public int insert_ExamInfo(ExamInfo examInfo) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql;
	//	if(examInfo.getExamInfoId()==0)
		  sql = "INSERT INTO [dbo].[T_ExamInfo]"
				+ "([examName],[paperId], [startTime],[endTime] ,[programScore],[choiceScore],[type])"
           		+ " VALUES  (?,?,?,?,?,?,?) ";
	//	else {
			
	//	}
		
		int result=0;
		 			try {
						result = dbpool.doUpdate(sql, new Object[]{
								examInfo.getExamName(),
								examInfo.getPaperId(),
								examInfo.getStartTime(),
								examInfo.getEndTime(),
								examInfo.getProgramScore(),
								examInfo.getChoiceScore(),
								examInfo.getType()
								
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
	public List<ExamInfo> getAllExamInfos(int offset, int fetch) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		// ORDER BY num OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY
		String sql = "SELECT [examInfoId] ,[examName],[paperId] ,[startTime] ,[endTime],[programScore] ,[choiceScore] ,[type]"+
		" ,ROW_NUMBER() OVER(ORDER BY [examInfoId] desc) AS num "+
 " FROM [dbo].[T_ExamInfo]"+" ORDER BY num OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY";
		 ExamInfo examInfo;
		ResultSet rs=null; 
		List<ExamInfo> examInfos = new ArrayList<ExamInfo>();
		 
		//int result=0;
		try {
			rs=dbpool.doQueryRS(sql, new Object[]{offset,fetch });
			while (rs.next()) {
				  examInfo= new ExamInfo();
				  examInfo.setExamInfoId(rs.getInt("examInfoId"));
				  examInfo.setExamName(rs.getString("examName"));
				  examInfo.setPaperId(rs.getInt("paperId"));
				  examInfo.setStartTime(rs.getString("startTime"));
				  examInfo.setEndTime(rs.getString("endTime"));
				  examInfo.setProgramScore(rs.getInt("programScore"));
				  examInfo.setChoiceScore(rs.getInt("choiceScore"));
				  examInfo.setType(rs.getInt("type"));
				  examInfos.add(examInfo);
				  
				 
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		
		//return result;
		return examInfos;
	}

	@Override
	public ExamInfo get_ExamInfoById(int examInfoId) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql = "SELECT [examInfoId] ,[examName],[paperId] ,"
				+ "CONVERT(varchar(100), startTime, 20) AS startTime ,"
				+ "CONVERT(varchar(100), endTime, 20) AS endTime ,"
				+ "[programScore] ,[choiceScore] ,[type] "
				+ "FROM [dbo].[T_ExamInfo] where examInfoId =?";
		ExamInfo efo= new ExamInfo();
		ResultSet rs=null; 
		 
		try {
			rs=dbpool.doQueryRS(sql, new Object[]{ examInfoId});
			if (rs.next()) {
				 
				efo.setExamInfoId(rs.getInt("examInfoId"));
				efo.setExamName(rs.getString("examName"));
				efo.setPaperId(rs.getInt("paperId"));
				efo.setStartTime(rs.getString("startTime"));
				efo.setEndTime(rs.getString("endTime"));
				efo.setProgramScore(rs.getInt("programScore"));
				efo.setChoiceScore(rs.getInt("choiceScore"));
				efo.setType(rs.getInt("type"));
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		
		return efo;
	}

	@Override
	public int update_ExamInfo(ExamInfo examInfo) {
		// TODO Auto-generated method stub
		String  sql = "UPDATE [dbo].[T_ExamInfo]"+
			       "SET [examName] = ?"+
				", paperId=?"+
			       " ,[startTime] =  ?"+
			       " ,[endTime] =  ?"+
			       " ,[programScore] = ?"+
			        ",[choiceScore] =  ?"+
			        " ,[type] =  ?"+
			        " WHERE [T_ExamInfo].examInfoId=? ";
		
		DBPool dbpool= new DBPool();
	 
		
		int result=0;
		 			try {
						result = dbpool.doUpdate(sql, new Object[]{
								examInfo.getExamName(),
								examInfo.getPaperId(),
								examInfo.getStartTime(),
								examInfo.getEndTime(),
								examInfo.getProgramScore(),
								examInfo.getChoiceScore(),
								examInfo.getType(),
								examInfo.getExamInfoId()
								
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
	public int getAllExamInfosNum() {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql = "select count(*) as totalNum from T_ExamInfo ";
		 
		ResultSet rs=null; 
		int result=0;
		try {
			rs=dbpool.doQueryRS(sql, new Object[]{ });
			if (rs.next()) {
				  result = rs.getInt("totalNum");
				 
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		
		return result;
	}

	@Override
	public int deleteExamInfoById(int examInfoId) {
		// TODO Auto-generated method stub
		String  sql = "DELETE FROM [dbo].[T_ExamInfo] WHERE examInfoId=?";
		
		DBPool dbpool= new DBPool();
	 
		
		int result=0;
		 			try {
						result = dbpool.doUpdate(sql, new Object[]{examInfoId});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						dbpool.close();
					}
	 
		
		return result;
	}

}

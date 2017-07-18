package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tas.bean.Course;
import com.tas.bean.Paper;
import com.tas.dao.PaperDao;
import com.tas.db.DBPool;

public class PaperDaoImpl implements PaperDao {
	
	@Override
	//所有参数都存在paper里
	public List<Paper> getPaperList(Paper paper ,int offset, int fetch) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		List <Paper> paperList =new ArrayList <Paper>();
		String sql;
		//拼装sql语句
		sql="select *,ROW_NUMBER() OVER(ORDER BY [paperId] asc) AS num  from T_paper where 1=1 ";
		if(paper.getCourseId()!=0){
			sql+=" and courseId = " +paper.getCourseId();
		}
		if(!paper.getTeacherId().trim().equals("") && paper.getTeacherId()!=null){
			sql+=" and teacherId = " +paper.getTeacherId();
		}
		if(paper.getPaperType()!=0){
			sql+=" and paperType = " +paper.getPaperType();
		}
		sql +=" ORDER BY num OFFSET "+offset;
		sql +=" ROWS  FETCH NEXT "+fetch;
		sql +=" ROWS ONLY ";
		ResultSet rs=null; 
		try {
			rs=dbpool.doQueryRS(sql, new Object[]{ });
			while (rs.next()) {
				Paper p = new Paper();
				p.setPaperName(rs.getString("paperName"));
				p.setCourseId(rs.getInt("courseId"));
				p.setChoiceScore(rs.getInt("choiceScore"));
				p.setProgramScore(rs.getInt("programScore"));
				p.setPaperType(rs.getInt("paperType"));
				paperList.add(p);
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		
		return paperList;
	}

	@Override
	public int getPaperListNum(Paper paper) {
		DBPool dbpool= new DBPool();
		List <Paper> paperList =new ArrayList <Paper>();
		String sql;
		//拼装sql语句
		sql="select * from T_paper where 1=1 ";
		if(paper.getCourseId()!=0){
			sql+=" and courseId = " +paper.getCourseId();
		}
		if(paper.getTeacherId()!=null && !paper.getTeacherId().trim().equals("")){
			sql+=" and teacherId = " +paper.getTeacherId();
		}
		if(paper.getPaperType()!=0){
			sql+=" and paperType = " +paper.getPaperType();
		}
		ResultSet rs=null; 
		try {
			rs=dbpool.doQueryRS(sql, new Object[]{ });
			while (rs.next()) {
				Paper p = new Paper();
				p.setPaperName(rs.getString("paperName"));
				p.setCourseId(rs.getInt("courseId"));
				p.setChoiceScore(rs.getInt("choiceScore"));
				p.setProgramScore(rs.getInt("programScore"));
				p.setPaperType(rs.getInt("paperType"));
				paperList.add(p);
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		
		return paperList.size();
	}

	@Override
	public int updatePaper(Paper paper) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql="UPDATE [dbo].[T_Paper]  SET [paperName] =?,"
				+ "[programScore] =?,"
				+ "[choiceScore] = ?,"
				+ "[teacherId] = ?,"
				+ "[paperType] = ?,"
				+ "[isVisable] = ?,"
				+ "[courseId] = ?"
				+ " WHERE paperId =? ";
		int result=0;
		
		try {
			result =dbpool.doUpdate(sql, new Object[]{
				paper.getPaperName(),
				paper.getProgramScore(),
				paper.getChoiceScore(),
				paper.getTeacherId(),
				paper.getPaperType(),
				paper.isVisable(),
				paper.getCourseId(),
				paper.getPaperId()
				
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}

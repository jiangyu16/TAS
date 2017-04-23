package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tas.bean.Chapter;
import com.tas.bean.Teacher;
import com.tas.dao.ChapterDao;
import com.tas.db.DBPool;

public class ChapterDaoImpl implements ChapterDao {

	@Override
	public List<Chapter> get_chapterByCourseId(int courseId) {
		// TODO Auto-generated method stub
		List<Chapter> chapterlists = new ArrayList<Chapter>();
		DBPool dbpool = new DBPool();
		//Connection conn=dbpool.getConnection();
		String sql="select * from T_Chapter where courseId=?";
		ResultSet rs=null; 
		
		try {
			Chapter chapter;
			rs=dbpool.doQueryRS(sql, new Object[]{courseId});
			while (rs.next()) {
				chapter=new Chapter();
				 chapter.setChapterId(rs.getInt("chapterId"));
				 chapter.setCourseId(rs.getInt("courseId"));
				 chapter.setChapterName(rs.getString("chapterName"));
				 chapterlists.add(chapter);
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		return chapterlists;
	}

	@Override
	public int insert_chapter(int courseId, String chapterName) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql = "insert into  T_Chapter ([courseId] ,[chapterName])  values (?,?) ";
		int result=0;
		 			try {
						result = dbpool.doUpdate(sql, new Object[]{courseId, chapterName});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						dbpool.close();
					}
	 
		
		return result;
	}

	@Override
	public int delete_chapter( int chapterId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				DBPool dbpool= new DBPool();
				String sql = "delete from  T_Chapter where chapterId=? ";//chapterId
				int result=0;
				 			try {
								result = dbpool.doUpdate(sql, new Object[]{  chapterId});
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}finally{
								dbpool.close();
							}
			 
				
				return result;
	}

	@Override
	public int update_chapter(int chapterId, String chapterName) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql = "update    T_Chapter set chapterName=? where chapterId=?";
		int result=0;
		 			try {
						result = dbpool.doUpdate(sql, new Object[]{ chapterName,chapterId});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						dbpool.close();
					}
	 
		
		return result;
	}

}

package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tas.bean.ProgramProblem;
import com.tas.bean.T_Class;
import com.tas.dao.ClassDao;
import com.tas.db.DBPool;

public class ClassDaoImpl implements ClassDao {

	@Override
	public List<T_Class> getClass(int offset, int fetch) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		String sql = "SELECT [classId] , [className] FROM [db_exam].[dbo].[T_Class]"
				+ " ORDER BY classId OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY ";

		List<T_Class> classList = new ArrayList<T_Class>();
		T_Class class1;
		ResultSet rs = null;

		try {
			rs = dbpool.doQueryRS(sql, new Object[] { offset, fetch });
			while (rs.next()) {
				class1 = new T_Class();
				class1.setClassId(rs.getInt("classId"));
				class1.setClassName(rs.getString("className"));
				classList.add(class1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbpool.close();
		}
		return classList;
	}

	@Override
	public int getAllClass() {
		DBPool dbpool = new DBPool();
		int totalProgram = 0;
		String sql = "select count(*) as totalProgram from T_Class";
		ResultSet rs = null;
		try {
			rs = dbpool.doQueryRS(sql, new Object[] {});
			if (rs.next())
				totalProgram = rs.getInt("totalProgram");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbpool.close();
		}
		return totalProgram;
	}

	@Override
	public int update_chapter(int classId, String className) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		String sql = "update T_class set className=? where classId=?";
		System.out.println(classId + " " + className);
		int result = 0;
		try {
			result = dbpool.doUpdate(sql, new Object[] { className, classId });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbpool.close();
		}

		return result;
	}

	@Override
	public int delete_class(int classId) {

		DBPool dbpool = new DBPool();
		String sql = "delete from  T_Class where classId=? ";// chapterId
		int result = 0;
		try {
			result = dbpool.doUpdate(sql, new Object[] { classId });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbpool.close();
		}
		return result;

	}

	@Override
	public int insert_class(int classId, String className) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql = "insert into  T_Class ([className])  values (?) ";
		int result=0;
		 			try {
						result = dbpool.doUpdate(sql, new Object[]{className});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						dbpool.close();
					}
	 
		
		return result;
	}
	
	@Override
	public int update_class(int classId, String className) {
		// TODO Auto-generated method stub
				DBPool dbpool= new DBPool();
				String sql = "update    T_Class set className=? where classId=?";
				int result=0;
				 			try {
								result = dbpool.doUpdate(sql, new Object[]{ className,classId});
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}finally{
								dbpool.close();
							}
			 
				
				return result;
			}

	@Override
	public List<T_Class> search_class(String className ,int offset, int fetch) {
		DBPool dbpool= new DBPool();
		String sql = "select *  from T_Class where className=?"
				+" ORDER BY classId OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY ";
		T_Class class1;
		List<T_Class> classList = new ArrayList<T_Class>();
		 ResultSet rs=null; 
		 try {
			rs=dbpool.doQueryRS(sql, new Object[]{className,offset,fetch});
			while(rs.next()){
				class1 = new T_Class();
				class1.setClassId(rs.getInt("classId"));
				class1.setClassName(rs.getString("className"));
				classList.add(class1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		 return classList;
	}

	@Override
	public int getClassByClassName(String className) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		 int totalProgram=0;
		String sql="select count(*) as totalProgram from T_Class where className=?";
		 ResultSet rs=null; 
		 try {
			rs=dbpool.doQueryRS(sql, new Object[]{className});
			if(rs.next())
			totalProgram = rs.getInt("totalProgram");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		return totalProgram;
	}
	
}

package com.tas.dao;

import java.sql.ResultSet;
import java.util.List;

import com.tas.bean.T_Class;



public interface ClassDao {
	public List<T_Class> getClass(int offset, int fetch);
	public int getAllClass();
	public int update_chapter(int classId, String className);
	public int delete_class(int classId);
	public int insert_class(int classId, String className);
	public int update_class(  int classId,String className);
	public List<T_Class> search_class(String className ,int offset, int fetch);
	public int getClassByClassName(String className);
	
}

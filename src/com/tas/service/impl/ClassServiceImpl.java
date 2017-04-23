package com.tas.service.impl;

import java.util.List;

import com.tas.bean.ProgramProblem;
import com.tas.bean.T_Class;
import com.tas.dao.ClassDao;
import com.tas.dao.QuestionDao;
import com.tas.dao.impl.ClassDaoImpl;
import com.tas.dao.impl.QuestionDaoImpl;
import com.tas.service.ClassService;
import com.tas.util.PageControl;

public class ClassServiceImpl implements ClassService {

	@Override
	public PageControl<T_Class> getClassByClassId( int curPage, int pageSize) {
		int offset,fetch;
		fetch=pageSize;
		offset=(curPage-1)*pageSize;
		ClassDao cd = new ClassDaoImpl();
		
		int totalRows = cd.getAllClass();
		List<T_Class> prolist = cd.getClass(offset, fetch);
		 
		PageControl<T_Class> pc = new PageControl<T_Class>(curPage,totalRows,pageSize);
		pc.setList(prolist);
		return pc;
	}
	
	@Override
	public PageControl<T_Class> getClassByClassName(String className,int curPage, int pageSize) {
		// TODO Auto-generated method stub
		int offset,fetch;
		
		fetch=pageSize;
		offset=(curPage-1)*pageSize;
	 	//System.out.println("ser" +offset+" "+ fetch);
		ClassDao cd = new ClassDaoImpl();
		List<T_Class> classlist= cd.search_class(className,offset,fetch);
		int totalRows = cd.getClassByClassName(className);
		PageControl<T_Class> pc = new PageControl<T_Class>(curPage,totalRows,pageSize);
		pc.setList(classlist);
		return pc;
		
	}

}

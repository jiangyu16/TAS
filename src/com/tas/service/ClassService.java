package com.tas.service;

import com.tas.bean.T_Class;
import com.tas.util.PageControl;

public interface ClassService {
	//new
	public PageControl<T_Class> getClassByClassId(int curPage, int pageSize);
	public PageControl<T_Class> getClassByClassName(String className,int curPage, int pageSize);
}

package com.tas.service;

import com.tas.bean.Paper;
import com.tas.bean.ProgramProblem;
import com.tas.util.PageControl;

public interface PaperService {

	public PageControl<ProgramProblem> getProblemsByPaperId(String paperId, int curPage, int pageSize);
	
	public PageControl<Paper> getPapersByAll(int paperType, int courseId, String teacherId,
	                                         int curPage, int pageSize);

}

package com.tas.service;

import java.util.List;

import com.tas.bean.ProgramProblem;
import com.tas.util.PageControl;

public interface ProgramProblemService {
	//public List<ProgramProblem> getProgramProblemByLanguage(int courseId  ,int offset, int fetch) ;
	public PageControl<ProgramProblem> getProgramProblemByLanguage(int courseId  ,int curPage, int pageSize) ;
	 
	//public List<ProgramProblem> getProgramProblemByCourseAndChapter(int courseId  ,int offset, int fetch) ;
	public PageControl<ProgramProblem>  getProgramProblemByCourseAndChapter(int courseId, int chapterId, int curPage, int pageSize);
	public int saveProgramProblem(ProgramProblem pp);
	public int delProgramProblem(int programProblemId);
}

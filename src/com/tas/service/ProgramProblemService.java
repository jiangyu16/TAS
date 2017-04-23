package com.tas.service;

import java.util.List;

import com.tas.bean.ProgramProblem;
import com.tas.util.PageControl;

public interface ProgramProblemService {
	//public List<ProgramProblem> getProgramProblemByLanguage(int courseId  ,int offset, int fetch) ;
	//public PageControl<ProgramProblem> getProgramProblemByLanguage(int courseId  ,int curPage, int pageSize) ;
	//public PageControl<ProgramProblem> getProgramProblemByLanguage(int courseId  ,int curPage, int pageSize, int programType) ;
	//public List<ProgramProblem> getProgramProblemByCourseAndChapter(int courseId  ,int offset, int fetch) ;
//	public PageControl<ProgramProblem>  getProgramProblemByCourseAndChapter(int courseId, int chapterId, int curPage, int pageSize);
	//public PageControl<ProgramProblem>  getProgramProblemByCourseAndChapter(int courseId, int chapterId, int curPage, int pageSize, int programType);
	public int saveProgramProblem(ProgramProblem pp);
	public int delProgramProblem(int programProblemId);
	public PageControl<ProgramProblem> getProgramProblem(int courseId, int chapterId, int curPage, int pageSize, int programType);
}

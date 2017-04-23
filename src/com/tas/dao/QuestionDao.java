package com.tas.dao;
 

import java.util.List;

import com.tas.bean.ProgramProblem;

public interface QuestionDao {
	//public List<ProgramProblem> getProgramProblem();
	 
     public List<ProgramProblem> getProgramProblem(int courseId, int offset, int fetch,int chapterId,int programType);
    // public List<ProgramProblem> getProgramProblemByCourseAndProgramType(int courseId, int offset, int fetch);
   //  public List<ProgramProblem> getProgramProblemByCourseAndChapter(int courseId, int chapterId,int offset, int fetch);
    // public int getAllProgramProblem(int courseId);//记录总数
   //  public int getProgramProblemNumByCourseAndChapter(int courseId,int chapterId);//按章节记录总数
     public int getProgramProblemNum(int courseId,int chapterId, int programType);
     public int insertProgramProblem(ProgramProblem pp);
     public int updateProgramProblem(ProgramProblem pp);
     public int delProgramProblemById(int programProblemId);
   
}

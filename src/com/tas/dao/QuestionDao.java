package com.tas.dao;
 

import java.util.List;

import com.tas.bean.ProgramProblem;

public interface QuestionDao {
     public List<ProgramProblem> getProgramProblemByLanguage(int courseId, int offset, int fetch);
     public List<ProgramProblem> getProgramProblemByCourseAndChapter(int courseId, int chapterId,int offset, int fetch);
     public int getAllProgramProblem(int courseId);
     public int getProgramProblemNumByCourseAndChapter(int courseId,int chapterId);
     public int insertProgramProblem(ProgramProblem pp);
     public int updateProgramProblem(ProgramProblem pp);
     public int delProgramProblemById(int programProblemId);
   
}

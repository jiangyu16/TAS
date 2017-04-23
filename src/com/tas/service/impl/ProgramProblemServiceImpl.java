package com.tas.service.impl;

import java.util.List;

import com.tas.bean.ProgramProblem;
import com.tas.dao.QuestionDao;
import com.tas.dao.impl.QuestionDaoImpl;
import com.tas.service.ProgramProblemService;
import com.tas.util.PageControl;

public class ProgramProblemServiceImpl implements ProgramProblemService {

	@Override
	public PageControl<ProgramProblem> getProgramProblemByLanguage(int courseId,int curPage, int pageSize) {
		// TODO Auto-generated method stub
		int offset,fetch;
		
		fetch=pageSize;
		offset=(curPage-1)*pageSize;
	 	//System.out.println("ser" +offset+" "+ fetch);
		QuestionDao qd=new QuestionDaoImpl();
		int totalRows = qd.getAllProgramProblem( courseId);
		List<ProgramProblem> prolist = qd.getProgramProblemByLanguage(courseId, offset, fetch);
		//System.out.println(" "+prolist.size());
		PageControl<ProgramProblem> pc = new PageControl<ProgramProblem>(curPage,totalRows,pageSize);
		pc.setList(prolist);
		return pc;
		
	}

	@Override
	public PageControl<ProgramProblem> getProgramProblemByCourseAndChapter(int courseId, int chapterId,int curPage, int pageSize) {
		// TODO Auto-generated method stub
        int offset,fetch;
		
		fetch=pageSize;
		offset=(curPage-1)*pageSize;
	//	System.out.println("ser" +offset+" "+ fetch);
		QuestionDao qd=new QuestionDaoImpl();
		int totalRows = qd.getProgramProblemNumByCourseAndChapter(courseId, chapterId);
		List<ProgramProblem> prolist = qd.getProgramProblemByCourseAndChapter(courseId, chapterId, offset, fetch);
		//System.out.println("行数"+prolist.size());
		PageControl<ProgramProblem> pc = new PageControl<ProgramProblem>(curPage,totalRows,pageSize);
		pc.setList(prolist);
		return pc;
	}

	@Override
	public int saveProgramProblem(ProgramProblem pp) {
		// TODO Auto-generated method stub
		int result=0;
		QuestionDao qd= new QuestionDaoImpl();
		if(pp.getProgramProblemId()==-1)result=qd.insertProgramProblem(pp);
		else result =qd.updateProgramProblem(pp);
		return result;
	}

	@Override
	public int delProgramProblem(int programProblemId) {
		// TODO Auto-generated method stub
		return new QuestionDaoImpl().delProgramProblemById(programProblemId);
		 
	}

}

package com.tas.service.impl;

import java.util.List;

import com.tas.bean.ProgramProblem;
import com.tas.dao.QuestionDao;
import com.tas.dao.impl.QuestionDaoImpl;
import com.tas.service.ProgramProblemService;
import com.tas.util.PageControl;

public class ProgramProblemServiceImpl implements ProgramProblemService {

	// @Override
	// public PageControl<ProgramProblem> getProgramProblemByLanguage(int
	// courseId,int curPage, int pageSize) {
	// // TODO Auto-generated method stub
	// int offset,fetch;
	//
	// fetch=pageSize;
	// offset=(curPage-1)*pageSize;
	// //System.out.println("ser" +offset+" "+ fetch);
	// QuestionDao qd=new QuestionDaoImpl();
	// int totalRows = qd.getAllProgramProblem( courseId);
	// List<ProgramProblem> prolist = qd.getProgramProblemByLanguage(courseId,
	// offset, fetch);
	// //System.out.println(" "+prolist.size());
	// PageControl<ProgramProblem> pc = new
	// PageControl<ProgramProblem>(curPage,totalRows,pageSize);
	// pc.setList(prolist);
	// return pc;
	//
	// }
	//
	// @Override
	// public PageControl<ProgramProblem>
	// getProgramProblemByCourseAndChapter(int courseId, int chapterId,int
	// curPage, int pageSize) {
	// // TODO Auto-generated method stub
	// int offset,fetch;
	//
	// fetch=pageSize;
	// offset=(curPage-1)*pageSize;
	// // System.out.println("ser" +offset+" "+ fetch);
	// QuestionDao qd=new QuestionDaoImpl();
	// int totalRows = qd.getProgramProblemNumByCourseAndChapter(courseId,
	// chapterId);
	// List<ProgramProblem> prolist =
	// qd.getProgramProblemByCourseAndChapter(courseId, chapterId, offset,
	// fetch);
	// //System.out.println("行数"+prolist.size());
	// PageControl<ProgramProblem> pc = new
	// PageControl<ProgramProblem>(curPage,totalRows,pageSize);
	// pc.setList(prolist);
	// return pc;
	// }

	@Override
	public int saveProgramProblem(ProgramProblem pp) {
		// TODO Auto-generated method stub
		int result = 0;
		QuestionDao qd = new QuestionDaoImpl();
		if (pp.getProgramProblemId() == -1)
			result = qd.insertProgramProblem(pp);
		else
			result = qd.updateProgramProblem(pp);
		return result;
	}

	@Override
	public int delProgramProblem(int programProblemId) {
		// TODO Auto-generated method stub
		return new QuestionDaoImpl().delProgramProblemById(programProblemId);

	}

	// @Override
	// public PageControl<ProgramProblem> getProgramProblemByLanguage(int
	// courseId, int curPage, int pageSize,
	// int programType) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public PageControl<ProgramProblem>
	// getProgramProblemByCourseAndChapter(int courseId, int chapterId, int
	// curPage,
	// int pageSize, int programType) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public PageControl<ProgramProblem> getProgramProblem(int courseId, int chapterId, int curPage, int pageSize,
			int programType) {
		// TODO Auto-generated method stub
		
		 int offset,fetch;
		 fetch=pageSize;
		 offset=(curPage-1)*pageSize;
		 List<ProgramProblem> prolist;
		 PageControl<ProgramProblem> pc=null;
		 //System.out.println("ser" +offset+" "+ fetch);
		 QuestionDao qd=new QuestionDaoImpl();
		 int totalRows;
		 totalRows = qd.getProgramProblemNum(courseId, chapterId, programType);
		 System.out.println(totalRows);
		 prolist = qd.getProgramProblem(courseId, offset, fetch, chapterId, programType);
		 pc=new PageControl<ProgramProblem>(curPage,totalRows,pageSize);
		 pc.setList(prolist);
//		if (chapterId == 0) {// 不分章节查询
//			if (programType == 0) {// 不分类型查询
//
//				 totalRows = qd.getAllProgramProblem( courseId);
//				prolist = qd.getProgramProblemByCourse(courseId, offset, fetch);
//				 //System.out.println(" "+prolist.size());
//				 
//				 pc= new PageControl<ProgramProblem>(curPage,totalRows,pageSize);
//				 pc.setList(prolist);
//			} else {// 按类型
//				
//				
//			//	System.out.println("eeeeeweew");
//			}
//		} else {// 按章节查询
//			if (programType == 0) {// 不分类型查询
//
//				totalRows = qd.getProgramProblemNumByCourseAndChapter(courseId, chapterId);
//				prolist = qd.getProgramProblemByCourseAndChapter(courseId, chapterId, offset, fetch);
//				// System.out.println("行数"+prolist.size());
//				pc = new PageControl<ProgramProblem>(curPage, totalRows, pageSize);
//				pc.setList(prolist);
//			} else {// 按类型
//			//	System.out.println("eeeeeweew");
//			}
//		}
		return pc;
	}

}

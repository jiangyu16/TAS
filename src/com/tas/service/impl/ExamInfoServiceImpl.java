package com.tas.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tas.bean.ExamInfo;
import com.tas.bean.Student;
import com.tas.dao.ExamInfoDao;
import com.tas.dao.StudentDao;
import com.tas.dao.impl.ExamInfoDaoImpl;
import com.tas.dao.impl.StudentDaoImpl;
import com.tas.service.ExamInfoService;
import com.tas.service.StudentService;
import com.tas.util.PageControl;

public class ExamInfoServiceImpl implements ExamInfoService {

	@Override
	public int saveExamInfo(HttpServletRequest request) {
		// TODO Auto-generated method stub
		  int result=0;
	//	String actionName=request.getParameter("action");
		//System.out.println(actionName);
	//	if(actionName.equals("saveExamInfo")){
		  System.out.println(request.getParameter("examInfoId")+"ewfw");
			if(request.getParameter("examInfoId")==null){//创建新的考试
				ExamInfo efo= new ExamInfo();
				efo.setExamName(request.getParameter("examName"));
				efo.setStartTime(request.getParameter("startTime"));
				efo.setEndTime(request.getParameter("endTime"));
				efo.setType(1);
				System.out.println("创建新的考试paperId:"+request.getParameter("paperId"));
				efo.setPaperId(Integer.parseInt(request.getParameter("paperId")));
				//System.out.println(request.getParameter("examName"));
				efo.setProgramScore(Integer.parseInt(request.getParameter("programScore")));
			//	efo.setChoiceScore(Integer.parseInt(request.getParameter("ChoiceScore")));
				ExamInfoDao efd = new ExamInfoDaoImpl();
			    result=	efd.insert_ExamInfo(efo);
				
			}else {
				ExamInfo efo= new ExamInfo();
				efo.setExamInfoId(Integer.parseInt(request.getParameter("examInfoId")));
				efo.setExamName(request.getParameter("examName"));
				efo.setStartTime(request.getParameter("startTime"));
				efo.setEndTime(request.getParameter("endTime"));
				efo.setType(1);
				System.out.println("paperId:"+request.getParameter("paperId"));
				efo.setPaperId(Integer.parseInt(request.getParameter("paperId")));
				 
				efo.setProgramScore(Integer.parseInt(request.getParameter("programScore")));
			//	efo.setChoiceScore(Integer.parseInt(request.getParameter("ChoiceScore")));
				ExamInfoDao efd = new ExamInfoDaoImpl();
			    result=	efd.update_ExamInfo(efo);
			    System.out.println("影响了"+result+"行");
			}
			//int examInfoId=Integer.parseInt(request.getParameter("e"));
		//}
		return result;
	}

	@Override
	public ExamInfo getExamInfoById(int examInfoId) {
		// TODO Auto-generated method stub
		return new ExamInfoDaoImpl().get_ExamInfoById(examInfoId);
	}

	@Override
	public PageControl<ExamInfo> getAllExamInfos(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		/*
		 * 
		 * int curPage = Integer.parseInt(request.getParameter("curPage"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));

			StudentService service = new StudentServiceImpl();
			PageControl<Student> studentPc = service.getStudentByClassId(classId, curPage, pageSize);
		 * 
		 * */
		 int curPage = Integer.parseInt(request.getParameter("curPage"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			
			System.out.println(curPage+ " "+pageSize);
			
			int offset, fetch;

			fetch = pageSize;
			offset = (curPage - 1) * pageSize;
			// System.out.println("ser" +offset+" "+ fetch);
			ExamInfoDao ed = new ExamInfoDaoImpl();
			int totalRows = ed.getAllExamInfosNum();
			List<ExamInfo> examInfos = ed.getAllExamInfos(offset, fetch);
			//System.out.println("2222" + students.size());
			PageControl<ExamInfo> pc = new PageControl<ExamInfo>(curPage, totalRows, pageSize);
			//System.out.println(pc.getPageList());
			pc.setList(examInfos);
			return pc;
			
		 
	}

	@Override
	public int deleteExamInfoById(int examInfoId) {
		// TODO Auto-generated method stub
		ExamInfoDao ed = new ExamInfoDaoImpl();
		
		return ed.deleteExamInfoById(examInfoId);
	}

}

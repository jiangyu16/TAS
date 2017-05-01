package com.tas.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.tas.bean.ExamInfo;
import com.tas.bean.Student;
import com.tas.dao.ExamInfoDao;
import com.tas.dao.impl.ExamInfoDaoImpl;
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
			if(request.getParameter("examInfoId")==null){//创建新的考试
				ExamInfo efo= new ExamInfo();
				efo.setExamName(request.getParameter("examName"));
				efo.setStartTime(request.getParameter("startTime"));
				efo.setEndTime(request.getParameter("endTime"));
				efo.setType(1);
				System.out.println(request.getParameter("examName"));
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
				efo.setPaperId(1);
				System.out.println(request.getParameter("examName"));
				efo.setProgramScore(Integer.parseInt(request.getParameter("programScore")));
			//	efo.setChoiceScore(Integer.parseInt(request.getParameter("ChoiceScore")));
				ExamInfoDao efd = new ExamInfoDaoImpl();
			    result=	efd.update_ExamInfo(efo);
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
	public PageControl<ExamInfo> getgetExamAllInfos(HttpServletRequest request) {
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
			
			
		return null;
	}

}

package com.tas.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.tas.bean.ExamStudent;
import com.tas.bean.Student;
import com.tas.dao.ExamStudentDao;
import com.tas.dao.StudentDao;
import com.tas.dao.impl.ExamStudentDaoImpl;
import com.tas.dao.impl.StudentDaoImpl;
import com.tas.service.ExamStudentService;
import com.tas.util.PageControl;

public class ExamStudentServiceImpl implements ExamStudentService {

	@Override
	public int addAllClassStudents(String examInfoId, String classId) {
		// TODO Auto-generated method stub
		return new ExamStudentDaoImpl().addAllClassStudents(examInfoId, classId);
	}

	@Override
	public PageControl<ExamStudent> getExamStudentsByExamInfoId(String examInfoId, int curPage, int pageSize) {
		// TODO Auto-generated method stub
		int offset, fetch;

		fetch = pageSize;
		offset = (curPage - 1) * pageSize;
		// System.out.println("ser" +offset+" "+ fetch);
		ExamStudentDao esd = new ExamStudentDaoImpl();
		int totalRows = esd.getAllExamStudentNum(examInfoId);
		
		List<ExamStudent> examStudents =esd.getExamStudentsByExamInfoId(examInfoId, offset, fetch);
		//System.out.println("2222" + students.size());
		PageControl<ExamStudent> pc = new PageControl<ExamStudent>(curPage, totalRows, pageSize);
		pc.setList(examStudents);
		return pc;
	}

	@Override
	public int deleteExamStudents(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String examStudents=request.getParameter("examStudents");
		String examInfoId = request.getParameter("examInfoId");
		ObjectMapper objectMapper=new ObjectMapper();
       // StudentList studentList=objectMapper.readValue(str, StudentList.class);
		//System.out.println(examStudents);
		 ExamStudent[]  ess;
		 int i=0;
		try {
			//从json数据中把数组读出来
			ess = (ExamStudent[]) objectMapper.readValue(examStudents, ExamStudent[].class);
			i= new ExamStudentDaoImpl().deleteExamStudents(ess, examInfoId);
//			for(ExamStudent es:ess){
//				i=i+new ExamStudentDaoImpl().deleteExamStudent(examInfoId, es.getStudentId());
//			}
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
         
		
		return i;
	}

}

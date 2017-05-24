package com.tas.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.tas.bean.ExamStudent;
import com.tas.service.impl.ExamStudentServiceImpl;
import com.tas.util.BeanList;
import com.tas.util.ExamStudentList;
import com.tas.util.PageControl;

/**
 * Servlet implementation class ExamStudentServlet
 */
@WebServlet("/ExamStudentServlet")
public class ExamStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String actionName=request.getParameter("action");
		//System.out.println(actionName);
		if(actionName.equals("addAllStudentsToExam")){
			String examInfoId=request.getParameter("examInfoId");
			String classId = request.getParameter("classId");
			//System.out.println(examInfoId+classId);
			int result = new ExamStudentServiceImpl().addAllClassStudents(examInfoId, classId);
			System.out.println(result);
		}else if(actionName.equals("getAllExamStudents")){
			String examInfoId=request.getParameter("examInfoId");
			int curPage =Integer.parseInt(request.getParameter("curPage")) ;
			int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;
			 PageControl<ExamStudent> pc = new ExamStudentServiceImpl().getExamStudentsByExamInfoId(examInfoId, curPage, pageSize) ;
			 ObjectMapper mapper=new ObjectMapper();
				String result= mapper.writeValueAsString(pc);
				System.out.println(result);
				response.setContentType("text/javascript");
				response.setCharacterEncoding("utf-8");
				
				response.getWriter().print(result);
		}else if(actionName.equals("deleteExamStudent")){
		        int result = new ExamStudentServiceImpl().deleteExamStudents(request);
		        response.setContentType("text/javascript");
				response.setCharacterEncoding("utf-8");
				System.out.println(result);
				ObjectMapper mapper=new ObjectMapper();
				response.getWriter().print(  mapper.writeValueAsString(result));
				
			 // ExamStudentList  examStudentList = objectMapper.readValue(examStudents, ExamStudentList.class);
         //   list=studentList.getStudent();
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.tas.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.tas.bean.ExamInfo;
import com.tas.dao.impl.ExamInfoDaoImpl;
import com.tas.service.impl.ExamInfoServiceImpl;
import com.tas.util.PageControl;

/**
 * Servlet implementation class ExamInfoServlet
 */
@WebServlet("/ExamInfoServlet")
public class ExamInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamInfoServlet() {
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
		System.out.println(actionName);
		if (actionName.equals("getExamInfoById")) {
				
			int examInfoId = Integer.parseInt(request.getParameter("examInfoId"));
			ExamInfo efo = new ExamInfoServiceImpl().getExamInfoById(examInfoId);
			//ExamInfo efo = new ExamInfoDaoImpl().get_ExamInfoById(examInfoId);
			ObjectMapper mapper=new ObjectMapper();
			String result= mapper.writeValueAsString(efo);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(result); 
			System.out.println(result);
		}else if(actionName.equals("getExamAllInfos")){
			request.getSession().setAttribute("testsession", "ts");//用来测试session能否跟随页面跳转
			PageControl<ExamInfo> pc = new ExamInfoServiceImpl().getAllExamInfos(request);
			ObjectMapper mapper=new ObjectMapper();
			String result= mapper.writeValueAsString(pc );
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			System.out.println(result);
			response.getWriter().print(result); 
		}else if(actionName.equals("deleteExamInfoById")){
			int examInfoId = Integer.parseInt(request.getParameter("examInfoId"));
			int result = new ExamInfoServiceImpl().deleteExamInfoById(examInfoId);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			System.out.println(result);
			response.getWriter().print(result); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//response.setContentType("text/javascript");
		request.setCharacterEncoding("utf-8");
	//	System.out.println(request.getParameter("examName"));
		String actionName=request.getParameter("action");
		System.out.println(actionName);
		if(actionName.equals("saveExamInfo"))	{
			int result= new ExamInfoServiceImpl().saveExamInfo(request);	
			System.out.println(result);
			 request.getRequestDispatcher("/admin/exam/examInfoList.jsp").forward(request, response);
		}
	}

}

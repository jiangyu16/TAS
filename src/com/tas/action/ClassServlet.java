package com.tas.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.tas.bean.ProgramProblem;
import com.tas.bean.T_Class;
import com.tas.dao.ClassDao;
import com.tas.dao.QuestionDao;
import com.tas.dao.impl.ChapterDaoImpl;
import com.tas.dao.impl.ClassDaoImpl;
import com.tas.dao.impl.QuestionDaoImpl;
import com.tas.service.ClassService;
import com.tas.service.ProgramProblemService;
import com.tas.service.impl.ClassServiceImpl;
import com.tas.service.impl.ProgramProblemServiceImpl;
import com.tas.util.PageControl;


@WebServlet("/ClassServlet")
public class ClassServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		 
		String actionName=request.getParameter("action");
		System.out.println(actionName);
		if (actionName.equals("get_classes")) {
			//int classId = Integer.parseInt(request.getParameter("classId"));
			int curPage = Integer.parseInt(request.getParameter("curPage"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			
			ClassService cs = new ClassServiceImpl();
//			List<T_Class> cls = new ArrayList<T_Class>();
//			ClassDao cd = new ClassDaoImpl();
			
				PageControl<T_Class> pc = cs.getClassByClassId(curPage, pageSize);
				//System.out.println(" "+curPage+" "+ pageSize);
				// ppls =ppd.getProgramProblemByLanguage(courseId, 0, 10);
				ObjectMapper mapper=new ObjectMapper();
				String result= mapper.writeValueAsString(pc);
				 System.out.println(result);
				//System.out.println(pc.getTotalPages());
				response.setContentType("text/javascript");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(result); 
			
		} else if(actionName.equals("search_class")){
			String className = request.getParameter("className");
			int curPage = Integer.parseInt(request.getParameter("curPage"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			ClassService cs = new ClassServiceImpl();
			PageControl<T_Class> pc = cs.getClassByClassName(className, curPage, pageSize);
			System.out.println(pc.getTotalPages());
			ObjectMapper mapper=new ObjectMapper();
			String result= mapper.writeValueAsString(pc);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(result); 
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
	//	request.setCharacterEncoding("UTF-8");
		String actionName=request.getParameter("action");
		if (actionName.equals("update_class")) {
			System.out.println("lll");
			int chapeterId=Integer.parseInt(request.getParameter("classId"));
			String chapterName = request.getParameter("className");
			int res =new ClassDaoImpl().update_chapter(chapeterId, chapterName);
		System.out.println(res);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(res);
			
		}else if (actionName.equals("delete_class")) {
			int classId=Integer.parseInt(request.getParameter("classId"));
			 
			int res =new ClassDaoImpl().delete_class(classId);
		//	System.out.println(res);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().print(res);
		}else if(actionName.equals("new_class")){
			//int classId=Integer.parseInt(request.getParameter("classId"));
			int classId = 1;
			String className = request.getParameter("className");
			int res =new ClassDaoImpl().insert_class(classId, className);
		//	System.out.println(res);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().print(res);
		} 
		
	}

}

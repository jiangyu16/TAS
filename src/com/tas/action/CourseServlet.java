package com.tas.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.tas.bean.Chapter;
import com.tas.bean.Course;
import com.tas.dao.impl.ChapterDaoImpl;
import com.tas.dao.impl.CourseDaoImpl;

/**
 * Servlet implementation class CourseServlet
 * 注解  
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
// int i=5;  int b=i;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doPost(request, response);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
	//	request.setCharacterEncoding("UTF-8");
		 
		String actionName=request.getParameter("action");
		if(actionName.equals("get_course")){
			List<Course> courses=new CourseDaoImpl().getCourses();
			//request.setAttribute("courses", courses);
			ObjectMapper mapper=new ObjectMapper();
			String result= mapper.writeValueAsString(courses);
			System.out.println(result);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().print(result);
		}else if(actionName.equals("get_chapter")){
			int courseId=Integer.parseInt(request.getParameter("courseId"));
			List<Chapter> chapters = new ChapterDaoImpl().get_chapterByCourseId(courseId);
		//	request.setAttribute("chapters",chapters);
			ObjectMapper mapper=new ObjectMapper();
			String result= mapper.writeValueAsString(chapters);
			System.out.println(result);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().print(result);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
	//	request.setCharacterEncoding("UTF-8");
		String actionName=request.getParameter("action");
		if(actionName.equals("new_chapter")){
			int courseId=Integer.parseInt(request.getParameter("courseId"));
			String chapterName = request.getParameter("chapterName");
			int res =new ChapterDaoImpl().insert_chapter(courseId, chapterName);
		//	System.out.println(res);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().print(res);
		}else if(actionName.equals("delete_chapter")){
			int chapterId=Integer.parseInt(request.getParameter("chapterId"));
			 
			int res =new ChapterDaoImpl().delete_chapter(chapterId);
		//	System.out.println(res);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().print(res);
		}else if(actionName.equals("update_chapter")){
			int chapeterId=Integer.parseInt(request.getParameter("chapeterId"));
			String chapterName = request.getParameter("chapterName");
			int res =new ChapterDaoImpl().update_chapter(chapeterId, chapterName);
		//	System.out.println(res);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().print(res);
		}
	//	if(action)
	}

}

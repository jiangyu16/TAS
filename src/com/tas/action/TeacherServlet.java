package com.tas.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.tas.bean.Teacher;
import com.tas.dao.TeacherDao;
import com.tas.dao.impl.TeacherDaoImpl;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherDao teacherDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
        teacherDao = new TeacherDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String actionName = request.getParameter("action");
        if (actionName.equals("get_teacher")) {
            System.out.println("get_teacher");
            List<Teacher> teachers = new TeacherDaoImpl().getTeachers();
            ObjectMapper mapper=new ObjectMapper();
            String result= mapper.writeValueAsString(teachers);
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
		doGet(request, response);
	}

}

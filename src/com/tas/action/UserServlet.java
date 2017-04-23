package com.tas.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tas.bean.Teacher;
import com.tas.service.TeacherService;
import com.tas.service.impl.TeacherServiceImpl;

/**
  
 
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 	this.doPost(request, response);
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println(getRemoteHost(request));
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
	//	request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		if(action.equals("login")){
			String userName=request.getParameter("username");
			String password=request.getParameter("password");
			String userType = request.getParameter("userType");
			if(userType.equals("student")){
				
			}
			else{
				TeacherService teacherService = new TeacherServiceImpl();
				Teacher teacher=teacherService.login(userName, password);
				if(teacher==null){
					 
					//	String result="<font color='red'>验证失败，请重新输入用户名或密码！</font>";
					// response.getWriter().print(result);
					 
					request.getSession().setAttribute("msg", "login failed, please input username and password again！");
					response.sendRedirect("login.jsp"); 
				}else {
					request.getSession().setAttribute("user", teacher);
					response.sendRedirect("UserServlet?action=index");
				}
			}
		}
		if(action.equals("index")){
		//	request.getRequestDispatcher("/AdminMain.jsp").forward(request, response);
			 response.sendRedirect("admin/AdminMain.jsp");
		}
		
	}
	public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	   // System.out.println(ip+" "+"eeee");
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

}

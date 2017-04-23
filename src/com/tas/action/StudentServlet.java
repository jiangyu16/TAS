package com.tas.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.tas.bean.Course;
import com.tas.bean.ProgramProblem;
import com.tas.bean.Student;
import com.tas.dao.impl.CourseDaoImpl;
import com.tas.dao.impl.StudentDaoImpl;
import com.tas.service.ProgramProblemService;
import com.tas.service.StudentService;
import com.tas.service.impl.ProgramProblemServiceImpl;
import com.tas.service.impl.StudentServiceImpl;
import com.tas.util.PageControl;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("action");

		if (actionName.equals("get_students")) {

			String classId =  request.getParameter("classId") ;
			int curPage = Integer.parseInt(request.getParameter("curPage"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));

			StudentService service = new StudentServiceImpl();
			PageControl<Student> studentPc = service.getStudentByClassId(classId, curPage, pageSize);

			ObjectMapper mapper = new ObjectMapper();
			String result = mapper.writeValueAsString(studentPc);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");

			System.out.println(result);

			response.getWriter().print(result);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}

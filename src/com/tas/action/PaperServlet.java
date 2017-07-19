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
import com.tas.bean.Paper;
import com.tas.bean.PaperType;
import com.tas.bean.ProgramProblem;
import com.tas.bean.Student;
import com.tas.dao.PaperDao;
import com.tas.dao.impl.CourseDaoImpl;
import com.tas.dao.impl.PaperDaoImpl;
import com.tas.dao.impl.PaperTypeDaoImpl;
import com.tas.service.PaperService;
import com.tas.service.StudentService;
import com.tas.service.impl.PaperServiceImpl;
import com.tas.service.impl.StudentServiceImpl;
import com.tas.util.PageControl;

@WebServlet("/PaperServlet")
public class PaperServlet extends HttpServlet {
	private PaperDao paperDao;

	public PaperServlet() {
		super();
		paperDao = new PaperDaoImpl();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("action");
	//	request.getParameter("paperType")

		if (actionName.equals("get_problems")) {

			String paperId = request.getParameter("paperId");
			int curPage = Integer.parseInt(request.getParameter("curPage"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));

			PaperService service = new PaperServiceImpl();
			PageControl<ProgramProblem> problemPc = service.getProblemsByPaperId(paperId, curPage, pageSize);

			ObjectMapper mapper = new ObjectMapper();
			String result = mapper.writeValueAsString(problemPc);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");

			System.out.println(result);

			response.getWriter().print(result);
		} else if (actionName.equals("delete_problem")) {
			System.out.println("delete_problem");
			String problemId = request.getParameter("problemId");
			String paperId = request.getParameter("paperId");
			int result = paperDao.delProblem(problemId, paperId);
			response.getWriter().println(result);
		} else if (actionName.equals("get_papertype")) {
		    System.out.println("get_papertype");
		    List<PaperType> papertypes = new PaperTypeDaoImpl().getPaperTypes();
            ObjectMapper mapper=new ObjectMapper();
            String result= mapper.writeValueAsString(papertypes);
            System.out.println(result);
            response.setContentType("text/javascript");
            response.setCharacterEncoding("utf-8");     
            response.getWriter().print(result);
        } else if (actionName.equals("get_papers")) {
            System.out.println("get_papers");
            int paperType = Integer.parseInt(request.getParameter("paperType"));
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            String teacherId = request.getParameter("teacherId");
            int curPage = Integer.parseInt(request.getParameter("curPage"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            PaperServiceImpl service = new PaperServiceImpl();
            PageControl<Paper> paperPc = service.getPapersByAll(paperType, courseId, teacherId, curPage, pageSize);
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(paperPc);
            response.setContentType("text/javascript");
            response.setCharacterEncoding("utf-8");
            System.out.println(result);
            response.getWriter().print(result);
        }

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

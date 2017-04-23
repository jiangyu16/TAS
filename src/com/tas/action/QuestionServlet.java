package com.tas.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.tas.bean.Course;
import com.tas.bean.ProgramProblem;
import com.tas.dao.QuestionDao;
import com.tas.dao.impl.CourseDaoImpl;
import com.tas.dao.impl.QuestionDaoImpl;
import com.tas.service.ProgramProblemService;
import com.tas.service.impl.ProgramProblemServiceImpl;
import com.tas.util.PageControl;

/**
 * Servlet implementation class ProgramProblemServlet
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		 
		String actionName=request.getParameter("action");
		System.out.println(actionName);
		if(actionName.equals("get_programs")){
			int courseId= Integer.parseInt(request.getParameter("courseId"));
			int chapterId=Integer.parseInt(request.getParameter("chapterId"));
			int curPage = Integer.parseInt(request.getParameter("curPage"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			ProgramProblemService pps = new ProgramProblemServiceImpl();
			
			
			 List<ProgramProblem> ppls= new ArrayList<ProgramProblem>();
			 QuestionDao ppd= new QuestionDaoImpl();
			if(chapterId==0){
				PageControl<ProgramProblem> pc = pps.getProgramProblemByLanguage(courseId, curPage, pageSize);
				System.out.println(courseId+" "+curPage+" "+ pageSize);
				// ppls =ppd.getProgramProblemByLanguage(courseId, 0, 10);
				ObjectMapper mapper=new ObjectMapper();
				String result= mapper.writeValueAsString(pc);
				  System.out.println(result);
				response.setContentType("text/javascript");
				response.setCharacterEncoding("utf-8");
				
				response.getWriter().print(result); 
			}else { 
				PageControl<ProgramProblem> pc = pps.getProgramProblemByCourseAndChapter(courseId, chapterId, curPage, pageSize);
				System.out.println(courseId+" "+curPage+" "+ pageSize);
				// ppls =ppd.getProgramProblemByLanguage(courseId, 0, 10);
				ObjectMapper mapper=new ObjectMapper();
				String result= mapper.writeValueAsString(pc);
				  System.out.println(result);
				response.setContentType("text/javascript");
				response.setCharacterEncoding("utf-8");
				
				response.getWriter().print(result); 
			}
		}else if(actionName.equals("save_program")){
			int programProblemId =-1;
			if(request.getParameter("programProblemId")==null);
			else programProblemId=Integer.parseInt(request.getParameter("programProblemId"));
			int courseId=Integer.parseInt(request.getParameter("courseId"));
			int chapterId =Integer.parseInt(request.getParameter("chapterId"));
			System.out.println(chapterId+"   eeeee");
			int languageId = Integer.parseInt(request.getParameter("languageId"));
			String title = request.getParameter("programsTitle");
			String text = request.getParameter("programsContext");
			String scource = request.getParameter("programsSource");
			String answer = request.getParameter("answer");
			int spendTime = Integer.parseInt(request.getParameter("spendTime"));
			ProgramProblem pp = new ProgramProblem();
			pp.setCourseId(courseId);pp.setChapterId(chapterId);pp.setText(text);pp.setTitle(title);
			pp.setSpendTime(spendTime);pp.setScource(scource);pp.setLanguageId(languageId);
			pp.setAnswer(answer);
			pp.setProgramProblemId(programProblemId);
			ProgramProblemService pps = new ProgramProblemServiceImpl();
			
			int result =pps.saveProgramProblem(pp);
			 System.out.println(result);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().print(result);
		}else if(actionName.equals("del_program")){
			 int programProblemId = Integer.parseInt(request.getParameter("programProblemId"));
			 ProgramProblemService pps = new ProgramProblemServiceImpl();
			 int result=pps.delProgramProblem(programProblemId);
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

package com.tas.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tas.bean.Paper;
import com.tas.service.PaperService;
import com.tas.service.impl.PaperServiceImpl;
import com.tas.util.PageControl;

@WebServlet("/PaperServlet")
public class PaperServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private PaperService paperService;
	
	public PaperServlet(){
		super();
	}
	//处理post方法
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		 
		String actionName=request.getParameter("action");
		//获取试卷列表
		if(actionName.equals("get_paperList")){
			int curPage = Integer.parseInt(request.getParameter("curPage"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			//课程ID
			int courseId = Integer.parseInt(request.getParameter("courseId"));
			System.out.println("课程ID         "+courseId);
			//Paper类型
			int paperType = Integer.parseInt(request.getParameter("paperType"));
			System.out.println("试卷类型         "+paperType);
			//用户ID
			String teacherId = request.getParameter("teacherId");
			
			//对前台传递的参数进行判断
			Paper tempPaper = new Paper();
			if(courseId!=0){
				tempPaper.setCourseId(courseId);
			}
			if(!teacherId.trim().equals("")&&teacherId!=null){
				tempPaper.setTeacherId(teacherId);
			}else{
				tempPaper.setTeacherId("");
			}
			if(paperType!=0){
				tempPaper.setPaperType(paperType);
			}
			//调用Service层的方法
			paperService = new PaperServiceImpl();
			System.out.println(tempPaper);
			PageControl<Paper> p = paperService.getPaperList(tempPaper, curPage, pageSize);
			
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			//返回PageControl对象
			response.getWriter().print(p); 
		}	
	
	}
}

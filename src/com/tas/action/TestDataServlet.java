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
import com.tas.bean.TestData;
import com.tas.dao.TestDataDao;
import com.tas.dao.impl.CourseDaoImpl;
import com.tas.dao.impl.TestDataDaoImpl;

/**
 * Servlet implementation class TestDataServlet
 */
@WebServlet("/TestDataServlet")
public class TestDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDataServlet() {
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
		if(actionName.equals("save_testData")){
			int result=0;
			int programProblemId = Integer.parseInt(request.getParameter("programProblemId"));
			String testDataInput = request.getParameter("testDataInput");
			String testDataOutput = request.getParameter("testDataOutput");
			int percent = Integer.parseInt(request.getParameter("percent"));
			String reason = request.getParameter("reason");
			TestData td = new TestData();
			td.setInput(testDataInput);td.setOutput(testDataOutput);
			td.setPercentage(percent); td.setProgramId(programProblemId);
			td.setReason(reason);
			int testdataId=Integer.parseInt(request.getParameter("testDataId"));
			if(testdataId==-1){
				//System.out.println("eeeewaa");
				// insert data
				
				td.setTestdataId(testdataId);
				TestDataDao tddi = new TestDataDaoImpl();
				result =tddi.insertTestData(td);
				response.setContentType("text/javascript");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(result);
			}else  {//update data
				   
			// System.out.println(request.getParameter("testDataId"));	
			}
			 
			/*List<Course> courses=new CourseDaoImpl().getCourses();
			//request.setAttribute("courses", courses);
			ObjectMapper mapper=new ObjectMapper();
			String result= mapper.writeValueAsString(courses);
			//System.out.println(result);
			response.setContentType("text/javascript");
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().print(result);*/
		}else  if(actionName.equals("get_testDataList")){ 
			 int  programProblemId = Integer.parseInt(request.getParameter("programProblemId"));
			 TestDataDao tddi = new TestDataDaoImpl();
			 List<TestData> testDatas=tddi.getTestDataByProgramId(programProblemId);
			 ObjectMapper mapper=new ObjectMapper();
				String  result= mapper.writeValueAsString(testDatas);
				//System.out.println("testdata come£º"+result);
				response.setContentType("text/javascript");
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(result);
		// System.out.println(request.getParameter("testDataId"));	
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

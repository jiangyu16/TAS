package com.tas.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.tas.bean.Student;
import com.tas.service.impl.StudentServiceImpl;

@WebServlet("/FileInputServlet")
public class FileInputServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentServiceImpl studentServiceImpl=new StudentServiceImpl();
	// 无参构造方法
	public FileInputServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 操作excel表格读取单元格值的方法
	private String getValue(Cell cell) {
		if (cell == null)
			return "";
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue()).trim();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue()).trim();
		} else {
			return String.valueOf(cell.getStringCellValue()).trim();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("action");
		
		//excel文件导入数据库action
		if (actionName.equals("fileinput")) {
			// 判断enctype属性是否为multipart/form-data
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置上传内容的大小限制（单位：字节）
			upload.setSizeMax(1024 * 1024 * 100);
			// 获取请求中上传的文件集合
			List<?> items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Iterator<?> iter = items.iterator();
			// 用于存储信息的List
			List<Student> stuList = new ArrayList<Student>();
			String classId ="";
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					// 如果是普通表单字段
					String name = item.getFieldName();

					String value = item.getString();
					//接收页面传来的classId			
					if(name.equals("classId")){
						classId=value;
					}
					
				} else {
					// 如果是文件字段
					String fieldName = item.getFieldName();
					String realFilename = item.getName();
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize();
					response.setContentType("text/javascript");
					response.setCharacterEncoding("utf-8");
					// 返回一个有效的json串，告诉页面上传成功
					response.getWriter().print("{}");
					// 对上传的文件进行处理
					// 如果是xml文件，将其导入数据库中(两种格式xml,xmls)
					if (contentType.equals("application/vnd.ms-excel") || contentType
							.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
						// 解析excel
						Workbook wookbook = null;
						// 将Excel的各行记录放入ImpExcelBean的list里面
						try {
							// WorkbookFactory是用来将Excel内容导入数据库的一个类
							wookbook = WorkbookFactory.create(item.getInputStream());
							Sheet sheet = wookbook.getSheetAt(0);// 获取第一张表
							int rowLen = sheet.getLastRowNum();// excel总行数
							Student student;
							
							// 导入各条记录
							// rowLen需要加1不然最后一行读取不到
							for (int i = 0; i < rowLen+1; i++) {
								Row row = sheet.getRow(i);
								student = new Student();
								int startCol = 0;
								// 将Excel中各行记录依次导入到Student的list中
								if (row != null) {
									// 学号
									String studentId = getValue(row.getCell(startCol++));
									// 解决科学记数法问题
									if (!studentId.equals("学号")) {
										BigDecimal bd = new BigDecimal(studentId);
										studentId = bd.toPlainString();
									}
									student.setStudentId(studentId);
									// 姓名
									String stuName = getValue(row.getCell(startCol++));
									student.setStuName(stuName);
									
								}else if(row == null){//判断是否为空,如果为空,就continue,避免因为空行导致数据的缺失
									continue;
								}
								if (student.getStudentId()!=null && !student.getStudentId().equals("") && !student.getStudentId().equals("学号")) {
									stuList.add(student);
								}
								
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}

			}
			//为所有的student classId赋值
			Iterator iterator = stuList.iterator();
			while(iterator.hasNext()){
				Student student = (Student) iterator.next();
				if(!classId.trim().equals("")){
					student.setClassId(Integer.parseInt(classId));
				}
			}
			studentServiceImpl.addExcelData(stuList);
			//System.out.println(stuList);
			//studentservice.addExcelData(stuList);
		}
	}

}

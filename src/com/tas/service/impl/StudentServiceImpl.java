package com.tas.service.impl;

import java.util.Iterator;
import java.util.List;

import com.tas.bean.Student;
import com.tas.dao.StudentDao;
import com.tas.dao.impl.StudentDaoImpl;
import com.tas.service.StudentService;
import com.tas.util.PageControl;

public class StudentServiceImpl implements StudentService{
	private StudentDaoImpl studentdao;
	
	//将Servlet传过来的数据传递给dao层
	public void addExcelData(List<Student> stuList){
		//遍历List,查询数据库中是否已经存在学号相同的数据,若有,则不添加
		Iterator iter = stuList.iterator();
		int success=0;//成功的数量
		int total=0;//总执行数据量
		int fail;//失败的数量
		while(iter.hasNext()){
			Student student=(Student) iter.next();
			System.out.println(student);
			studentdao=new StudentDaoImpl();
			//先查询该id是否已存在,若不存在将student插入
			System.out.println("正在插入...............");
			int i = studentdao.insertStudent(student);
			if(i==1){
				success++;
			}
			total++;		
		}
		fail=total-success;
		System.out.println("总共执行插入"+total+"条数据,"+success+"条成功,"+fail+"条失败");
	}



	@Override
	public PageControl<Student> getStudentByClassId(String classId, int curPage, int pageSize) {
		int offset, fetch;

		fetch = pageSize;
		offset = (curPage - 1) * pageSize;
		// System.out.println("ser" +offset+" "+ fetch);
		StudentDao qd = new StudentDaoImpl();
		int totalRows = qd.getAllgetStudent(classId);
		List<Student> students = qd.getAllgetStudent(classId, offset, fetch);
		//System.out.println("2222" + students.size());
		PageControl<Student> pc = new PageControl<Student>(curPage, totalRows, pageSize);
		pc.setList(students);
		return pc;
	}
}

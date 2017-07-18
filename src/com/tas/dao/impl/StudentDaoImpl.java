package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tas.bean.ProgramProblem;
import com.tas.bean.Student;
import com.tas.dao.StudentDao;
import com.tas.db.DBPool;

public class StudentDaoImpl implements StudentDao {

	@Override
	public List<Student> getStudents(String classId) {
		// TODO Auto-generated method stub
		List<Student> studentList = new ArrayList<Student>();
		DBPool dbpool = new DBPool();
		String sql = "SELECT * from dbo.T_Student where classId=?";
		ResultSet rs = null;
		Student student = null;
		try {
			rs = dbpool.doQueryRS(sql, new Object[] { classId });
			while (rs.next()) {
				student = new Student();
				student.setStudentId(rs.getString("studentId"));
				student.setStuName(rs.getString("stuName"));
				student.setPassword(rs.getString("password"));
				studentList.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbpool.close();
		}

		return studentList;
	}

	@Override
	public int getAllgetStudent(String courseId) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		int totalStudent = 0;
		String sql = "select count(*) as totalStudent from T_Student where classId=?";
		ResultSet rs = null;
		try {
			rs = dbpool.doQueryRS(sql, new Object[] { courseId });
			if (rs.next())
				totalStudent = rs.getInt("totalStudent");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbpool.close();
		}
		return totalStudent;
	}

	@Override
	public List<Student> getAllgetStudent(String classId, int offset, int fetch) {

		DBPool dbPool = new DBPool();
		String sql = "SELECT *,ROW_NUMBER() OVER(ORDER BY [studentId] asc) AS num " + "from dbo.T_Student "
				+ "where classId=? " + "ORDER BY num OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY ";

		List<Student> students = new ArrayList<>();
		ResultSet rs = null;
		Student student = null;

		try {
			rs = dbPool.doQueryRS(sql, new Object[] { classId, offset, fetch });
			while (rs.next()) {
				student = new Student();
				student.setStudentId(rs.getString("studentId"));
				student.setPassword(rs.getString("password"));
				student.setStuName(rs.getString("stuName"));
				students.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbPool.close();
		}
		return students;
	}

	@Override
	public int delStudent(String studentId) {

		DBPool dbPool = new DBPool();
		String sql = "delete from T_Student where studentId=? ";
		int result = 0;

		try {
			result = dbPool.doUpdate(sql, new Object[] { studentId });
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbPool.close();
		}
		System.out.println(result);
		return result;
	}

	@Override
	public int resetPassword(String studentId) {

		DBPool dbPool = new DBPool();
		String sql = "update T_Student set password= ? where studentId=? ";
		int result = 0;

		try {
			result = dbPool.doUpdate(sql, new Object[] { studentId ,studentId});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbPool.close();
		}

		return result;
	}
	@Override
	public int insertStudent(Student student) {
		// TODO Auto-generated method stub
		int i = 1;
		DBPool dbpool = new DBPool();
		String sql = "SET NOCOUNT ON insert into t_student values(?,?,?,?,?)";
		try {
			dbpool.doUpdate(sql, new Object[]{student.getStudentId()
					,student.getStudentId(),student.getStuName(),student.getClassId(),student.getLastLoginIp()});
			System.out.println("insert success ");
		} catch (SQLException e) {
			System.out.println("isert failed");
			i=0;
		}finally{
			dbpool.close();
		}
		return i;
	}

	@Override
	public int insertStuents(Student[] students) {
		// TODO Auto-generated method stub
		int i = 1;
		DBPool<Student> dbpool = new DBPool<Student>();
		//String sql = "SET NOCOUNT ON insert into t_student values(?,?,?,?,?)";
		String sql = " insert into t_student values(?,?,?,?,?)";
		Map<Integer,Object> paramMap=new HashMap<Integer,Object>();
	//	paramMap.put(1, examInfoId);
		Map<Integer,String> methodMap = new HashMap<Integer,String>();
		methodMap.put(1, "getStudentId");
		methodMap.put(2, "getStudentId");methodMap.put(3, "getStuName");
		methodMap.put(4, "getClassId");methodMap.put(5, "getLastLoginIp");
		try {
			i= dbpool.doBatch(sql, Student.class, students, methodMap, paramMap);
			System.out.println("insert success ");
		} catch (SQLException e) {
			System.out.println("isert failed");
			//i=0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbpool.close();
		}
		//System.out.println(x);
		return i;
		
	//	return 0;
	}
	
	
}
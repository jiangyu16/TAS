package com.tas.bean;

public class Student {
	private String studentId;
	private String password;
	private String stuName;
	private int classId;
	private String lastLoginIp;
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
//	public String toString(){
//		return "学号:"+studentId+"  "+"姓名:"+stuName+"	"+"班级:"+classId+"   "+"最后登陆Ip"+lastLoginIp;
//	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", password=" + password + ", stuName=" + stuName + ", classId="
				+ classId + ", lastLoginIp=" + lastLoginIp + "]";
	}
	
}

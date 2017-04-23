package com.tas.bean;

public class T_Class {
	
	int classId;
	String className;
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	@Override
	public String toString() {
		return "Class [classId=" + classId + ", className=" + className + "]";
	}

}

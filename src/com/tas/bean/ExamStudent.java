package com.tas.bean;

public class ExamStudent {
	String studentId;
	String examInfoId;
	int programScore;
	int choiceScore;
	String studentName;
	String classId;
	String className;
	String examName;
	boolean checked;//前台接收数据用

	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public int getProgramScore() {
		return programScore;
	}
	public void setProgramScore(int programScore) {
		this.programScore = programScore;
	}
	public int getChoiceScore() {
		return choiceScore;
	}
	public void setChoiceScore(int choiceScore) {
		this.choiceScore = choiceScore;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getExamInfoId() {
		return examInfoId;
	}
	public void setExamInfoId(String examInfoId) {
		this.examInfoId = examInfoId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
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
		return "ExamStudent [studentId=" + studentId + ", examInfoId=" + examInfoId + ", programScore=" + programScore
				+ ", choiceScore=" + choiceScore + ", studentName=" + studentName + ", classId=" + classId
				+ ", className=" + className + ", examName=" + examName +  ", checked=" + checked +"]";
	}
	

}

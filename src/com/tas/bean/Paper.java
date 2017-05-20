package com.tas.bean;

public class Paper {
	private int paperId;
	private String paperName;
	private int programScore;
	private int choiceScore;
	private String teacherId;
	private int paperType;
	private boolean isVisable;
	private int courseId;
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
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
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public int getPaperType() {
		return paperType;
	}
	public void setPaperType(int paperType) {
		this.paperType = paperType;
	}
	public boolean isVisable() {
		return isVisable;
	}
	public void setVisable(boolean isVisable) {
		this.isVisable = isVisable;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	
}

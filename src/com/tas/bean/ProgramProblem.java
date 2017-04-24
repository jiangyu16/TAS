package com.tas.bean;

import java.util.List;

public class ProgramProblem {
	int programProblemId=-1;
	int courseId;
	int chapterId;
	String title;
	String text;// 
	String scource;// 
	int spendTime;// 
	int languageId;
	int programType;
	
	// 
	List<TestData> testDataList;
	String courseName;
	String chapterName;
	String language;
	String answer;
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getProgramProblemId() {
		return programProblemId;
	}
	public void setProgramProblemId(int programProblemId) {
		this.programProblemId = programProblemId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getScource() {
		return scource;
	}
	public void setScource(String scource) {
		this.scource = scource;
	}
	public int getSpendTime() {
		return spendTime;
	}
	public void setSpendTime(int spendTime) {
		this.spendTime = spendTime;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	public List<TestData> getTestDataList() {
		return testDataList;
	}
	public void setTestDataList(List<TestData> testDataList) {
		this.testDataList = testDataList;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	public int getProgramType() {
		return programType;
	}
	public void setProgramType(int programType) {
		this.programType = programType;
	}
	@Override
	public String toString() {
		return "ProgramProblem [programProblemId=" + programProblemId + ", courseId=" + courseId + ", chapterId="
				+ chapterId + ", title=" + title + ", text=" + text + ", scource=" + scource + ", spendTime="
				+ spendTime + ", languageId=" + languageId + ", testDataList=" + testDataList + ", courseName="
				+ courseName + ", chapterName=" + chapterName + ", language=" + language + "]";
	}
	

}

package com.tas.bean;

import java.sql.Date;

public class ExamInfo {
	int examInfoId;
	String examName;
	int paperId;
	String startTime;
	String endTime;
	int programScore;
	int choiceScore;
	int type;//类型
	String typeName;
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getExamInfoId() {
		return examInfoId;
	}
	public void setExamInfoId(int examInfoId) {
		this.examInfoId = examInfoId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "ExamInfo [examInfoId=" + examInfoId + ", examName=" + examName + ", paperId=" + paperId + ", startTime="
				+ startTime + ", endTime=" + endTime + ", programScore=" + programScore + ", choiceScore=" + choiceScore
				+ ", type=" + type + ", typeName=" + typeName + "]";
	}
	
}

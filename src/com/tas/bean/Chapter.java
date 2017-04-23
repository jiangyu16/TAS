package com.tas.bean;

public class Chapter {
	int courseId;
	int chapterId;
	String chapterName;
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
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	@Override
	public String toString() {
		return "Chapter [courseId=" + courseId + ", chapterId=" + chapterId + ", chapterName=" + chapterName + "]";
	}
	
}

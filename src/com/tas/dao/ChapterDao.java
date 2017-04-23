package com.tas.dao;

import java.util.List;

import com.tas.bean.Chapter;

public interface ChapterDao {
	public List<Chapter> get_chapterByCourseId(int courseId);
	public int insert_chapter(int courseId, String chapterName);
	public int delete_chapter(  int chapterId);
	public int update_chapter(  int chapterId,String chapterName);
}

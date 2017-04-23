package com.tas.test;

import java.util.Iterator;
import java.util.List;

import com.tas.bean.Chapter;
import com.tas.dao.ChapterDao;
import com.tas.dao.impl.ChapterDaoImpl;

public class TestChapter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChapterDao cd = new ChapterDaoImpl();
		List<Chapter> cls=cd.get_chapterByCourseId(1);
		Iterator<Chapter> it = cls.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}

}

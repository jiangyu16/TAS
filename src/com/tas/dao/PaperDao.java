package com.tas.dao;

import java.util.List;

import com.tas.bean.Paper;

public interface PaperDao {
	public List <Paper> getPaperList(Paper paper,int offset, int fetch); 
	public int getPaperListNum(Paper paper); 
	public int updatePaper(Paper paper);
}

package com.tas.service.impl;

import java.util.List;

import com.tas.bean.Paper;
import com.tas.dao.PaperDao;
import com.tas.dao.impl.PaperDaoImpl;
import com.tas.service.PaperService;
import com.tas.util.PageControl;

public class PaperServiceImpl implements PaperService{
	private PaperDao paperDao = new PaperDaoImpl();
	@Override
	public PageControl<Paper> getPaperList(Paper paper, int curPage, int pageSize) {
		// TODO Auto-generated method stub
		 int offset,fetch;
		 fetch=pageSize;
		 offset=(curPage-1)*pageSize;
		 List<Paper> paperlist;
		 PageControl<Paper> pc=null;
		 //System.out.println("ser" +offset+" "+ fetch);
		 int totalRows;
		 totalRows = paperDao.getPaperListNum(paper);
		 System.out.println(totalRows);
		 paperlist = paperDao.getPaperList(paper, offset, fetch);
		 pc=new PageControl<Paper>(curPage,totalRows,pageSize);
		 pc.setList(paperlist);
		return pc;
	}

}

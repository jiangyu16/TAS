package com.tas.util;
import java.util.ArrayList;
import java.util.List;
public class PageControl <T>{
	private int curPage = 1;
	private int pageSize;
	private int totalRows;
	private int totalPages;	
	private List<T> list;//用于存放分页数据
	private List<Integer> pageList=new ArrayList<Integer>();
	private int pageListSize=5;//分页显示的页面总数
	
	public List<Integer> getPageList() {
		return pageList;
	}


	


	public PageControl(int curPageStr, int totalRows,int pageSize) {
		
		 
			this.curPage =  curPageStr ;    //初始化当前页数
		 	
		this.totalRows = totalRows;							//初始化总行数		
		this.pageSize=pageSize;//设置每页显示的记录数
		
		//计算总页数
		this.totalPages = (this.totalRows / this.pageSize) + ((this.totalRows % this.pageSize) > 0 ? 1 : 0);
	
	//	System.out.println(this.totalPages);
		if(curPage>totalPages)curPage=totalPages;//当前页如果超过最大页
		 pageList.clear();
		 int i= (curPage-1)/5;
		 int j;
		 
		 for(j=1;j<=pageListSize&&(i*5+j)<=(totalPages);j++)pageList.add(i*5+j);
		
	}
	

	public List<T> getList() {
		return list;
	}

	public int getCurPage() {
		return curPage;
	}


	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	public void setList(List<T> list) {
		this.list = list;
	}


	public int getTotalRows() {
		return totalRows;
	}


	 
	
}

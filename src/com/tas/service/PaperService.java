package com.tas.service;

import com.tas.bean.Paper;
import com.tas.util.PageControl;

public interface PaperService {
	public PageControl<Paper> getPaperList(Paper paper, int curPage, int pageSize);
}

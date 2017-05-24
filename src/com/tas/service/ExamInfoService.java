package com.tas.service;

import javax.servlet.http.HttpServletRequest;

import com.tas.bean.ExamInfo;
import com.tas.util.PageControl;

public interface ExamInfoService {
	public int saveExamInfo(HttpServletRequest request);
	public ExamInfo getExamInfoById(int examInfoId);
	public PageControl<ExamInfo> getAllExamInfos(HttpServletRequest request);
	public int deleteExamInfoById(int examInfoId);
}

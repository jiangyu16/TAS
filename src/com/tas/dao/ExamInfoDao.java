package com.tas.dao;

import java.util.List;

import com.tas.bean.ExamInfo;

public interface ExamInfoDao {
    public int insert_ExamInfo(ExamInfo examInfo);
    public int update_ExamInfo(ExamInfo examInfo);
    public List<ExamInfo> getAllExamInfos(int curPage, int pageSize);
    public int getAllExamInfosNum();
    public ExamInfo  get_ExamInfoById(int examInfoId);
    public int deleteExamInfoById(int examInfoId);
}

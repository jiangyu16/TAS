package com.tas.dao;

import java.util.List;

import com.tas.bean.Paper;
import com.tas.bean.ProgramProblem;

public interface PaperDao {

	public int queryProblems(String paperId);

	public List<ProgramProblem> queryProblems(String paperId, int curPage, int pageSize);

	public int delProblem(String problemId, String paperId);
	
	
    /**   
    * @Function: PaperDao.java
    * @Description: 根据三个条件动态地得到结果
    *
    * @param:描述1描述
    * @return：返回结果描述
    * @throws：异常描述
    *
    * @version: v1.0.0
    * @author: Michael
    * @date: 2017年7月6日 下午5:16:12 
    *
    * Modification History:
    * Date         Author          Version            Description
    *---------------------------------------------------------*
    * 2017年7月6日     Michael           v1.0.0               修改原因
    */
    List<Paper> getPapersByAll(int paperType, int courseId, String teacherId, int offset,
            int fetch);
    int getPapersByAll(int paperType, int courseId, String teacherId);

}

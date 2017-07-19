package com.tas.service.impl;

import java.util.List;

import com.tas.bean.Paper;
import com.tas.bean.ProgramProblem;
import com.tas.bean.Student;
import com.tas.dao.PaperDao;
import com.tas.dao.StudentDao;
import com.tas.dao.impl.PaperDaoImpl;
import com.tas.dao.impl.StudentDaoImpl;
import com.tas.service.PaperService;
import com.tas.util.PageControl;

public class PaperServiceImpl implements PaperService {

	@Override
	public PageControl<ProgramProblem> getProblemsByPaperId(String paperId, int curPage, int pageSize) {
		int offset, fetch;

		fetch = pageSize;
		offset = (curPage - 1) * pageSize;
		// System.out.println("ser" +offset+" "+ fetch);
		PaperDao pd = new PaperDaoImpl();
		int totalRows = pd.queryProblems(paperId);
		List<ProgramProblem> problems = pd.queryProblems(paperId, offset, fetch);
		// System.out.println("2222" + students.size());
		PageControl<ProgramProblem> pc = new PageControl<ProgramProblem>(curPage, totalRows, pageSize);
		pc.setList(problems);
		return pc;
	}
	
    public PageControl<Paper> getPapersByAll(int paperType, int courseId, String teacherId,
            int curPage, int pageSize) {
        int offset, fetch;
        fetch = pageSize;
        offset = (curPage - 1) * pageSize;
        PaperDao pd = new PaperDaoImpl();
        List<Paper> papers = pd.getPapersByAll(paperType, courseId, teacherId, offset, fetch);
        int totalRows = pd.getPapersByAll(paperType, courseId, teacherId);
        PageControl<Paper> pc = new PageControl<>(curPage, totalRows, pageSize);
        pc.setList(papers);
        return pc;
    }

}

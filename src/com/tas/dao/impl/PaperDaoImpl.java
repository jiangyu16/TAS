package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tas.bean.Paper;
import com.tas.bean.ProgramProblem;
import com.tas.bean.Student;
import com.tas.dao.PaperDao;
import com.tas.db.DBPool;

public class PaperDaoImpl implements PaperDao {

	@Override
	public int queryProblems(String paperId) {
		// TODO Auto-generated method stub
		DBPool dbpool = new DBPool();
		int totalProblem = 0;
		String sql = "SELECT count(*) as totalStudent from dbo.T_ProgramPaper a,dbo.T_ProgramProblem b where a.programProblemId=b.programProblemId and paperId=?";
		ResultSet rs = null;
		try {
			rs = dbpool.doQueryRS(sql, new Object[] { paperId });
			if (rs.next())
				totalProblem = rs.getInt("totalStudent");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbpool.close();
		}
		return totalProblem;
	}

	@Override
	public List<ProgramProblem> queryProblems(String paperId, int offset, int fetch) {

		DBPool dbPool = new DBPool();

		String sql = "SELECT *,ROW_NUMBER() OVER(ORDER BY a.programProblemId asc)  AS num "
				+ "from dbo.T_ProgramPaper a,dbo.T_ProgramProblem b,dbo.T_Chapter c "
				+ "where a.programProblemId=b.programProblemId and paperId=? and b.chapter=c.chapterId and b.courseId=c.courseId "
				+ "ORDER BY num OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY ";

		List<ProgramProblem> problems = new ArrayList<>();
		ResultSet rs = null;
		ProgramProblem problem = null;

		try {
			rs = dbPool.doQueryRS(sql, new Object[] { paperId, offset, fetch });
			while (rs.next()) {
				problem = new ProgramProblem();
				problem.setProgramProblemId(rs.getInt("programProblemId"));
				problem.setCourseId(rs.getInt("courseId"));
				problem.setChapterId(rs.getInt("chapter"));
				problem.setChapterName(rs.getString("chapterName"));
				problem.setTitle(rs.getString("title"));
				problem.setText(rs.getString("text"));
				problem.setScource(rs.getString("source"));
				problem.setSpendTime(rs.getInt("spendTime"));
				problem.setLanguageId(rs.getInt("languageId"));
				problem.setAnswer(rs.getString("answer"));
				problems.add(problem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbPool.close();
		}
		return problems;
	}

	@Override
	public int delProblem(String problemId, String paperId) {

		DBPool dbPool = new DBPool();
		String sql = "delete from T_ProgramPaper where programProblemId=? and paperId=?";
		int result = 0;

		try {
			result = dbPool.doUpdate(sql, new Object[] { problemId, paperId });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

    

   
    @Override
    public List<Paper> getPapersByAll(int paperType, int courseId, String teacherId, int offset,
            int fetch) {
        //System.out.println("teacherId："+teacherId);
        StringBuffer sb = null;
        sb = new StringBuffer();
        sb.append(
                "SELECT paperId,paperName,programScore,choiceScore,a.teacherId,a.paperType,isVisable,a.courseId FROM T_Paper a ");
        if (paperType != 0) {
            sb.append(",T_PaperType b ");
        }
        if (courseId != 0) {
            sb.append(",T_Course c ");
        }
        if (!teacherId.equals("0")) {
            sb.append(",T_Teacher d ");
        }
        sb.append("WHERE 1 = 1 ");
        List list = null;
        list = new ArrayList<>();
        if (paperType != 0) {
            list.add(paperType);
            sb.append("AND a.paperType= b.paperTypeId ");
            sb.append("AND a.paperType = ? ");
        }
        if (courseId != 0) {
            list.add(courseId);
            sb.append("AND a.courseId = c.courseId ");
            sb.append("AND a.courseId = ? ");
        }
        if (!teacherId.equals("0")) {
            list.add(teacherId);
            sb.append("AND a.teacherId = d.teacherId ");
            sb.append("AND a.teacherId = ? ");
        }
        sb.append("ORDER BY paperId OFFSET ? ROWS  FETCH NEXT ? ROWS ONLY");
        list.add(offset);
        list.add(fetch);
        Object[] objects = list.toArray();
//        for (int i = 0; i < objects.length; i++) {
//            System.out.println(objects[i]);
//        }
        DBPool dbPool = new DBPool();
        String sql = new String(sb);
        List<Paper> papers = new ArrayList<>();
        ResultSet rs = null;
        Paper paper = null;
        try {
            rs = dbPool.doQueryRS(sql, objects);
            while (rs.next()) {
               paper = new Paper();
               paper.setPaperId(rs.getInt("paperId"));
               paper.setPaperName(rs.getString("paperName"));
               paper.setProgramScore(rs.getInt("programScore"));
               paper.setChoiceScore(rs.getInt("choiceScore"));
               paper.setTeacherId(rs.getString("teacherId"));
               paper.setPaperType(rs.getInt("paperType"));
               paper.setIsVisable(rs.getBoolean("isVisable"));
               paper.setCourseId(rs.getInt("courseId"));
               papers.add(paper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbPool.close();
        }
        return papers;
    }
    
    public int getPapersByAll(int paperType, int courseId, String teacherId) {
        StringBuffer sb = null;
        sb = new StringBuffer();
        sb.append(
                "SELECT count(*) AS totalPaper FROM T_Paper a ");
        if (paperType != 0) {
            sb.append(",T_PaperType b ");
        }
        if (courseId != 0) {
            sb.append(",T_Course c ");
        }
        if (!teacherId.equals("0")) {
            sb.append(",T_Teacher d ");
        }
        sb.append("WHERE 1 = 1 ");
        List list = null;
        list = new ArrayList<>();
        if (paperType != 0) {
            list.add(paperType);
            sb.append("AND a.paperType= b.paperTypeId ");
            sb.append("AND a.paperType = ? ");
        }
        if (courseId != 0) {
            list.add(courseId);
            sb.append("AND a.courseId = c.courseId ");
            sb.append("AND a.courseId = ? ");
        }
        if (!teacherId.equals("0")) {
            list.add(teacherId);
            sb.append("AND a.teacherId = d.teacherId ");
            sb.append("AND a.teacherId = ? ");
        }
        ResultSet rs = null;
        Object[] objects = list.toArray();
        DBPool dbPool = new DBPool();
        String sql = new String(sb);
        int totalPaper = 0;
        try {
            rs = dbPool.doQueryRS(sql, objects);
            if (rs.next())
                totalPaper = rs.getInt("totalPaper");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            dbPool.close();
        }
        return totalPaper;
        
    }
    
}

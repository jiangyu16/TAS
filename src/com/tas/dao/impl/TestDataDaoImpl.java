package com.tas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tas.bean.Course;
import com.tas.bean.TestData;
import com.tas.dao.TestDataDao;
import com.tas.db.DBPool;

public class TestDataDaoImpl implements TestDataDao {

	@Override
	public int insertTestData(TestData td) {
		// TODO Auto-generated method stub
		DBPool dbpool= new DBPool();
		String sql = "insert into [T_Testdata] ([programId],[input],[output],[percentage],[reason])  values (?,?,?,?,?) ";
		int result=0;
		 			try {
						result = dbpool.doUpdate(sql, new Object[]{td.getProgramId(), td.getInput(),td.getOutput(),td.getPercentage(),td.getReason()});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						dbpool.close();
					}
	 
		
		return result;
	}

	@Override
	public int delTestData(int testDataId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updataTestData(TestData td) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TestData> getTestDataByProgramId(int programProblemId) {
		// TODO Auto-generated method stub
		 List<TestData> testDataList= new  ArrayList<TestData>();
		 DBPool dbpool = new DBPool();
			//Connection conn=dbpool.getConnection();
			String sql="SELECT TOP 1000 [testdataId] ,[programId] ,[input],[output] ,[percentage],[reason] FROM [db_exam].[dbo].[T_Testdata] "
					+ "where programId=?";
			ResultSet rs=null; 
			TestData td=null;
			try {
				rs=dbpool.doQueryRS(sql, new Object[]{programProblemId });
				while (rs.next()) {
					td= new TestData();
					 td.setInput(rs.getString("input"));
					 td.setOutput(rs.getString("output"));
					 td.setPercentage(rs.getInt("percentage"));
					 td.setProgramId(rs.getInt("programId"));
					 td.setTestdataId(rs.getInt("testdataId"));
					 td.setReason("reason");
					 testDataList.add(td);
				} 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				dbpool.close();
			}
			
			 
		return testDataList;
	}

}

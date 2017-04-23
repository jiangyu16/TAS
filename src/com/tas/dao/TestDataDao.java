package com.tas.dao;

import java.util.List;

import com.tas.bean.TestData;

public interface TestDataDao {
	public int insertTestData(TestData td);
	public int delTestData(int testDataId);
	public int updataTestData(TestData td);
	public List<TestData> getTestDataByProgramId(int programProblemId);
}

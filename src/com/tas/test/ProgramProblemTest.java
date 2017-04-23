package com.tas.test;

import java.util.Iterator;
import java.util.List;

import com.tas.bean.ProgramProblem;
import com.tas.dao.QuestionDao;
 
import com.tas.dao.impl.QuestionDaoImpl;

public class ProgramProblemTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuestionDao ppd= new QuestionDaoImpl();
		int i=ppd.getAllProgramProblem(2);
		System.out.println(i);
//		List<ProgramProblem> list =  ppd.getProgramProblemByLanguage(1,2,2);
//		Iterator<ProgramProblem> it= list.iterator();
//		while(it.hasNext()){
//			ProgramProblem pp=it.next();
//			System.out.println(pp.toString());
//		}
	}

}

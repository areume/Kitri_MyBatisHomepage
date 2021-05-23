package com.java.sawon.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.java.myBatis.SqlManager;

public class SawonDAO {
	// MyBatis
	private static SqlSessionFactory sqlSessionFactory=SqlManager.getInstance();
	private SqlSession sqlSession;	
	
	private static SawonDAO instance=new SawonDAO();
	public static SawonDAO getInstance() {
		return instance;
	}
	
	public List<SawonDTO> select(String departmentName) {
		List<SawonDTO> sawonList=null;
		
		try {
			sqlSession=sqlSessionFactory.openSession();
			sawonList=sqlSession.selectList("sawonList",departmentName);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return sawonList;
	}
	
}

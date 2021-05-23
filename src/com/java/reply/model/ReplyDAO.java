package com.java.reply.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.java.myBatis.SqlManager;

public class ReplyDAO {

	private SqlSessionFactory sqlSessionFactory=SqlManager.getInstance();
	private SqlSession sqlSession;
	
	private static ReplyDAO instance=new ReplyDAO();
	public static ReplyDAO getInstance() {
		return instance;
	}
	
	public int insert(ReplyDTO replydto) {
		int value=0;
		
		try {
			sqlSession=sqlSessionFactory.openSession();
			value=sqlSession.insert("reply_writer",replydto);
			sqlSession.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}		
		return value;
	}

	public int getBunho() {
		int value=0;
		
		try {
			sqlSession=sqlSessionFactory.openSession();
			value=sqlSession.selectOne("reply_bunho");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return value;
	}

	public List<ReplyDTO> getList() {		
		List<ReplyDTO> list=null;
		
		try {
			sqlSession=sqlSessionFactory.openSession();
			list=sqlSession.selectList("reply_list");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		
		return list;
	}

	public int delete(int bunho) {
		int value=0;
		
		try {
			sqlSession=sqlSessionFactory.openSession();
			value=sqlSession.delete("reply_delete",bunho);
			sqlSession.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return value;
	}

	public ReplyDTO select(int bunho) {
		ReplyDTO dto=null;
		
		try {
			sqlSession=sqlSessionFactory.openSession();
			dto=sqlSession.selectOne("reply_select",bunho);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return dto;
	}

	public int update(int bunho, String linereply) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("bunho",bunho);
		map.put("linereply",linereply);
		int value=0;
		
		try {
			sqlSession=sqlSessionFactory.openSession();
			value=sqlSession.update("reply_update",map);
			sqlSession.commit();
			
		} catch(Exception e) {
			e.printStackTrace();	
		} finally {
			sqlSession.close();
		}
		
		return value;
	}
}

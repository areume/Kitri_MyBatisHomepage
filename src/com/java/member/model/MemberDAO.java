package com.java.member.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.java.myBatis.SqlManager;

public class MemberDAO {	// Data Access Object
	
	// mybatis
	private static SqlSessionFactory sqlSessionFactory=SqlManager.getInstance();
	private SqlSession session;
	
	// singleton pattern : 단 한개의 객체만을 가지고 구현 한다.
	private static MemberDAO instance=new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public int insert(MemberDTO memberdto) {

		int value=0;
		
		try {			
			// MyBatis
			session=sqlSessionFactory.openSession();
			value=session.insert("member_insert",memberdto);
			session.commit();	
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return value;
	}
	
	public int idCheck(String id) {
		int value=0;
		
		try {
			
			session=sqlSessionFactory.openSession();
			String checkId=session.selectOne("member_id_check",id);
			
			if(checkId!=null) value=1;

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
				
		return value;
	}
	
	public List<ZipcodeDTO> zipcodeReader(String checkDong) {
		
		List<ZipcodeDTO> arraylist=null;
				
		try {
			
			session=sqlSessionFactory.openSession();
			arraylist=session.selectList("member_zipcode",checkDong); // 반환형이 list 타입이므로 arraylist를 list로 바꿔주어야 한다. command 파일에서도 바꿔야한다.
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	
		return arraylist;

	}
	
	public String loginCheck(String id, String password) {
			
		String value=null;
		
		try {
			HashMap<String,String> hashMap=new HashMap<String,String>();
			hashMap.put("id",id);
			hashMap.put("password",password);
			session=sqlSessionFactory.openSession();
			value=session.selectOne("member_login",hashMap);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return value;
	}
	
	
	public MemberDTO updateId(String id) {	// 업데이트할 멤버를 id로 찾기
		
		MemberDTO memberdto=null;
		
		try {		
			session=sqlSessionFactory.openSession();
			memberdto=session.selectOne("member_select",id);			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return memberdto;
		
	}
	
	public int update(MemberDTO memberdto) {
		int value=0;
		
		try {

			session=sqlSessionFactory.openSession();
			value=session.update("member_update",memberdto);
			session.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return value;
		
	}
	
	public int delete(String id, String password) {

		int value=0;
		
		try {
			
			Map<String,String> hashMap=new HashMap<String,String>();
			hashMap.put("id",id);
			hashMap.put("password",password);
	
			session=sqlSessionFactory.openSession();
			value=session.delete("member_delete",hashMap);
			session.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return value;
	}

}

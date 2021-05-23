package com.java.board.model;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.java.myBatis.SqlManager;

public class BoardDAO {
	
	private static SqlSessionFactory sqlSessionFactory=SqlManager.getInstance();
	private SqlSession session;

	private static BoardDAO instance=new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public int insert(BoardDTO boarddto) {

		int value=0;		
		writeNumber(boarddto);
		System.out.println(boarddto);
		
		try {
				
			session=sqlSessionFactory.openSession();
			value=session.insert("board_insert",boarddto);
			session.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	
		return value;
	}
	
	public void writeNumber(BoardDTO boarddto) {
		// 그룹번호(ROOT), 글순서(자식), 글레벨(자식)
		
		int boardNumber=boarddto.getBoardNumber();
		int sequenceNumber=boarddto.getSequenceNumber();
		int sequenceLevel=boarddto.getSequenceLevel();

		try {
			
			if(boardNumber==0) {	// 부모글	
				
				session=sqlSessionFactory.openSession();
				int max=session.selectOne("board_group_number_max");
				
				if(max!=0) boarddto.setGroupNumber(max+1);
					
			} else {	// 자식글 : 글순서, 글레벨
							
				session=sqlSessionFactory.openSession();
				session.update("board_sequence_update",boarddto);
				session.commit();
				boarddto.setSequenceNumber(++sequenceNumber);
				boarddto.setSequenceLevel(++sequenceLevel);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public int getCount() {	
		int value=0;
		
		try {	
			session=sqlSessionFactory.openSession();
			value=session.selectOne("board_count");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return value;
	}
	
	public List<BoardDTO> getBoardList(int startRow, int endRow) {
		HashMap<String,Integer> hashmap=new HashMap<String,Integer>();
		hashmap.put("startRow",startRow);
		hashmap.put("endRow",endRow);
		List<BoardDTO> boardlist=null;
		
		try {	
			session=sqlSessionFactory.openSession();
			boardlist=session.selectList("board_list",hashmap);

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return boardlist;
	}

	public BoardDTO read(int boardNumber) {
		
		BoardDTO boarddto = null;
		
		try {
			session=sqlSessionFactory.openSession();
			session.update("board_read_count_update",boardNumber);
			boarddto=session.selectOne("board_read",boardNumber);
			session.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		
		return boarddto;
	}
	

	public int delete(int boardNumber, String password) {
		// HashMap<String,Object>
		HashMap<String,String> hashmap=new HashMap<String,String>();
		hashmap.put("boardNumber",String.valueOf(boardNumber));
		hashmap.put("password",password);
		int value=0;
		
		try {
			
			session=sqlSessionFactory.openSession();
			value=session.delete("board_delete",hashmap);
			session.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return value;
	}

	public BoardDTO select(int boardNumber) {
		
		BoardDTO boarddto=null;
		
		try {
						
			session=sqlSessionFactory.openSession();
			boarddto=session.selectOne("board_read",boardNumber);
					
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return boarddto;
	}

	
	public int update(BoardDTO boarddto) {
		
		int value=0;
		
		try {
			session=sqlSessionFactory.openSession();
			value=session.update("board_update",boarddto);	
			session.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return value;
	}
	
}

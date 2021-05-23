package com.java.fileBoard.model;

import java.sql.Connection;
//import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.java.database.ConnectionProvider;
import com.java.database.JdbcUtil;
import com.java.myBatis.SqlManager;

public class BoardDAO {
	
	private static SqlSessionFactory sqlSessionFactory=SqlManager.getInstance();
	private SqlSession session;	

	private static BoardDAO instance=new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public int insert(BoardDTO boarddto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int value=0;
		
		writeNumber(boarddto,conn);
		System.out.println(boarddto);
		
		try {
			String sql=null; 
			
			if (boarddto.getFileSize()==0) {	// 파일이 없는 경우

				sql = "insert into board(board_number,writer,subject,email,content,password,write_date,read_count,group_number,sequence_number,sequence_level) values (board_board_number_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, boarddto.getWriter());
				pstmt.setString(2, boarddto.getSubject());
				pstmt.setString(3, boarddto.getEmail());
				pstmt.setString(4, boarddto.getContent().replace("\r\n", "<br>"));
				pstmt.setString(5, boarddto.getPassword());

				/*
				 * Date date=boarddto.getWriteDate(); long time=date.getTime(); Timestamp ts=new
				 * Timestamp(time);
				 */
				pstmt.setTimestamp(6, new Timestamp(boarddto.getWriteDate().getTime()));
				pstmt.setInt(7, boarddto.getReadCnt());
				pstmt.setInt(8, boarddto.getGroupNumber());
				pstmt.setInt(9, boarddto.getSequenceNumber());
				pstmt.setInt(10, boarddto.getSequenceLevel());
				
			}else {
				
//				sql="insert into board(board_number,writer,subject,email,content,password,write_date,read_count,group_number,sequence_number,sequence_level,file_name,path,file_size) values (board_board_number_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				conn = ConnectionProvider.getConnection();
//				pstmt = conn.prepareStatement(sql);
//
//				pstmt.setString(1, boarddto.getWriter());
//				pstmt.setString(2, boarddto.getSubject());
//				pstmt.setString(3, boarddto.getEmail());
//				pstmt.setString(4, boarddto.getContent().replace("\r\n", "<br>"));
//				pstmt.setString(5, boarddto.getPassword());
//
//				pstmt.setTimestamp(6, new Timestamp(boarddto.getWriteDate().getTime()));
//				pstmt.setInt(7, boarddto.getReadCnt());
//				pstmt.setInt(8, boarddto.getGroupNumber());
//				pstmt.setInt(9, boarddto.getSequenceNumber());
//				pstmt.setInt(10, boarddto.getSequenceLevel());
//				
//				pstmt.setString(11,boarddto.getFileName());
//				pstmt.setString(12,boarddto.getPath());
//				pstmt.setLong(13,boarddto.getFileSize());

			}
			
			value=pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		
		return value;
	}
	
	public void writeNumber(BoardDTO boarddto, Connection conn) {
		// 그룹번호(ROOT), 글순서(자식), 글레벨(자식)
		
		int boardNumber=boarddto.getBoardNumber();
		int groupNumber=boarddto.getGroupNumber();
		int sequenceNumber=boarddto.getSequenceNumber();
		int sequenceLevel=boarddto.getSequenceLevel();
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;

		try {
			
			if(boardNumber==0) {	// 부모글	
				sql="select max(group_number) from board";
				conn=ConnectionProvider.getConnection();
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
					
				if(rs.next()) {
					int max=rs.getInt(1);	// 인덱스 값으로 가져온다.
					boarddto.setGroupNumber(max+1);
				}
					
			} else {	// 자식글 : 글순서, 글레벨
				sql="update board set sequence_number=sequence_number+1 where group_number=? and sequence_number>?";
				conn=ConnectionProvider.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,groupNumber);
				pstmt.setInt(2,sequenceNumber);
				pstmt.executeUpdate();
				
				sequenceNumber=sequenceNumber+1;
				sequenceLevel=sequenceLevel+1;
				
				boarddto.setSequenceNumber(sequenceNumber);
				boarddto.setSequenceLevel(sequenceLevel);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}

	}

	public int getCount( ) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int value=0;
		
		try {
			
			String sql="select count(*) from board";
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				value=rs.getInt(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}

		return value;
	}
	
	public ArrayList<BoardDTO> getBoardList(int startRow, int endRow) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BoardDTO> boardlist=new ArrayList<BoardDTO>();
		
		try {
			
			String sql="select * from "
					+ "(select rownum as rnum, a.* from "
					+ "(select * from board order by group_number desc, sequence_number asc) a) b "
					+ "where b.rnum>=? and b.rnum<=?";
			
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO boarddto=new BoardDTO();
				
				boarddto.setBoardNumber(rs.getInt("board_number"));
				boarddto.setWriter(rs.getString("writer"));
				boarddto.setSubject(rs.getString("subject"));
				boarddto.setEmail(rs.getString("email"));
				boarddto.setContent(rs.getString("content"));
				
				boarddto.setPassword(rs.getString("password"));
				boarddto.setWriteDate(new Date(rs.getTimestamp("write_date").getTime()));
				boarddto.setReadCnt(rs.getInt("read_count"));
				//내부적으로 밑의 3개는 필요하다
				boarddto.setGroupNumber(rs.getInt("group_number"));
				boarddto.setSequenceNumber(rs.getInt("sequence_number"));
				boarddto.setSequenceLevel(rs.getInt("sequence_level"));
				
				boardlist.add(boarddto);
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return boardlist;
	}

	public BoardDTO read(int boardNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO boarddto = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); //auto커밋 안해주기
			
			String sqlUpdate = "update board set read_count=read_count+1 where board_number=?";
			pstmt = conn.prepareStatement(sqlUpdate);
			pstmt.setInt(1, boardNumber);
			int value = pstmt.executeUpdate();
			if(value>0) JdbcUtil.close(pstmt);
			
			String sqlSelct = "select * from board where board_number=?";
			pstmt = conn.prepareStatement(sqlSelct);
			pstmt.setInt(1, boardNumber);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boarddto = new BoardDTO();
				boarddto.setBoardNumber(rs.getInt("board_number"));
				boarddto.setWriter(rs.getString("writer"));
				boarddto.setSubject(rs.getString("subject"));
				boarddto.setEmail(rs.getString("email"));
				boarddto.setContent(rs.getString("content"));
				
				boarddto.setPassword(rs.getString("password"));
				boarddto.setWriteDate(new Date(rs.getTimestamp("write_date").getTime()));
				boarddto.setReadCnt(rs.getInt("read_count"));
				boarddto.setGroupNumber(rs.getInt("group_number"));
				boarddto.setSequenceNumber(rs.getInt("sequence_number"));
				boarddto.setSequenceLevel(rs.getInt("sequence_level"));		
				
				boarddto.setFileName(rs.getString("file_name"));
				boarddto.setPath(rs.getString("path"));
				boarddto.setFileSize(rs.getLong("file_size"));
					
			}	
			conn.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollBack(conn);
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		
		return boarddto;
	}

	public int delete(int boardNumber, String password) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int value=0;
		
		try {
			
			String sql="delete from board where board_number=? and password=?";
			
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,boardNumber);
			pstmt.setString(2,password);
			
			value=pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}

		return value;
	}

	public int update(BoardDTO boarddto) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int value=0;
		
		try {
			
			String sql="update board set writer=?, subject=?, email=?, content=?, password=? where board_number=?";
			conn=ConnectionProvider.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,boarddto.getWriter());
			pstmt.setString(2,boarddto.getSubject());
			pstmt.setString(3,boarddto.getEmail());
			pstmt.setString(4,boarddto.getContent());
			pstmt.setString(5,boarddto.getPassword());
			pstmt.setInt(6,boarddto.getBoardNumber());
			
			value=pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return value;
	}
	
	public BoardDTO select(int boardNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO boarddto = null;
		
		try {
			conn = ConnectionProvider.getConnection();
	
			String sqlSelct = "select * from board where board_number=?";
			pstmt = conn.prepareStatement(sqlSelct);
			pstmt.setInt(1, boardNumber);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boarddto = new BoardDTO();	
				
				boarddto.setFileName(rs.getString("file_name"));
				boarddto.setPath(rs.getString("path"));
				boarddto.setFileSize(rs.getLong("file_size"));
					
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollBack(conn);
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);		
		}	
		return boarddto;
	}
}

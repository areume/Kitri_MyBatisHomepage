package com.java.board.command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDAO;
import com.java.board.model.BoardDTO;
import com.java.command.Command;

public class WriteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//request.setCharacterEncoding("utf-8");
		
		BoardDTO boarddto=new BoardDTO();
		
		boarddto.setBoardNumber(Integer.parseInt(request.getParameter("boardNumber")));
		boarddto.setGroupNumber(Integer.parseInt(request.getParameter("groupNumber")));
		boarddto.setSequenceNumber(Integer.parseInt(request.getParameter("sequenceNumber")));
		boarddto.setSequenceLevel(Integer.parseInt(request.getParameter("sequenceLevel")));
		
		boarddto.setWriter(request.getParameter("writer"));
		boarddto.setSubject(request.getParameter("subject"));
		boarddto.setEmail(request.getParameter("email"));
		boarddto.setContent(request.getParameter("content"));
		boarddto.setPassword(request.getParameter("password"));
		boarddto.setWriteDate(new Date());
		logger.info(logMsg+boarddto);
		
		int check=BoardDAO.getInstance().insert(boarddto);
		logger.info(logMsg+check);
		
		request.setAttribute("check",check);
		
		return "/WEB-INF/views/board/writeOk.jsp";
	}

}

package com.java.fileBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDAO;
import com.java.fileBoard.model.BoardDTO;
import com.java.command.Command;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		
		//request.setCharacterEncoding("utf-8");
		
		BoardDTO boarddto=new BoardDTO();
		boarddto.setBoardNumber(boardNumber);
		boarddto.setWriter(request.getParameter("writer"));
		boarddto.setSubject(request.getParameter("subject"));
		boarddto.setEmail(request.getParameter("email"));
		boarddto.setContent(request.getParameter("content"));
		boarddto.setPassword(request.getParameter("password"));
		
		// 파일 수정
		
		int check=BoardDAO.getInstance().update(boarddto);
		logger.info(logMsg+"check : "+check);
		
		request.setAttribute("check",check);
		request.setAttribute("pageNumber",pageNumber);
		request.setAttribute("boardNumber",boardNumber);
		
		return "/WEB-INF/views/fileBoard/updateOk.jsp";
	}

}

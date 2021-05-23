package com.java.fileBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDAO;
import com.java.fileBoard.model.BoardDTO;
import com.java.command.Command;

public class UpdateCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		logger.info(logMsg+boardNumber+"/"+pageNumber);
		
		BoardDTO boarddto=BoardDAO.getInstance().select(boardNumber);
		
		request.setAttribute("boarddto",boarddto);
		request.setAttribute("pageNumber",pageNumber);
		logger.info(logMsg+boarddto);
		
		return "/WEB-INF/views/fileBoard/update.jsp";
	}

}

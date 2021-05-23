package com.java.fileBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDAO;
import com.java.fileBoard.model.BoardDTO;
import com.java.command.Command;

public class ReadCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		logger.info(logMsg+ boardNumber+" , "+pageNumber);
		
		BoardDTO boarddto = BoardDAO.getInstance().read(boardNumber);
		logger.info(logMsg+"boardDtoRead: "+boarddto);
		
		if(boarddto.getFileSize()!=0) {
			
			int index=boarddto.getFileName().indexOf('_')+1;
			boarddto.setFileName(boarddto.getFileName().substring(index));
			
		}
		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("boarddto", boarddto);
		
	
		return "/WEB-INF/views/fileBoard/read.jsp";
	}

}

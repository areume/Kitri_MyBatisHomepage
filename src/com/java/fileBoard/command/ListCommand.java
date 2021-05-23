package com.java.fileBoard.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDAO;
import com.java.fileBoard.model.BoardDTO;
import com.java.command.Command;

public class ListCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNumber=request.getParameter("pageNumber");
		if(pageNumber==null) {
			pageNumber="1";
		}
		
		int currentPage=Integer.parseInt(pageNumber);
		
		int boardSize=5; // [1] start : 1, end : 10 [2] start : 11, end : 20, ...
		int startRow=(currentPage-1)*boardSize+1;
		int endRow=currentPage*boardSize;
		
		int count=BoardDAO.getInstance().getCount();
		logger.info(logMsg+count);
		
		ArrayList<BoardDTO> boardlist=null;
		
		if(count>0) {
			// startRow, endRow
			boardlist=BoardDAO.getInstance().getBoardList(startRow,endRow);
			logger.info(logMsg+boardlist.size());
		}
		
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("boardSize", boardSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("count", count);
		
		return "/WEB-INF/views/fileBoard/list.jsp";
	}

}

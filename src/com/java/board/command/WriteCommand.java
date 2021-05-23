package com.java.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

public class WriteCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		// root
		int boardNumber=0;		// root 글이면 0번
		int groupNumber=1;		// 그룹 번호
		int sequenceNumber=0;	// 글 순서
		int sequenceLevel=0;	// 글 레벨
		
		// 답글 (boardNumber=부모글 번호) DB 글 번호, 그룹 번호, 글 순서, 글 레벨 가져오기.
		if(request.getParameter("boardNumber")!=null ) {
			boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber=Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber=Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel=Integer.parseInt(request.getParameter("sequenceLevel"));		
		}
		
		logger.info(logMsg+"boardNumber : "+boardNumber);
		logger.info(logMsg+"groupNumber : "+groupNumber);
		logger.info(logMsg+"sequenceNumber : "+sequenceNumber);
		logger.info(logMsg+"sequenceLevel : "+sequenceLevel);
		
		request.setAttribute("boardNumber",boardNumber);
		request.setAttribute("groupNumber",groupNumber);
		request.setAttribute("sequenceNumber",sequenceNumber);
		request.setAttribute("sequenceLevel",sequenceLevel);
		
		return "/WEB-INF/views/board/write.jsp";
	}

}

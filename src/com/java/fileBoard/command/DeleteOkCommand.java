package com.java.fileBoard.command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDAO;
import com.java.fileBoard.model.BoardDTO;
import com.java.command.Command;

public class DeleteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));		
		String password=request.getParameter("password");	
		logger.info(logMsg+boardNumber+"/"+pageNumber+"/"+password);

		BoardDTO readBoard=BoardDAO.getInstance().select(boardNumber);
		
		int check=BoardDAO.getInstance().delete(boardNumber,password);
		logger.info(logMsg+check);
		
		if(check>0 && readBoard.getPath()!=null) {
			File file=new File(readBoard.getPath());
			
			if(file.exists() && file.isFile()) {
				file.delete();
				/* 업데이트는 지우고 다시 올리는 것으로 하면 된다. */
			}
		}
		
		request.setAttribute("check",check);
		request.setAttribute("pageNumber",pageNumber);
		
		return "/WEB-INF/views/fileBoard/deleteOk.jsp";
	}

}

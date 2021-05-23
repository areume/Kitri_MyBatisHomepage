package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDAO;

public class IdCheckCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id=request.getParameter("id");
		logger.info(logMsg+id);
		
		int check=MemberDAO.getInstance().idCheck(id);	// 1이 넘어오면 아이디가 중복된다.
		logger.info(logMsg+check);
		
		request.setAttribute("check",check);
		request.setAttribute("id",id);
		return "/WEB-INF/views/member/idCheck.jsp";
	}

}

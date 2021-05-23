package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.command.Command;
import com.java.member.model.MemberDAO;
import com.java.member.model.MemberDTO;

public class UpdateCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		logger.info(logMsg+id);
		
		MemberDTO memberdto=MemberDAO.getInstance().updateId(id);
		logger.info(logMsg+memberdto.toString());
		
		request.setAttribute("memberdto",memberdto);
		
		return "/WEB-INF/views/member/update.jsp";
	}

}

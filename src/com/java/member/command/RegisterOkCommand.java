package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDAO;
import com.java.member.model.MemberDTO;

public class RegisterOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//request.setCharacterEncoding("utf-8");
		
		MemberDTO memberdto=new MemberDTO();
		
		memberdto.setId(request.getParameter("id"));
		memberdto.setPassword(request.getParameter("password"));
		memberdto.setName(request.getParameter("name"));
		memberdto.setJumin1(request.getParameter("jumin1"));
		memberdto.setJumin2(request.getParameter("jumin2"));
		
		memberdto.setEmail(request.getParameter("email"));
		memberdto.setZipcode(request.getParameter("zipcode"));
		memberdto.setAddress(request.getParameter("address"));
		memberdto.setJob(request.getParameter("job"));
		memberdto.setMailing(request.getParameter("mailing"));
		
		memberdto.setInterest(request.getParameter("resultInterest"));
		memberdto.setMemberLevel("BB");
		
		logger.info(logMsg+memberdto.toString());
		
		int check=MemberDAO.getInstance().insert(memberdto);
		logger.info(logMsg+check);
		
		request.setAttribute("check", check);
		return "/WEB-INF/views/member/registerOk.jsp";
	}

}

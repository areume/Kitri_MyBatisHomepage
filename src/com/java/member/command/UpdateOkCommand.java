package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.java.command.Command;
import com.java.member.model.MemberDAO;
import com.java.member.model.MemberDTO;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//request.setCharacterEncoding("utf-8");		
		MemberDTO memberdto=new MemberDTO();
		
		/*HttpSession session=request.getSession();
		memberdto.setId((String)session.getId());*/
		memberdto.setNumber(Integer.parseInt(request.getParameter("number")));
		memberdto.setPassword(request.getParameter("password"));
		memberdto.setEmail(request.getParameter("email"));
		memberdto.setZipcode(request.getParameter("zipcode"));
		memberdto.setAddress(request.getParameter("address"));
		
		memberdto.setJob(request.getParameter("job"));
		memberdto.setMailing(request.getParameter("mailing"));
		memberdto.setInterest(request.getParameter("interest"));
		
		logger.info(logMsg+memberdto.toString());
		
		int check=MemberDAO.getInstance().update(memberdto);
		logger.info(logMsg+check);
		
		request.setAttribute("check",check);
		
		return "/WEB-INF/views/member/updateOk.jsp";
	}

}

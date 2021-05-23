package com.java.member.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDAO;
import com.java.member.model.ZipcodeDTO;

public class ZipcodeCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//request.setCharacterEncoding("utf-8");
		
		String checkDong=request.getParameter("dong");
		logger.info(logMsg+checkDong);
		
		if(checkDong!=null) {
			List<ZipcodeDTO> zipcodelist=MemberDAO.getInstance().zipcodeReader(checkDong);
			logger.info(logMsg+zipcodelist.size());
			
			request.setAttribute("zipcodelist",zipcodelist);
		}

		return "/WEB-INF/views/member/zipcodeSearch.jsp";
	}

}

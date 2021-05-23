package com.java.parsing.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

public class ParsingXMLCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		// ajax
		// return "/WEB-INF/views/ajax/proxy/pXML.jsp";
		
		// jquery ajax
		return "/WEB-INF/views/jquery/proxy/pXML.jsp";
	}

}

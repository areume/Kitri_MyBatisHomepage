package com.java.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//web.xml이 호출해준다.
		
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request,response);		// 요청하기 전 필터링 작업
	
	}
}

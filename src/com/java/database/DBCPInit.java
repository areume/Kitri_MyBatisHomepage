package com.java.database;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

// 서버가 시작되면서 데이터베이스도 같이 구동되어야 한다.

public class DBCPInit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		try {
			String jdbcDriver=config.getInitParameter("jdbcDriver");
			Class.forName(jdbcDriver);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

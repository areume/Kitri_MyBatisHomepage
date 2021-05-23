package com.java.parsing.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import com.java.command.Command;

public class ProxyCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		// 프록시 서버 : 시스템에 방화벽을 가지고 있는 경우 접근을 할 수 없다. 외부와 통신을 위해 만들어 놓은 서버.
		//		          방화벽 안쪽에 있는 서버들의 외부 연결은 프록시 서버를 통해 이루어진다.
		
		// 내 서버로 가져와서 작업을 진행한다. 외부에서 가져오는 것은 모두 GET 방식이다. 요청 시 특수문자를 넣을 수 없다. 한글과 영문만 가능하다.
		String url="https://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109";
		GetMethod method=new GetMethod(url);
		
		HttpClient client=new HttpClient();
		int statusCode=client.executeMethod(method);		
		logger.info(logMsg+statusCode);					// 200, 400
		
		if(statusCode==HttpStatus.SC_OK) {
			String result=method.getResponseBodyAsString();	// 소스가 넘어온다.
			logger.info(logMsg+result);
			
			response.setContentType("application/xml;charset=utf-8"); // application/text, application/json
			PrintWriter out=response.getWriter();
			out.print(result);
		}
		
		return null;
	}
}

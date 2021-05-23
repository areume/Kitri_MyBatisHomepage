package com.java.reply.command;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.java.command.Command;
import com.java.reply.model.ReplyDAO;
import com.java.reply.model.ReplyDTO;

public class ReplyWriteCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String writeReply=request.getParameter("writeReply");
		logger.info(logMsg+"writeReply : "+writeReply);
		
		ReplyDTO replydto=new ReplyDTO();
		replydto.setLine_reply(writeReply);
		
		/* 답글 삽입 */
		int check=ReplyDAO.getInstance().insert(replydto);
		logger.info(logMsg+"check : "+check);
		
		/* 가장 큰 번호 가져오기 */
		if(check>0) {
			int bunho=ReplyDAO.getInstance().getBunho();
			logger.info(logMsg+"bunho : "+bunho);
			
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("bunho",bunho);
			map.put("line_reply",replydto.getLine_reply());
			
			JSONObject obj = new JSONObject(map);
	        logger.info(logMsg+obj);
			
			// String str=bunho+","+writeReply;	// JSON으로 보내주기 위함. 자바스크립트에서 ,로 구분하여 만들어준다.(SPRING 실습때 진행)
			response.setContentType("application/text;charset=utf-8");
			PrintWriter pw=response.getWriter();
			
			pw.print(obj);
		}
		
		return null;
	}

}

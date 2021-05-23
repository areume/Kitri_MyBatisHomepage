package com.java.reply.command;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.java.command.Command;
import com.java.reply.model.ReplyDAO;
import com.java.reply.model.ReplyDTO;

public class ReplySelectCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int bunho=Integer.parseInt(request.getParameter("bunho"));
		logger.info(logMsg+"bunho : "+bunho);
		
		ReplyDTO dto=ReplyDAO.getInstance().select(bunho);
		logger.info(logMsg+"dto : "+dto);
		
		if(dto!=null) {
			//String str=dto.getBunho()+","+dto.getLine_reply();		
			
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("bunho",dto.getBunho());
			map.put("reply",dto.getLine_reply());
			
			JSONObject obj=new JSONObject(map);
			logger.info(logMsg+"JSON : "+obj);
			
			response.setContentType("application/text;charset=utf-8");
			PrintWriter out=response.getWriter();
			
			out.print(obj);
		}
		
		return null;
	}

}

package com.java.reply.command;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.java.command.Command;
import com.java.reply.model.ReplyDAO;

public class ReplyUpdateCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int bunho=Integer.parseInt(request.getParameter("bunho"));
		String linereply=request.getParameter("reply");
		logger.info(logMsg+"bunho : "+bunho+" / linereply : "+linereply);
		
		int check=ReplyDAO.getInstance().update(bunho,linereply);
		logger.info(logMsg+"update check : "+check);
		
		if(check>0) {
			
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("bunho",bunho);
			map.put("reply",linereply);
			
			JSONObject obj = new JSONObject(map);
	        logger.info(logMsg+obj);
			
			// String str=bunho+","+linereply;
			response.setContentType("application/text;charset=utf-8");
			PrintWriter out=response.getWriter();
			
			out.print(obj);
		}
		
		return null;
	}

}

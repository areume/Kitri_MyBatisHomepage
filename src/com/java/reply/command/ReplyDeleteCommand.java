package com.java.reply.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.reply.model.ReplyDAO;

public class ReplyDeleteCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		// DB에서 삭제
		int bunho=Integer.parseInt(request.getParameter("bunho"));
		logger.info(logMsg+"Delete Bunho : "+bunho);
		
		// DAO 함수 호출
		int check=ReplyDAO.getInstance().delete(bunho);
		logger.info(logMsg+"delete check : "+check);
		
		if(check>0) { // DB에서 삭제된 경우에만 뷰에서 삭제되도록.
			response.setContentType("application/text;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.print(bunho);
		}

		return null;
	}

}

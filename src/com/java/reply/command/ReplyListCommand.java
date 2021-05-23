package com.java.reply.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.reply.model.ReplyDAO;
import com.java.reply.model.ReplyDTO;

public class ReplyListCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		// 기존 댓글 목록 가져오기
		List<ReplyDTO> replyList=ReplyDAO.getInstance().getList();
		logger.info(logMsg+"replyList Size : "+replyList.size());
		
		// list 넘겨주기
		request.setAttribute("replyList",replyList);
		
		// ajax
		// return "/WEB-INF/views/ajax/reply/list.jsp";
		
		// jquery
		return "/WEB-INF/views/jquery/reply/list.jsp";
	}
}

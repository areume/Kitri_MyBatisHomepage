package com.java.sawon.command;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.java.command.Command;
import com.java.sawon.model.SawonDAO;
import com.java.sawon.model.SawonDTO;

public class ListOkCommand implements Command {

	@Override
	@SuppressWarnings("unchecked") //경고 꼴뵈기 싫으면 추가하기
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String departmentName=request.getParameter("departmentName");
		logger.info(logMsg+"departmentName : "+departmentName);
		
		List<SawonDTO> sawonList=SawonDAO.getInstance().select(departmentName);
		logger.info(logMsg+"List size : "+sawonList.size());
		
		// JSON
		JSONArray jsonArray=new JSONArray();
		
		if(sawonList!=null) {
			for(int i=0;i<sawonList.size();i++) {
				SawonDTO sawondto=sawonList.get(i);
				
				JSONObject obj=new JSONObject();
				obj.put("employee_id",sawondto.getEmployee_id());
				obj.put("first_name",sawondto.getFirst_name());
				obj.put("job_id",sawondto.getJob_id());
				obj.put("department_id",sawondto.getDepartment_id());
				obj.put("department_name",sawondto.getDepartment_name());
				
				jsonArray.add(obj);
			}
		}
		logger.info(logMsg+jsonArray);
		
		response.setContentType("application/text;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(jsonArray);
		
		
		// List -> JSON Array(JSON 라이브러리 다운, JSON 형태 코딩 구현) -> JSP page 출력시 태그를 만듬. : Spring 할때		
		// request.setAttribute("sawonList",sawonList);
		
		// ajax
		// return "/WEB-INF/views/ajax/sawon/listOk.jsp";
		
		// jquery
		//return "/WEB-INF/views/jquery/sawon/listOk.jsp";
		
		return null;
	}

}

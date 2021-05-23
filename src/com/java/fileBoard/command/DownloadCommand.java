package com.java.fileBoard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.fileBoard.model.BoardDAO;
import com.java.fileBoard.model.BoardDTO;

public class DownloadCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		logger.info(logMsg+boardNumber);
		
		BoardDTO boarddto=BoardDAO.getInstance().select(boardNumber);
		logger.info(logMsg+boarddto.getFileName()+"/"+boarddto.getPath()+"/"+boarddto.getFileSize());
		
		int index=boarddto.getFileName().indexOf("_")+1;
		String fName=boarddto.getFileName().substring(index);
		String fileName=new String(fName.getBytes("utf-8"),"ISO-8859-2");
		
		long fileSize=boarddto.getFileSize();
		String path=boarddto.getPath();
		
		response.setHeader("Content-Disposition","attachment;filename="+fileName);
		response.setContentType("application/octet-stream");
		response.setContentLengthLong(fileSize);
		
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		
		try {
			
			bis=new BufferedInputStream(new FileInputStream(path),1024);
			bos=new BufferedOutputStream(response.getOutputStream(),1024);
			
			while(true) {
				int data=bis.read();
				if(data==-1) break;
				
				bos.write(data);
			}
			bos.flush();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(bis!=null) bis.close();
			if(bos!=null) bos.close();
		}
		
		return null;
	}

}

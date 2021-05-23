package com.java.fileBoard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;		// tomcat 말고, commons로 import 해주어야 한다.
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.java.fileBoard.model.BoardDAO;
import com.java.fileBoard.model.BoardDTO;
import com.java.command.Command;

public class WriteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//request.setCharacterEncoding("utf-8");
		
		DiskFileItemFactory factory=new DiskFileItemFactory();		// 파일 보관 객체. 업로드된 파일을 저장할 저장소와 관련된 클래스.
		ServletFileUpload upload=new ServletFileUpload(factory);	// 요청 처리 객체. HTTP 요청에 대한 HttpServletRequest 객체로 부터 multipart/form-data 형식으로 넘어온 HTTP Body 부분을 다루기 쉽게 변환 해주는 역할.
		List<FileItem> list=upload.parseRequest(request);			// FileItem : 사용자가 업로드한 File 데이터나 사용자가 input text에 입력한 일반 요청 데이터에 대한 객체입니다.
		
		Iterator<FileItem> iterator=list.iterator();
		
		BoardDTO boarddto=new BoardDTO();
		HashMap<String,String> dataMap=new HashMap<String,String>();
		
		while(iterator.hasNext()) {
			FileItem fileitem=iterator.next();

			if(fileitem.isFormField()) {		// fileitem이 simple form field를 나타내는지 알려준다.
				
//				String name=fileitem.getFieldName();
//				logger.info(logMsg+"text : "+name);
				
//				if(fileitem.getFieldName().equals("boardNumer")) {
//					String boardNumber=fileitem.getString();
//					int num=Integer.parseInt(boardNumber);
//					boarddto.setBoardNumber(num);
//				}
//				
//				if(fileitem.getFieldName().equals("groupNumber")) {
//					boarddto.setGroupNumber(Integer.parseInt("groupNumber"));
//				}
//				
//				if(fileitem.getFieldName().equals("sequenceNumber")) {
//					boarddto.setSequenceNumber(Integer.parseInt("sequenceNumber"));
//				}
//				
//				if(fileitem.getFieldName().equals("sequenceLevel")) {
//					boarddto.setSequenceLevel(Integer.parseInt("sequenceLevel"));
//				}
//				
//				if(fileitem.getFieldName().equals("writer")) {
//					boarddto.setWriter(fileitem.getString("utf-8"));
//				}
//				
//				if(fileitem.getFieldName().equals("subject")) {
//					boarddto.setSubject(fileitem.getString("utf-8"));
//				}
//				
//				if(fileitem.getFieldName().equals("email")) {
//					boarddto.setEmail(fileitem.getString("utf-8"));
//				}
//				
//				if(fileitem.getFieldName().equals("content")) {
//					boarddto.setContent(fileitem.getString("utf-8"));
//				}
//				
//				if(fileitem.getFieldName().equals("password")) {
//					boarddto.setPassword(fileitem.getString("utf-8"));
//				}
				
				String name=fileitem.getFieldName();
				String value=fileitem.getString("utf-8");
				logger.info(logMsg+name+"/"+value);
				dataMap.put(name,value);
					
			}else {
				//String name=fileitem.getFieldName();
				//logger.info(logMsg+"binary : "+name+","+fileitem.getName()+","+fileitem.getSize());
				
				if(fileitem.getFieldName().equals("file")) {
					// 파일명 	 fileitem.getName()
					// 파일크기 fileitem.getSize()
					// getInputStram
					
					if(fileitem.getName()==null || fileitem.getName().equals("")) continue;
					
					upload.setFileSizeMax(1024*1024*10);  // B*KB*MB*GB (10MB)
					String fileName=System.currentTimeMillis()+"_"+fileitem.getName();
					
					/* 입력받은 파일을 저장할 경로 */
					/* 절대경로 */
					String dir="C:\\areum\\mvc\\workspace\\MVCHomePage\\WebContent\\pds\\";
					File file=new File(dir,fileitem.getName());
					
					/* 톰캣 실제 서버 경로 - 개인적으로 비추비추 */
					//String dir=request.getServletContext().getRealPath("\\pds\\");
					//logger.info(logMsg+dir);
					
					/* 지정한 폴더를 생성해서 파일 올려주기 */
//					File dir=new File("C:\\pds\\");
//					dir.mkdir();
//					
//					File file=null;
//					if(dir.exists() && dir.isDirectory()) {
//						file=new File(dir,fileName);
//					}
										
					BufferedInputStream bis=null;
					BufferedOutputStream bos=null;
					
					try {
						bis=new BufferedInputStream(fileitem.getInputStream(),1024);
						bos=new BufferedOutputStream(new FileOutputStream(file),1024);
						
						/* 파일 읽어와서 저장하기 */
						while(true) {
							int data=bis.read();
							if(data==-1) break;
							
							bos.write(data);
						}
						bos.flush();						
						
					} catch(IOException e) {
						e.printStackTrace();
					} finally {
						if(bis!=null) bis.close();
						if(bos!=null) bos.close();
					}
					boarddto.setFileName(fileName);
					boarddto.setFileSize(fileitem.getSize());
					boarddto.setPath(file.getAbsolutePath());
				}
			}
		}
		boarddto.setWriteDate(new Date());
		boarddto.setDataMap(dataMap);
		logger.info(logMsg+boarddto.toString());
		
		int check=BoardDAO.getInstance().insert(boarddto);
		logger.info(logMsg+check);
		
		request.setAttribute("check",check);
		return "/WEB-INF/views/fileBoard/writeOk.jsp";
	}

}

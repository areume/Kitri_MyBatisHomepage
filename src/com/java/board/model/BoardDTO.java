package com.java.board.model;

import java.util.Date;

public class BoardDTO {
	
	private int boardNumber;
	private String writer;
	private String subject;
	private String email;
	private String content;
	private String password;
	
	private Date writeDate;
	private int readCnt;
	private int groupNumber;
	private int sequenceNumber;
	private int sequenceLevel;
	

	public BoardDTO() {
		
	}
	
	public BoardDTO(int boardNumber, String writer, String subject, String email, String content, String password,
			Date writeDate, int groupNumber, int sequenceNumber, int sequenceLevel, int readCnt) {
		super();
		this.boardNumber = boardNumber;
		
		this.writer = writer;
		this.subject = subject;
		this.email = email;
		this.content = content;
		this.password = password;
		
		this.writeDate = writeDate;
		this.groupNumber = groupNumber;
		this.sequenceNumber = sequenceNumber;
		this.sequenceLevel = sequenceLevel;
		this.readCnt = readCnt;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public int getSequenceLevel() {
		return sequenceLevel;
	}

	public void setSequenceLevel(int sequenceLevel) {
		this.sequenceLevel = sequenceLevel;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	@Override
	public String toString() {
		return "BoardDTO [boardNumber=" + boardNumber + ", writer=" + writer + ", subject=" + subject + ", email="
				+ email + ", content=" + content + ", password=" + password + ", writeDate=" + writeDate
				+ ", groupNumber=" + groupNumber + ", sequenceNumber=" + sequenceNumber + ", sequenceLevel="
				+ sequenceLevel + ", readCnt=" + readCnt + "]";
	}

}

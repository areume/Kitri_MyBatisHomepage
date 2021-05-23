<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
	<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="${root}/xhr/xhr.js"></script>	
	<script type="text/javascript" src="${root}/javascript/reply/replyWrite.js"></script>
	<script type="text/javascript" src="${root}/javascript/reply/replyDelete.js"></script>
	<script type="text/javascript" src="${root}/javascript/reply/replyUpdate.js"></script>
	<link rel="stylesheet" href="${root}/css/reply/reply.css"/>
</head>
<body>
	<div>실시간 댓글이 가능합니다.</div>
	<br><br>
	
	<div>
		<input id="writeReply" type="text" name="write" size="50">
		<input type="button" value="한 줄 댓글" onclick="writeToServer('${root}')">
	</div>
	
	<div id="listAllDiv">
		<!-- 새로운 댓글 -->
		
		<!-- 기존 댓글 : 자바 스크립트에서 생성한 태그들과 동일한 형태로 작성한다. -->
		<c:forEach var="replyDTO" items="${replyList}">
			<div class="replyDiv" id="${replyDTO.bunho}">
				<span class="cssBunho">${replyDTO.bunho}</span>
				<span class="cssReply">${replyDTO.line_reply}</span>
				<span class="cssUpDel">
					<a href="javascript:deleteToServer('${replyDTO.bunho}','${root}')">삭제&nbsp;</a>					
					<a href="javascript:selectToServer('${replyDTO.bunho}','${root}')">수정</a>
				</span>
			</div>
		</c:forEach>
	</div>
</body>
</html>
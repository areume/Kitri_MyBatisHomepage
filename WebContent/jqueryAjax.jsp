<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
	<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${root}/css/sawon/ajaxCss.css">
</head>
<body>
	<h3>사원 테이블</h3>
	<a href="${root}/sawon/list.do">사원목록</a><br><br>
	
	<h3>실시간 댓글</h3>
	<a href="${root}/reply/replyList.do">한 줄 답글</a><br><br>
	
	<h3>기상청 날씨</h3>
	<a href="${root}/parsing.do">오늘의 날씨</a><br><br>
	
	<h3>Kakao Map</h3>
	<a href="${root}/map.do">Kakao Map</a><br><br>
	
	<h3>Address Map</h3>
	<a href="${root}/addr.do">Address Map</a><br><br>
</body>
</html>
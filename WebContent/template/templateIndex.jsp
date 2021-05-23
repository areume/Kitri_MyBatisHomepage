<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="left.jsp"></jsp:include>
	
	<c:if test="${viewPage==null}">
		<div align="center">
			<h2>Welcome HomePage</h2>
		</div>
	</c:if>
	
	<c:if test="${viewPage!=null}">
		<jsp:include page="${viewPage}"></jsp:include>
	</c:if>
	
	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
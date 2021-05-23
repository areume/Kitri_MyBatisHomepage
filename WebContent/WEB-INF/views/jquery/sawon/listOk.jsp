<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
	<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${root}/css/sawon/listCss.css">
</head>
<body>
	<table border="1">
		<tr>
			<td align="center" bgcolor="#DEDEDE" width="100">사번</td>
			<td align="center" bgcolor="#DEDEDE" width="100">이름</td>
			<td align="center" bgcolor="#DEDEDE" width="100">입사년도</td>
			<td align="center" bgcolor="#DEDEDE" width="100">직군</td>
			<td align="center" bgcolor="#DEDEDE" width="100">부서번호</td>
			<td align="center" bgcolor="#DEDEDE" width="100">부서이름</td>
		</tr>
		
		<c:forEach var="sawondto" items="${sawonList}">
			<tr>
				<td align="center" width="100">${sawondto.employee_id}</td>
				<td align="center" width="100">${sawondto.first_name}</td>
				<td align="center" width="100">
					<fmt:formatDate value="${sawondto.hire_date}" pattern="yyyy-MM--dd"/>
				</td>
				<td align="center" width="100">${sawondto.job_id}</td>
				<td align="center" width="100">${sawondto.department_id}</td>
				<td align="center" width="100">${sawondto.department_name}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
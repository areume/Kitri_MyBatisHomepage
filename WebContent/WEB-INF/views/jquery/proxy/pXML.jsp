<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
	<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="${root}/xhr/jquery.js"></script>
	<script type="text/javascript">
	
		$(function() {
			$("#btn").click(function() {
				$.ajax({
					url:"${root}/pXML.do",
					type:"get",
					dataType:"xml",
					success:processXML		// 코드 길어지므로 함수 생성하기
				});
			});
			
			function processXML(xmlDoc) {
				//alert(xmlDoc);	// [object XMLDocument] 나오면 정상.
				var location=$(xmlDoc).find("location");			// location 태그 찾아오기
				
				var city=$(location).eq(1).children("city").text();	// 2번째 location 태그의 자식 중, city 태그의 text 뽑아오기	
				$("#city").text(city);
				
				var data=$(location).eq(1).children("data");		// 2번째 location 태그의 자식 중, data 태그 가져오기
								
				var wf=$(data).eq(1).children("wf").text();			// 2번째 data 태그의 자식 중, wf 태그의 text 뽑아오기
				$("#wf").text(wf);	
			
				var titleWf=$(xmlDoc).find("wf").eq(0).text();		// xmlDoc에서 wf 태그 찾아, 첫번째의 text 가져오기
				$("#titleWf").html(titleWf);
				
				//alert(city+" "+wf+" "+titleWf);
			}
		});			
	</script>
</head>
<body>
	<input type="button" value="오늘의 날씨" id="btn"><br>
		
	<div>
		<span id="titleWf" style="color:red;"></span><br><br>
		<span id="city" style="color:blue;"></span><br>
		<span id="time" style="color:green;"></span><br>
		<span id="wf"></span><br><br>
	</div>
</body>
</html>
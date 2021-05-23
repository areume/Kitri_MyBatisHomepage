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
	<script type="text/javascript">
	
		function toServer(root) {
			// 열었던 RSS 파일 주소 복사 붙여넣기.
			var url=root+"/pXML.do";
			// POST 될 수 없다.
			sendRequest("GET",url,null,fromServer);
		}
		
		function fromServer() {
			// 외부에서 데이터를 가져올 때는 항상 확인하기. 4 / 200 넘어오면 뿌리기.
			//alert(xhr.readyState+" / "+xhr.status);
			if(xhr.readyState==4 && xhr.status==200) {
				//alert(xhr.responseXML);
				processXML();
			}			
		}
		
		function processXML() {
			var xmlDoc=xhr.responseXML;
			
			var location=xmlDoc.getElementsByTagName("location");
			alert(location.length); 	// 35
			
			// 타이틀 뽑아오기
			var titleWf=xmlDoc.getElementsByTagName("wf");
			document.getElementById("titleWf").innerHTML=titleWf[0].childNodes[0].nodeValue;
			
			// 지역 뽑아오기 (for문으로 가져오는 건 개인적으로 해보기)
			var city=location[11].getElementsByTagName("city");
			document.getElementById("city").innerText=city[0].firstChild.nodeValue;
			
			// 데이터 블록 뽑아오기
			var data=location[11].getElementsByTagName("data");
			
			// 시간 가져오기	
			var time=data[1].getElementsByTagName("tmEf");
			document.getElementById("time").innerText=time[0].childNodes[0].nodeValue;			
			
			// wf 데이터 가져오기
			var wf=data[1].getElementsByTagName("wf");
			document.getElementById("wf").innerText=wf[0].childNodes[0].nodeValue;
		}
		
	</script>
</head>
<body>
	<input type="button" value="오늘의 날씨" onclick="toServer('${root}')">
	
	<div>
		<span id="titleWf" style="color:red;"></span><br><br>
		<span id="city" style="color:blue;"></span><br><br>
		<span id="time" style="color:green;"></span><br><br>
		<span id="wf"></span><br><br>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=403e874b82c53be6148a1a9672052a79"></script>
</head>
<body>

	<!-- http://apis.map.kakao.com/ -->
	<div id="map" style="width: 500px; height: 400px;"></div>
	
	<script>
		var container=document.getElementById('map');
		var options={
			center : new kakao.maps.LatLng(37.441401, 126.796218),
			level : 3
		};

		var map=new kakao.maps.Map(container, options);
		
		// 마커가 표시도리 위치와 title 객체 배열
		var positions=[
			{
				title: '아름집',
				latlng: new kakao.maps.LatLng(37.441401, 126.796218)			
			},
			{
				title: '마트',
				latlng: new kakao.maps.LatLng(37.442991, 126.7919356)		
			},
			{
				title: '공원',
				latlng: new kakao.maps.LatLng(37.4417135, 126.7941854)		
			}
		];
  
		for (var i = 0; i < positions.length; i ++) {

		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		    });
		}
		
		function setCenter() {            
		    // 이동할 위도 경도 위치를 생성합니다 
		    var moveLatLon = new kakao.maps.LatLng(37.442991, 126.7919356);
		    
		    // 지도 중심을 이동 시킵니다
		    map.setCenter(moveLatLon);
		}

		function panTo() {
		    // 이동할 위도 경도 위치를 생성합니다 
		    var moveLatLon = new kakao.maps.LatLng(37.4417135, 126.7941854);
		    
		    // 지도 중심을 부드럽게 이동시킵니다
		    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
		    map.panTo(moveLatLon);            
		}        

	</script>
	
	<p>
    	<button onclick="setCenter()">지도 중심좌표 이동시키기</button> 
    	<button onclick="panTo()">지도 중심좌표 부드럽게 이동시키기</button> 
	</p>
	
</body>
</html>
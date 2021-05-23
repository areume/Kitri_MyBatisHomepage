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
	<script type="text/javascript" src="${root}/xhr/xhr.js"></script>
	<script type="text/javascript">
			
		var arr=new Array();
	
		function toServer() {
			var addr=document.getElementById("addr").value;			
			var url="https://dapi.kakao.com/v2/local/search/address.json";
			var params="query="+addr;
			
			sendRequest("GET",url,params,fromServer);
		}
		
		function fromServer() {
			if(xhr.readyState==4 && xhr.status==200) {
				processJSON();
			}
		}
		
		// map 변수 밖으로 빼주기
		var map;
		
		function processJSON() {
			var obj=JSON.parse(xhr.responseText);
			var y=obj.documents[0].y;
			var x=obj.documents[0].x;
			//arr.push(y+" / "+x);
			//alert(arr.join("\n"));
			
			var container=document.getElementById('map');
			var options={
				center : new kakao.maps.LatLng(y,x),
				level : 3
			};

			map=new kakao.maps.Map(container, options);
			
			// 마커가 표시될 위치입니다 
			var markerPosition  = new kakao.maps.LatLng(y,x); 

			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});

			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map); 
			
			// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
			var mapTypeControl = new kakao.maps.MapTypeControl();

			// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
			// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
			map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

			// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
			var zoomControl = new kakao.maps.ZoomControl();
			map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		}
		
		// 지도에 추가된 지도타입정보를 가지고 있을 변수입니다
		var currentTypeId;

		// 버튼이 클릭되면 호출되는 함수입니다
		function setOverlayMapTypeId(maptype) {
		    var changeMaptype;
		    
		    // maptype에 따라 지도에 추가할 지도타입을 결정합니다
		    if (maptype === 'traffic') {            
		        
		        // 교통정보 지도타입
		        changeMaptype = kakao.maps.MapTypeId.TRAFFIC;         
		    }
		    
		    // 이미 등록된 지도 타입이 있으면 제거합니다
		    if (currentTypeId) {
		        map.removeOverlayMapTypeId(currentTypeId);    
		    }
		    
		    // maptype에 해당하는 지도타입을 지도에 추가합니다
		    map.addOverlayMapTypeId(changeMaptype);
		    
		    // 지도에 추가된 타입정보를 갱신합니다
		    currentTypeId = changeMaptype;        
		}
		
		function removeType() {
			if (currentTypeId) {
		        map.removeOverlayMapTypeId(currentTypeId);    
		    }
		}
		
	</script>
</head>
<body>

	<h3>주소 검색</h3>
	<input id="addr" type="text">
	<input type="button" value="주소 검색" onclick="toServer()"><br><br>
	
	<div id="map" style="width:800px;height:400px;"></div><br>
	<input type="button" value="교통정보 보기" onclick="setOverlayMapTypeId('traffic')">
	<input type="button" value="원래대로 보기" onclick="removeType()">
</body>
</html>
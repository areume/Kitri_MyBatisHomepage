/**
 * 
 */

var xhr=null;
var arr=new Array();

function startRequest() {
	if(window.XMLHttpRequest) {
		xhr=new XMLHttpRequest;
	}else {
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function toServer(root) {
	var departmentName=document.getElementById("sawonForm").departmentName.value;
	// id가 sawonForm인 태그를 선택하고, 그 태그 안의 요소 중 name이 departmentName인 요소를 가져온다.
	//arr.push(departmentName);
	//alert(arr.join("\n"));
	
	if(departmentName!="부서를 선택하세요.") {
		startRequest();
		var url=root+"/sawon/listOk.do";
		
		xhr.open("POST",url,true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.send("departmentName="+departmentName);
		xhr.onreadystatechange=resultProcess;
	}
}

function resultProcess() {
	if(xhr.readyState==4 && xhr.status==200) {
		arr.push(xhr.responseText);
		
		var resultDisp=document.getElementById("resultDisp");
		resultDisp.innerHTML=xhr.responseText;
	}
	//alert(arr.join("\n"));
}
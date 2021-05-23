/**
 * 
 */

/* 객체 생성 */
function createXHR() {
	if(window.XMLHttpRequest) {
		return new XMLHttpRequest;	
	}else {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

var xhr=null;

function sendRequest(method,url,params,callback) { // GET, root+"/reply/replyWrite.do", "writeReply="+writeReply, writeFromServer

	var httpMethod=method.toUpperCase();
	var httpUrl=url;
	var httpParams=(params==null || params=="")?null:params;
	
	if(httpMethod=="GET" && httpMethod!=null) {
		httpUrl+="?"+httpParams;
	}
	
	//arr.push("[sendRequest] httpMethod : "+httpMethod);
	//arr.push("[sendRequest] httpUrl : "+httpUrl);
	//arr.push("[sendRequest] httpParams : "+httpParams);
	
	//alert("[sendRequest] httpMethod : "+httpMethod+"\n[sendRequest] httpUrl : "+httpUrl+"\n[sendRequest] httpParams : "+httpParams);
	
	xhr=createXHR();
	xhr.open(httpMethod,httpUrl,true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization","KakaoAK 31473e97e05c1b978e38f839bfb4153f");
	xhr.send(httpMethod=="POST"?httpParams:null);
	xhr.onreadystatechange=callback;
}
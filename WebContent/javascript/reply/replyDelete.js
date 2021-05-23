/**
 * 
 */

function deleteToServer(bunho,root) {
	// 번호 잘 넘어오는지 확인하기
	//arr.push(bunho+" / "+root);
	//alert(arr.join("\n"));
	
	var url=root+"/reply/replyDelete.do";
	var params="bunho="+bunho;
	
	sendRequest("GET",url,params,deleteFromServer);
}

function deleteFromServer() {
	if(xhr.readyState==4 && xhr.status==200) {
		// 넘어온 번호 가져오기.
		var bunho=xhr.responseText;	
		// id로 태그 가져와서 삭제하기.
		var div=document.getElementById(bunho);
		// 상위 태그 가져오기.
		var listDiv=document.getElementById("listAllDiv");
		listDiv.removeChild(div);
	}
}
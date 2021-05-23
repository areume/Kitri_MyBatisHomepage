/**
 * 
 */

function deleteToServer(bunho,root) {
	
	var params="bunho="+bunho;
	var url=root+"/reply/replyDelete.do?"+params;
	
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		success:deleteProcess		
	});
}

function deleteProcess(data) {
	$("#listAllDiv").children("#"+data).remove();
}

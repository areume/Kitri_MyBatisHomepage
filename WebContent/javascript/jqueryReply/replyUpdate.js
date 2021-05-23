/**
 * 
 */

var root=null;

function selectToServer(bunho,requestRoot) {
	root=requestRoot;

	var params="bunho="+bunho;
	var url=root+"/reply/replySelect.do?"+params;
	
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		success:selectProcess
	});
}

function selectProcess(data) {
	var obj=$.parseJSON(data);
	var bunho=obj.bunho;
	var reply=obj.reply;
	
	var updateReply="<div id='new'>";
	updateReply+="<input type='text' id='newReplyText' value='"+reply+"'>";
	updateReply+="<input type='button' value='수정하기' onclick=updateToServer("+bunho+")>";
	updateReply+="</div>";
		
	$("#"+bunho).append(updateReply);
}

function updateToServer(bunho) {
	var reply=$("#newReplyText").val();
	var params="bunho="+bunho+"&reply="+reply;
	var url=root+"/reply/replyUpdate.do?"+params;
		
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		success:updateProcess
	});
}

function updateProcess(data) {
	var obj=$.parseJSON(data);
	
	var bunho=obj.bunho;
	var reply=obj.reply;
	
	alert(bunho+"...."+reply);

	$("#"+bunho).children("span").eq(1).text(reply);
	$("#new").remove();
}





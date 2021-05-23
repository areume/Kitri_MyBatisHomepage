/**
 * 
 */

var arr=new Array();

var root=null;

function writeToServer(requestRoot) {
	var root=requestRoot;
	var writeReply=document.getElementById("writeReply").value;
	arr.push("[writeToServer] root : "+root);
	arr.push("[writeToServer] writeReply : "+writeReply)
	
	var url=root+"/reply/replyWrite.do";
	var params="writeReply="+writeReply;
	
	sendRequest("GET",url,params,writeFromServer);
}

/* TEXT로 받아주기 */
function writeFromServer() {
	if(xhr.readyState==4 && xhr.status==200) {
		arr.push("[writeFromServer] : "+xhr.responseText);
		
		// 받은 데이터 분리해서 읽기. 확실하게 공백을 제거해야 한다.
		var result=xhr.responseText.split(",");
		var bunho=result[0].trim();
		var reply=result[1].trim();
		
		// 입력창 비워주기
		document.getElementById("writeReply").value="";
		
		// 답글 1개당 총 6개 태그 생성.
		// 생성한 태그 붙일 div 태그 찾아오기
		var listDiv=document.getElementById("listAllDiv");
		
		// 한 개 답글을 담을 div 태그 생성. 아이디를 시퀀스 번호로 지정.
		var div=document.createElement("div");
		div.className="replyDiv";
		div.id=bunho;
		
		// 번호
		var spanBunho=document.createElement("span");
		spanBunho.className="cssBunho";
		spanBunho.innerText=bunho;
		
		// 답글
		var spanReply=document.createElement("span");
		spanReply.className="cssReply";
		spanReply.innerText=reply;
		
		// 버튼을 담는 span
		var spanUpDel=document.createElement("span");
		spanUpDel.className="cssUpDel";
		
		// 삭제 버튼
		var aDelete=document.createElement("a");
		//aDelete.href="javascript:deleteToServer("+bunho+",\'"+root+"\')"; //번호, 루트 전달
		aDelete.href="#";
		aDelete.onclick=function(){
			// root 특수문자 처리 필요하다.
			deleteToServer(bunho,root)
		};

		aDelete.innerHTML="삭제&nbsp;";
		
		// 수정 버튼
		var aUpdate=document.createElement("a");
		aUpdate.href="#";
		aUpdate.onclick=function(){
			selectToServer(bunho,root)
		};
		aUpdate.innerHTML="수정";
		
		// 태그 붙이기
		spanUpDel.appendChild(aDelete);
		spanUpDel.appendChild(aUpdate);
		
		div.appendChild(spanBunho);
		div.appendChild(spanReply);
		div.appendChild(spanUpDel);
		
		listDiv.appendChild(div);
		listDiv.insertBefore(div,listDiv.firstChild);
				
		//alert(arr.join("\n"));
	}
}

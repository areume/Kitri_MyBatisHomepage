/**
 * 
 */

var root=null;

function selectToServer(bunho,requestRoot) {
	root=requestRoot;
	
	// 번호, root 넘어오는지 확인
	//arr.push(bunho+" / "+root);
	//alert(arr.join("\n"));
	
	var url=root+"/reply/replySelect.do";
	var params="bunho="+bunho;

	sendRequest("GET",url,params,selectFromServer);
}

function selectFromServer() {
	if(xhr.readyState==4 && xhr.status==200) {
		// 넘어온 문자역 가져오기.
		var result=xhr.responseText.split(",");
		var bunho=result[0].trim();
		var reply=result[1].trim();
		
		//arr.push(bunho+">>>>"+reply);
		//alert(arr.join("\n"));
		
		// 답글 내용 뿌려줄 태그를 생성하여 내용 뿌려주기.
		// 생성할 태그는 수정이 완료되면 삭제된다. (구별되는 식별자를 부여해햐 한다.)
		// 전체 div
		var div=document.createElement("div");
		div.id="up"+bunho;
		
		// 기존 텍스트 뿌리고, 수정할 텍스트 입력할 input
		var inputText=document.createElement("input");
		inputText.type="text";
		inputText.value=reply;
		
		// 수정 작업 버튼 input
		var inputButton=document.createElement("input");
		inputButton.type="button";
		inputButton.value="수정";
		inputButton.onclick=function(){
			updateToServer(bunho,inputText.value)
		};
		
		// 태그 붙이기
		div.appendChild(inputText);
		div.appendChild(inputButton);
		
		// 생성할 태그는 수정 작업을 할 태그의 아래에 붙여준다.
		var bunhoDiv=document.getElementById(bunho);
		bunhoDiv.appendChild(div);
		
	}
}

function updateToServer(bunho,value) {
	var url=root+"/reply/replyUpdate.do";
	var params="bunho="+bunho+"&value="+value;
	
	sendRequest("POST",url,params,updateFromServer);
}

function updateFromServer() {
	if(xhr.readyState==4 && xhr.status==200) {
		var result=xhr.responseText.split(",");
		var bunho=result[0].trim();
		var reply=result[1].trim();
		
		// 기존 태그에 바꾼 내용 넣어주기.
		// 번호로 가져온 태그의 자식 노드를 가져오기.
		var bunhoDiv=document.getElementById(bunho);
		var span=bunhoDiv.getElementsByTagName("span");
		
		// 답글 내용 바꿔주기. replace 사용 가능.
		span[1].innerText=reply;
		
		// 수정을 위해 생성한 태그 삭제하기.
		// 기존 번호를 이용해 수정 태그 가져오기.
		var upDiv=document.getElementById("up"+bunho);
		bunhoDiv.removeChild(upDiv);
	}
}





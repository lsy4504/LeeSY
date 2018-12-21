<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
  
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<SCRIPT type="text/javascript" src="${cPath }/js/jquery-3.3.1.min.js"></SCRIPT>
<SCRIPT type="text/javascript">
	$(function(){
		if(!WebSocket){
			alert("너는 못할듯..;");
			return;
		}
		var url="ws://"+location.host;
		if(location.port) url+= ":"+location.port;
		url += "<c:url value='/chatting' />";
		var ws;
		$("#enterBtn").on("click",function(){
			var nickName=$("#nickName").val();
			if(!nickName) ninkName="이익명";
			url+="/"+encodeURIComponent(nickName);
			ws = new WebSocket(url);
			ws.onopen=function(event){ console.log(event);}
			ws.onclose=function(event){ console.log(event);}
			ws.onmessage=function(event){
				try{
				var chattingMessage=JSON.parse(event.data);
				var name=chattingMessage.sender.name;
				var message=chattingMessage.message;
				$("#messageArea").append(
					$("<div />").append($("<div />").text(name+":"+message)
							
							
					)		
				);		
				}catch(e){
					$("#messageArea").append(
							$("<div />").append($("<div />").text(event.data))		
						);		
					
				}
			}
		});
		$("#message").on("keydown",function(event){
			if(event.keyCode!=13) return true;
			var msg=$(this).val();
			ws.send(msg);
			$(this).val("");
		});
		$("#outBtn").on("click",function(){
			ws.close();
		});
		
		
	});

</SCRIPT>
</head>
<body>
	<INPUT  type="text"  id="nickName"/>
	<INPUT  type="button" value="단톡방 입장" id="enterBtn"/>
	<INPUT  type="button" value="단톡방 퇴장" id="outBtn"/>
	<br/>
	<INPUT  type="text"  id="message"/>
	
	<DIV id="messageArea">
		
	</DIV> 
</body>
</html>
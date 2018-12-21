<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<STYLE type="text/css">
.sendMessage{
	background-color: yellow;
}
.receiveMessage{
background-color: blue; 
}
</STYLE>
<SCRIPT type="text/javascript" src="${cPath }/js/jquery-3.3.1.min.js"></SCRIPT>
<SCRIPT type="text/javascript">
	$(function(){
		if(WebSocket){
			$("#messageArea").append(
				$("<div />").text("웹 소켓 지원")
			);
			var url="ws://"+location.host;
			if(location.port) url+=":"+location.port;
			url +="<c:url value='/echoTest'/> ";
			var webSocket=new WebSocket(url);
			webSocket.onopen=function(event){
				console.log("연결 성공" +event);
			}
			webSocket.onclose=function(event){
				console.log("연결 종료" +event);
			}
			webSocket.onmessage=function(event){
				var msg= event.data;
				$("#messageArea").append(
						$("<div />").text("receive message:"+ msg).addClass("receiveMessage")		
					);
			}
			webSocket.onerror=function(event){
				console.log("에러 발생" +event);
			}
			$("#sendBtn").on("click",function(){
				var msg=$("#message").val();
				webSocket.send(msg);
				$("#messageArea").append(
						$("<div />").text("send message:"+ msg).addClass("sendMessage")		
					);
			});
			$("#closeBtn").on("click",function(){
				webSocket.close();
			});
		}
	});
</SCRIPT>
</head>
<body>
<INPUT type="text" id="message"/>
<INPUT type="button" value="전송" id="sendBtn"/>
<INPUT type="button" value="종료" id="closeBtn"/>
<DIV id="messageArea">

</DIV>

</body>
</html>
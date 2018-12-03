<%@page import="kr.or.ddit.web.calculate.EnumSystem"%>
<%@page import="kr.or.ddit.web.calculate.Browser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String user= request.getHeader("user-agent");
	String bro=null;
	String sys=null;
	Browser[] browser=Browser.values();
	Browser bb=Browser.getBrowser(user);
// 	for( Browser b:browser){
// 		if(user.toUpperCase().contains(b.name()))
// 		{
// 			bb=b;
// 			break;
// 		}
// 	}
	EnumSystem enumSystem=EnumSystem.getEnumSyutem(user);
// 	for(EnumSystem e : EnumSystem.values())
// 	{
// 		if(user.toUpperCase().contains(e.name())){
// 			enumSystem=e;
// 			break;
// 		}else{
// 			enumSystem.setSystem("데스크탑");
// 		}
// 	}
// 	if(user.contains("WOW")){
// 		bro="당신은 익스플로어네?";
// 	}else if(user.contains("Firefox"))
// 	{
// 		bro="너는 불여우";
// 	}else if(user.contains("Chrome")){
// 		bro="너는 크롬";
// 	}
// 	if(user.contains("Android")){
// 		sys="안드로이드";
// 	}else if(user.contains("Iphone")){
// 		sys="아이퐁";
// 	}else{
// 		sys="컴퓨터네?";
// 	}
%>
<meta charset="UTF-8">
<title>03/userAgent.jsp</title>
</head>
<body>
<!-- 클라이언트의 시스템과 브라우저에 대한 정보를 확인. -->
<!-- 클라이언트의 시스템이 -->
<!-- 데스크탑이라면, "데스크탑 접속" -->
<!-- 브라우저가 크롬이라면 "당신의 브라우저는 크롬" -->
<!-- 브라우저가 불여우라면 " 당신의 블아ㅜ저는 불여우" -->
<!-- 와 같은 형태의 메시지를 출력. -->
<div id="msgArea">
<%=bb.getBro()%><br>
<%=enumSystem.getSystem() %>
</div>
</body>
</html>
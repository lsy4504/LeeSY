<%@page import="kr.or.ddit.CommonException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>웹 어플리케션의 에러 처리방법</h4>
	<pre>
		1.지역적 처리 (각 jsp에 대해 직접 에러처리 페이지를 설정, page 지시자의 erropage 속성)
		2.전역적 처리 (web.xml에 어플리케이션 자체를 대상으로 에러 처리 페이지 설정 error-page 태그)
			1)예외 타입별 처리(exception-type)
			2)에러 상태 코드별 처리(error_code)
		** 지역처리 > 예외 타입별 처리 > 에러 상태 코드별 처리
		스 프 링 글 스 위 스 웨 덴 마 크 로 아 티 아 르 헨 티 나 이 지 리 아 
	
	</pre>


</body>
</html>
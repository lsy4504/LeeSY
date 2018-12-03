<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
// 	session.removeAttribute("id");
	session.invalidate();
	//세션스코프안에있는 모 든 객체 삭제
	//요청이 새로들어오면 새로운 세션생성
	//이동(index.jsp)
	response.sendRedirect(request.getContextPath()+"/");
%>

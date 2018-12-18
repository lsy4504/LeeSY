<%@page import="kr.or.ddit.vo.AlbasengVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Include</h4>
<pre>
	1.동적 include : 실행중(runtime), 실행 결과물들이 include
					서버사이드 페이지 모듈화에 주로 사용됨.
					1)ReqeustDispatcher 를 사용
					2)PageContext를 사용
					3)액션 태그를 사용
					JSP 스펙에 따라 기본적으로 제공되는 커스텀 태그
					&lt;prifix:tagname attributes..../&rt;
					-forward: request dispatch 방식의 forward
					-include: request dispatch 방식의 include
					-useBean
						(setProperty/getProperty)
					<jsp:useBean id="albaVO" class="kr.or.ddit.vo.AlbasengVO" 
 						scope="page"
 					/> 
 					<jsp:setProperty property="name" name="albaVO" value="이름"/>
<%--  					<jsp:setProperty property="age" name="albaVO" param="age"/> --%>
 					<jsp:setProperty property="*" name="albaVO" />
 					<jsp:getProperty property="name" name="albaVO" />
 					<jsp:getProperty property="age" name="albaVO" />
					<%-- 
						AlbasengVO albaVO=(AlbasengVO) pageContext.getAttribute("alvaVO");
						if(albaVO==null) {albaVO=new AlbasengVO();
							pageContext.setAttribute("alvaVO", albaVO);
						}
						albaVO.setName("이름");
						alvaVO.setAge(new Interger(request.getParmeter("age")));
					--%>
					<%= albaVO %>
	<%
		pageContext.include("/includee/codeFragment.jsp");
	%>
	2.정적 include : 실행전, 소스자체가 include
					include 지시자 이용
					중복 코드 해결에 주로 사용.
					웹 어플리케이션에 전역적으로 정적 include를 할때, 
					web.xml의 include-coda/pleud 등이 활용됨( 비추함 -by 최의현)
<%-- <%@ include file="/includee/codeFragment.jsp" %> --%>
<%-- 	<%= varAtfrag.length() %> --%>
	<%-- 정적 인클루드는 자신이 짠 코드가 아닌이상 위치찾기가 어려우므로 추천해주지 않음. --%>
	
</pre>
</body>
</html>
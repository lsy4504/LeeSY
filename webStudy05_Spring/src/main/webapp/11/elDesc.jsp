<%@page import="java.util.Date"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/elDesc.jsp</title>
</head>
<body>
<h4>EL(표현언어, Expression Language)</h4>
<pre>
	:데이터를 출력할 목적의 스크립트 언어.
	1.네가지 scope 내의 attribute 데이터에 대한 접근 방법 제공(**)
	\${속성명(EL변수) } 	
		:속성을 검색시, 영역에 대한 명시가 없는 경우, 네가지 영역을 순차적으로 검색
		:직접 접근시, pageScope,requestScope,sessionScope,applicationScope 객체 새용
	2.EL연산자 제공 : 피연산자로 리터럴이나 속성만 사용가능
		1)산술연산자 : +,-,*,/,% -> 기본으로 실수 연산 수행 (double)
					+는 산술연산자의 기능만 수행
					+는 연결연산자 기능을 못함..
			${2/3},${3%2 },${"1"+1 },${"3"/2 }
		<%-- -- 	  ${"a"+3 } -->--%> 
		2)논리연산자 : AND(&&,and), OR(||,or), NOT(!,not)
		<% 
			Boolean bool=new Boolean(true);
			pageContext.setAttribute("bool", bool);
		%>
		${bool2 }|	${bool2 and true }	| ${not bool }
					
		3)비교연산자 : ==(eq), !=(ne) , &gt;(gt) , &lt;(lt) ,&gt;=(ge),&lt;=(le)
			${3 gt 2 }, ${bool ne false }
		4)단항연산자 :++/--(불가, EL 3.0 부터 지원),empty(***)
		<% 
			String test="	";
			pageContext.setAttribute("testAttr", test);
			List<String> testList=Arrays.asList("a","b");
			pageContext.setAttribute("testList", testList);
		%>
		${empty testAttr }, ${empty testList }
		5)삼항연산자: 조건식? 참표현:거짓표현
		${empty testList? "컬렉션 비어있어":"가득참"}
		
	3.자바객체의 메소드에 대한 접근 방법 제공(since EL 2.2 version)
		<% 
			MemberVO member= new MemberVO("a001","asdf");
			pageContext.setAttribute("member", member);
			member.setMem_add1("대정");
			member.setMem_add2("개발원");
		%>
		${member.getMem_id()},${member.mem_id } <!-- 변수명이아닌 겟메서드명을 가지고옴 -->
		${member.getAddress() },${member.address }
	
	4.집합객체의 요소에 대한 접근 방법 제공
	5. el의 기본객체 제공
	
	<% 
		Date today=new Date();
		pageContext.setAttribute("today", today+"pageScope");
		request.setAttribute("today", today+"requestScope");
		//스코프 영역에 담겨야만 el태그에서 사용 가능함.
		//범위가 작은순서대로 찾
		//특정한 스코프의 영역에서 찾고싶을때는 스코프명을 적어주면됨
	%>
	<%= today %>,${requestScope.today}
</pre>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>
<script type="text/javascript">
<c:if test="${not empty message }">
	alert("${message }");
	<c:remove var="message" scope="session"/>
</c:if>
</script>
</head>
<body>
	<form action="<c:url value='/common/login.do'/>" method="post">
		<ul>
			<li>
				<c:set var="savedId" value="${cookie['id']['value'] }"/>
			아이디 : <input type="text" name="mem_id"
				value="${savedId }" /> <label> <input
					type="checkbox" name="idChecked" value="idSaved"
					 ${not empty savedId? "checked":"" } />아이디 저장하기
			</label> <%-- 체크하면전송 체크안하면 비전송 
				1. 쿠키로 기억 일주일동안 살리기... ㅎ
				2. 일주일동안 아이디 상태복원
				3. 근데 체크상태도 유지해야함
				----
				1.로그인 성공 과정에서 체크 안하면 
				기존의 쿠키 제거 
			
			
			--%>
			</li>
			<li>비밀번호 : <input type="password" name="mem_pass" /> <input
				type="submit" value="로그인" />
			</li>
		</ul>

	</form>
</body>
</html>
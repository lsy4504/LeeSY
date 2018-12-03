<%@page import="kr.or.ddit.utils.CookieUtil"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String mem_id = request.getParameter("mem_id");
	String message = (String) session.getAttribute("message");
	String idChecked = new CookieUtil(request).getCookieValue("id");

// 		if(mem_id==null || mem_id.trim().length()==0){
// 			mem_id="";
// 		}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>
<script type="text/javascript">
	<%if (StringUtils.isNotBlank(message)) {%>
	alert("<%=message%>");
<%session.removeAttribute("message");
			}%>
	
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/login/loginCheck.jsp"
		method="post">
		<ul>
			<li>아이디 : <input type="text" name="mem_id"
				value="<%=Objects.toString(idChecked, "")%>" /> <label> <input
					type="checkbox" name="idChecked" value="idSaved"
					<%=StringUtils.isNotBlank(idChecked) ? "checked" : ""%> />아이디 저장하기
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
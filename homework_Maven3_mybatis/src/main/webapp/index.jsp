<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.utils.CookieUtil"%>
<%@page import="kr.or.ddit.web.modulize.ServiceType"%>
<%@page import="kr.or.ddit.web.Service"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
	String commend=request.getParameter("commend");
	int statusCode=0;
	String includePage=null;
	if(StringUtils.isNotBlank(commend)){
		
		try{
			ServiceType serviceType= ServiceType.valueOf(commend.toUpperCase());
			includePage=serviceType.getServicePage();
		}catch(IllegalArgumentException e){
			statusCode= HttpServletResponse.SC_NOT_FOUND;
		}
	}
// 	if(commend!=null){
// 	Service service =Service.getService(commend);
	
// 	commend=service.getCommend();
// 	System.out.print(commend);
// 	}
	
	//서비스 제공 못하면 낫파운드~
	new CookieUtil(request).getCookie(request.getParameter("img"));
	
%>
<meta charset="UTF-8">
<title>/index.jsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
	<link href="<%=request.getContextPath() %>/css/Calendar.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath() %>/css/select.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/css/main.css" rel="stylesheet"> 

<script type="text/javascript">
function goLog(commend) {
	var logform=document.logForm;
	logform.commend.value=commend;
	logform.submit();
}
</script>
</head>
<body>
	<div id='top' class="top">
		<jsp:include page="/includee/header.jsp"></jsp:include>
	</div>
	<div id='left' class="left">
		<jsp:include page="/includee/left.jsp"/>
	</div>
	<div id='body' class="body">
	
	<%
	if(!(StringUtils.isNotBlank(includePage))){%>
		<h4>웰컴 페이지</h4>
		<pre>
	처음부터 웰컴 페이지로 접속하거나,
	로그인에 성공해서 웰컴 페이지로 접속하는 경우의 수가 있음.
	<% 
			if (authMember!=null) {
				out.println("<a href='"+request.getContextPath() +"/member/mypage.do'>"+authMember.getMem_name() + "</a>님("+authMember.getMem_auth() +")");
				out.print(String.format("<a href='%s'>로그아웃</a>", request.getContextPath() + "/login/logout.jsp"));
			} else {
				out.print(
						String.format("<a href=%s>로그인 하러가기</a>", "javascript:goLog('login');"));
			}
	}else{
		%>
		<jsp:include page="<%=includePage %>"/>
	<%
	}
	%>
		<%--
		
	String min=request.getParameter("minDan");
			System.out.print(min);
		
		if(!(StringUtils.isNotBlank(min))){
			System.out.print("야이놈아");
			--%>
			
			<%-- 
		}
		--%>
	
</pre>
	<form method="post" name="logForm" >
	<input name="commend" value="" type="hidden" />
	</form>
	</div>
	<div id='footer' class='footer'>
<%-- 	<iframe src="<%=request.getContextPath()%>/includee/footer.jsp"></iframe> --%>
	<%
		pageContext.include("/includee/footer.jsp");
	%>
<%-- 		<jsp:include page="/includee/footer.jsp"/> --%>
	</div>
</body>
</html>
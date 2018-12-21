<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
	.flagImg{
		width:20px;
		height:20px;
	}
</style>

<ul>
	<li><a href="<%= request.getContextPath() %>/member/memberList.do">회원관리</a></li>
	<li><a href="<%= request.getContextPath() %>/prod/prodList.do">상품관리</a></li>
	<li><a href="<%= request.getContextPath() %>/buyer/buyerList.do">거래처관리</a></li>
	<li>방영록</li>
	<li><a href="<%= request.getContextPath() %>/board/boardList.do">자유게시판</a></li>
	<li>
	<input class="flagImg" type="image" src="<c:url value='/images/korea.png'/>"
			onclick="location.href='?locale=ko'" name="click" value="ko">
		<input class="flagImg" type="image" src="<c:url value='/images/america.png'/>"
			onclick="location.href='?locale=en'" name="click" value="en">
	</li>
</ul>

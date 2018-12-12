<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/boardBook.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script> 
<script type="text/javascript">
$.getContextPath = function(){
	return "${pageContext.request.contextPath }";
}

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:url value='/boardBook/boardBookInsert.do' var="insert"/>
<body>
<form action="${insert}" id="insertBoard" method="post" name="insertBoard" >
	<input type="hidden" value="${pageContext.request.remoteAddr }" name="bo_ip">
	<table>
		<tr>
			<th>작성자</th>
			<th>비밀번호</th>
		</tr>
		<tr>
			<td><input type="text" name="bo_writer" ></td>
			<td><input type="password" name="bo_pass" ></td>
		</tr>
		<tr>
			<td><input type="text" name="bo_profile"></td>
			<td><textarea rows="10" cols="30" name="bo_content" ></textarea> </td>
		</tr>
		<tr>
			<td><input type="button" value="등록" id="insertBtn">  
			<input type="reset" value="취소">  </td>
		</tr>
	</table>
</form>
<br>
<br>
<div id="boardBookList">
<c:if test="${not empty paging.dataList }">
	<c:forEach items="${paging.dataList }" var="boardBook" >
		<hr>
		NO.${boardBook.bo_no}&nbsp;&nbsp;${boardBook.bo_writer }&nbsp;&nbsp;${boardBook.bo_date }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>수정</span><span>삭제</span>	
		<hr>
		${boardBook.bo_content }
	</c:forEach>
</c:if>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set  var="cPath" value="${pageContext.request.contextPath }" scope="application"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${cPath }/js/ckeditor/ckeditor.js"></script>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="" >
	<table>
	
		<tr>
			<th>제목</th>
			<td colspan="5"><input type="text" name="bo_title" value="${board.bo_title }"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="board_writer" value="${board.board_writer }"></td>
			<th>비밀번호</th>
			<td><input type="password" name='bo_pass'></td>
			<th>메일</th>
			<td><input type="text" name="bo_mail" value="${board.bo_mail }"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="5"> 
			<textarea id="bo_content" rows="30" cols="100" name="bo_content"  >${board.bo_content }</textarea>
			</td>
		</tr>
		<tr>
			<th>파일</th>
			<td><input type="file" name="bo_file"> </td>
			<td><input type="file" name="bo_file"> </td>
			<td><input type="file" name="bo_file"> </td>
		</tr>
		<tr>
			<td> <input type="submit" value="등록">  
			<input type="reset" value="취소">  
			 <input type="button" value="뒤로가기" onclick="history.back()">  </td>
		</tr>
	
	
	</table>
	<input type="hidden" value="${pageContext.request.remoteAddr }" name='bo_ip'>

</form>
<script type="text/javascript">
	CKEDITOR.replace( 'bo_content' );
</script>

</body>
</html>
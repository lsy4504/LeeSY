<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
	    var fileForm = document.fileForm;
		$("#fileList>li").on("dblclick", function() {
			alert($(this).attr('value'));
			fileForm.path.value=$(this).attr('value');
			fileForm.name.value=$(this).text();
			fileForm.submit();

		});
// 		$("#fileList>li").on("drag", function() {
// 			alert($(this).attr('value'));
// 		});
// 		$("#fileList>li").on("dropenter", function() {
// 			alert($(this).attr('value'));
// 		});
	})
	
</script>
<title>Insert title here</title>
</head >

	<jsp:useBean id="file" class="java.util.ArrayList" scope="request" />
	<ul id="fileList" ondr>
		<%
// 			String delCheck="<input type='checkbox' name='del' value='%s'/>";
			String pattern = "<li value=%s >%s  </li>";
			for (Object name : file) {
				File ss = (File) name;
// 				out.print(String.format(delCheck, ss.getAbsolutePath()));
				out.print(String.format(pattern, ss.getAbsolutePath(), ss.getName()));
			}
		%>
	</ul>
	<form method="get" name="fileForm" >
	<input name="path" value="" type="hidden" />
	<input name="name" value="" type="hidden" />
	<input type="radio" name="option" value="move">이동
	<input name="option"  value="del" type="radio">삭제
	<input name="option"  value="copy" type="radio">복사
	</form>
</body>
</html>
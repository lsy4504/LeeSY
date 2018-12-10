<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cPath" value="${pageContext.request.contextPath }"
	scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${cPath }/js/ckeditor/ckeditor.js"></script>

</head>
<c:if test="${not empty message}"> 
<script type="text/javascript">
	alert("${message}");
</script>
</c:if>
<body>
	<form method="post" enctype="multipart/form-data" action="" id="form">
		<table>
			<tr>
				<th>제목</th>
				<td colspan="5"><input type="text" name="bo_title"
					value="${board.bo_title }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="board_writer"
					value="${board.board_writer }"></td>
				<th>비밀번호</th>
				<td><input type="password" name='bo_pass'></td>
				<th>메일</th>
				<td><input type="text" name="bo_mail" value="${board.bo_mail }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="5"><textarea id="bo_content" rows="30" cols="100"
						name="bo_content">${board.bo_content }</textarea></td>
			</tr>
			<c:if test="${not empty board.pdsList }">
					<tr>
				<c:forEach items="${board.pdsList }" var="pds">
						<td>
							<ul>
								<li >${pds.pds_filename }<span class="delspan"  id="${pds.pds_no }" >X</span>
								</li>
							</ul>
						</td>
				</c:forEach>
					</tr>
			</c:if>
			<tr>
				<th>파일</th>
				<td><input type="file" name="bo_file"></td>
				<td><input type="file" name="bo_file"></td>
				<td><input type="file" name="bo_file"></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="등록"> <input
					type="reset" value="취소"> <input type="button" value="뒤로가기"
					onclick="history.back()"></td>
			</tr>


		</table>
		<input type="hidden" value="${pageContext.request.remoteAddr }"
			name='bo_ip'>
		<input type="hidden" value="${board.bo_no }"
			name='bo_no'>

	</form>
	<script type="text/javascript">
	$(".delspan").on("click",function(){
		alert("들어는오나?")
	var pds_no=$(this).prop("id");
		alert(pds_no);
		var tag="<input type='hidden' name='delFiles' value=%V>"
		
		$(this).parent().remove();
		$("#form").append(tag.replace("%V",pds_no)); 
		
	})
</script>
	<script>
		CKEDITOR
				.replace(
						'bo_content',
						{

							extraAllowedContent : 'h3{clear};h2{line-height};h2 h3{margin-left,margin-top}',

							// Adding drag and drop image upload.
							extraPlugins : 'uploadimage',
							uploadUrl : '${pageContext.request.contextPath}/board/uploadImage.do',

							// Configure your file manager integration. This example uses CKFinder 3 for PHP.
							filebrowserImageUploadUrl : '${pageContext.request.contextPath}/board/uploadImage.do',

							height : 560,

							removeDialogTabs : 'image:advanced;link:advanced'
						});
	</script>

</body>
</html>
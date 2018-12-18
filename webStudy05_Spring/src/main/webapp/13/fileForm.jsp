<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/fileForm.jsp</title>
</head>
<body>
<!-- 클라이언트로부터 이미지를 업로드 받고, 다시 현재 페이지로 돌아와서
하단에 있는 div 태그네에 해당 이미지가 출력되로록.,..
단 이미지 파일의 메타데이터들도 함꼐 출력되로록.... -->
	<h4>파일 업로드 양식</h4>
	<form action="<c:url value='/upload_2' ></c:url>" method="post" enctype="multipart/form-data">
		<ul>
			<li>업로더:<input type="text" name="uploader"/>
			</li>
			<li>업로드할 파일:<input type="file" name="selectFile"/> 
			<input type="submit" value="전송행"/>
			</li>
		</ul>






	</form>
<c:set var="fileVO" value="${sessionScope.fileVO } "/>
<c:if test="${not empty fileVO }"> 
<div>
	
	<img src='<c:url value='${imgMeta["Url"] }'/> '/>
	<p>
	${imgMeta["uploader"] },
	</p>
	<p>
	${imgMeta["originalFilename"] },
	</p>
	<p>
	${imgMeta["filesize"] },
	</p>
	<p>
	${imgMeta["saveFileAb"] },
	</p>
</div>	
</c:if>
</body>
</html>
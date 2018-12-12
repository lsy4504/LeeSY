<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/boardBook.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
	
<script src="http://malsup.github.com/jquery.form.js"></script> 
<script type="text/javascript">
$.getContextPath = function(){
	return "${pageContext.request.contextPath }";
}

</script>
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

<table id="boardBookTable">
<c:if test="${not empty paging.dataList }">
	<c:forEach items="${paging.dataList }" var="boardBook" >
	<tr>
		<td>NO.${boardBook.bo_no}</td><td name='writer'>${boardBook.bo_writer }</td><td> ${boardBook.bo_date } </td><td><span id="updateBoard" bono='${boardBook.bo_no}' data-toggle='modal' class='boardUpdateBtn'>[수정]</span></td><td><span id="deleteBoard">[삭제]</span></td>	
	</tr>
	<tr id='${boardBook.bo_no}'>
		 <td  class='.content'>${boardBook.bo_content }</td>
	</tr>	
	</c:forEach>
</c:if>
</table>
	<form  method="post" id="updateBoardForm" action="${pageContext.request.contextPath}/boardBook/boardBookUpdate.do">
		<table>
			<tr>
				<td><input type="hidden" name="bo_pass">
				</td>
			</tr>
			<tr>
				<td colspan="2"><textarea hidden="hidden" rows="10" cols="50"
						name="bo_content"></textarea></td>
			</tr>
			<tr>
				<td>
				 <input type="hidden"  name="rep_no">
				 <input type="hidden" value="${board.bo_no }" name="bo_no"></td>
			</tr>
		</table>
	</form>

<div class="modal fade" id="updateBoardModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">정말로 삭제할거야?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form onsubmit="retunr false;" id="modalForm1">
        비밀번호:<input type="password" id="bo_pass">
       
        <input type="hidden" id="rep_no">
        <textarea rows="10" cols="50"
						name="bo_content" id="bo_content"></textarea>
        <input type="hidden" value="${board.bo_no }" id="bo_no">
      </form>
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary"  data-dismiss="modal" id="updateBtn"  >수정</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>
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
<link href="${pageContext.request.contextPath }/css/ul.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/css/form.css" rel="stylesheet" type="text/css">
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
<c:url value='/replyBook/replyBookList.do' var="replyList"/>
<body>
<div id="form-main">
  <div id="form-div">
<form action="${insert}" id="insertBoard" method="post" name="insertBoard" class="form">
	<input type="hidden" value="${pageContext.request.remoteAddr }" name="bo_ip">
			<p class="name">
			<input type="text" name="bo_writer" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="Name" id="name" >
			</p>
			<p class="email">
			<input type="password" name="bo_pass"  class="validate[required,custom[email]] feedback-input" id="email" placeholder="Email">
			</p>
			<p class="email">
			<input type="text" name="bo_profile" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="Comment">
			</p>
			  <p class="text">
			<textarea  name="bo_content" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="Comment" ></textarea>
			</p>
			<div class="submit">
			<input type="button" value="등록" id="insertBtn">
			<input type="reset" value="취소">  
			 <div class="ease"></div>
			</div>  
</form>
 <form action="${replyList }" id="replyForm" method="post"> 
 	<input type="hidden" name="bo_no">
 </form>
</div>
<br>
<br>

<c:if test="${not empty paging.dataList }">
	<div class="container group" id="boardBookTable">
	<c:forEach items="${paging.dataList }" var="boardBook" >
		<div class="grid-1-5" id='${boardBook.bo_no }' >
			<h2>NO.${boardBook.bo_no}</h2>
			<h3><span class="uppercase" name='writer'>${boardBook.bo_writer }</span></h3>
			<p class='content'>${boardBook.bo_content }</p>
	<ul>
	<li>${boardBook.bo_date }
	</li>
	<li><span id="updateBoard" bono='${boardBook.bo_no}' data-toggle='modal' class='boardUpdateBtn'>[수정]</span>
	</li>
	<li><span id="deleteBoard" bono='${boardBook.bo_no}' data-toggle='modal' class='boardUpdateBtn'>[삭제]</span>
	</li>
	<li><span id="replyList" bono='${boardBook.bo_no}' class="button">댓글 보기</span>
	</li>
	</ul>
	<div id='re_${boardBook.bo_no }'>
	
	</div>
	</div>
	</c:forEach>
	</div>
</c:if>
	<form  method="post" id="updateBoardForm" action="${pageContext.request.contextPath}/boardBook/boardBookUpdate.do">
		<table>
		<tbody>
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
		</tbody>
			<tfoot>
		<tr>
		<td colspan="7">
		<nav aria-label="Page navigation example" id='page2'>
			${paging.pagingHTML }
			</nav>
			</td>
			</tr>
		</tfoot>
		</table>
	</form>

<div class="modal fade" id="updateBoardModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">입력해봐</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form onsubmit="retunr false;" id="modalForm1">
       작성자:<input type="text" id="bo_writer">
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
</div>
</body>
</html>
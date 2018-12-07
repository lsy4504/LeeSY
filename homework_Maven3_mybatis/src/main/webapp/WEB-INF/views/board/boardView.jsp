<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/board.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>

	<script src="http://malsup.github.com/jquery.form.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/replyProcess.js"></script>
<script type="text/javascript">
	$.getContextPath = function(){
		return "${pageContext.request.contextPath }";
	}
	alert($.getContextPath());
</script>		

<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>글번호</th>
			<td>${board.bo_no}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.board_writer}</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${board.bo_pass}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${board.bo_ip}</td>
		</tr>
		<tr>
			<th>메일</th>
			<td>${board.bo_mail}</td>
		</tr>
		
		<tr>
			<th>제목</th>
			<td>${board.bo_title}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach var="pds" items="${board.pdsList }" varStatus="vs">
				<c:url value="/board/download.do" var="downloadURL">
					<c:param name="what" value="${pds.pds_no }"> </c:param>
				</c:url>
				<a href="${downloadURL }">
					${pds.pds_filename } 
				</a>
					<c:if test="${not vs.last }">&nbsp;|&nbsp;</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.bo_content}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.bo_date}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.bo_hit}</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>${board.bo_rcmd}</td>
		</tr>
	</table>
	<c:if test="${not empty board.replyList }">
		<table>
			<thead>
				<tr>
					<th>댓글번호</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>내용</th>
				</tr>
			</thead>
			<tbody id="listBody">
				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="1">
						<nav aria-label="Page navigation example" id="pagingArea">
						</nav>
					</td>

				</tr>


			</tfoot>
		</table>
	</c:if>
	
	<form  method="post" id="insertReply" action="${pageContext.request.contextPath}/reply/replyInsert.do">
		<table>
			<tr>
				<td>작성자 <input type="text" name="rep_writer" >
				</td>
				<td>비밀번호<input type="password" name="rep_pass">
				</td>
			</tr>
			<tr>
				<td colspan="2"><textarea rows="10" cols="50"
						name="rep_content"></textarea></td>
			</tr>
			<tr>
				<td>
				<input type="submit" value="등록" >
<!-- 				<input type="submit" value="수정" onclick="javascript:intercept('/reply/replyUpdate.do')"> -->
				 <input type="hidden"  name="rep_no">
				 <input type="hidden" value="${board.bo_no }" name="bo_no"></td>
			</tr>
		</table>
	</form>

	
	<div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
        비밀번호:<input type="password" id="rep_pass1">
       
        <input type="hidden" id="rep_no1">
        <textarea rows="10" cols="50"
						name="rep_content" id="rep_content"></textarea>
        <input type="hidden" value="${board.bo_no }" id="bo_no1">
      </form>
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary"  data-dismiss="modal" id="updateBtn"  >수정</button>
      </div>
    </div>
  </div>
</div>
	<div class="modal fade" id="dela" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">정말로 삭제할거야?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form onsubmit="retunr false;" id="modalForm">
        비밀번호:<input type="password" id="rep_pass">
        <input type="hidden" id="rep_no">
        <input type="hidden" value="${board.bo_no }" id="bo_no">
      </form>
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary"  data-dismiss="modal" id="modalBtn"  >삭제</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	function paging(page){
	
		pagingReply(page,${board.bo_no});
	}
	paging(1);
</script>
</body>
</html>
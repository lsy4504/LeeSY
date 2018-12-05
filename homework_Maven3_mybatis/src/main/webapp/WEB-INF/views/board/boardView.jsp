<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath() %>/css/board.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">	
  <script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript">
	function paging(page) {
		
		$.ajax({
			url : "${pageContext.request.contextPath}/reply/replyList.do",
			data :{
				bo_no:${board.bo_no },
				page:page
			},
			dataType : "json",
			success : function(resp) {
				
				var html=""
				if(resp.dataList){
					$.each(resp.dataList,function(idx,reply){
						html+="<tr>";
						html+="<td>"+reply.rep_no+"</td>";
						html+="<td>"+reply.rep_writer+"</td>";
						html+="<td>"+reply.rep_date+"</td>";
						html+="<td>"+reply.rep_content+"</td>";
						html+="</tr>";
					});
				}else{
					html+="<tr><td colspan='4'> 데이터없즘 .</td></tr>";
				}
				pagingArea.html(resp.pagingHTML);
				listBody.html(html);
			},
			error : function(resp) {
				console.log(resp.status);
			}
		});
	}
	$(function(){
		pagingArea=$("#pagingArea");
		listBody=$("#listBody")
		paging(1);
	})
</script>
<title>Insert title here</title>
</head>
<body>
<table>
	<tr><th>글번호</th><td>${board.bo_no}</td></tr>
<tr><th>작성자</th><td>${board.board_writer}</td></tr>
<tr><th>비밀번호</th><td>${board.bo_pass}</td></tr>
<tr><th>주소</th><td>${board.bo_ip}</td></tr>
<tr><th>메일</th><td>${board.bo_mail}</td></tr>
<tr><th>제목</th><td>${board.bo_title}</td></tr>
<tr><th>내용</th><td>${board.bo_content}</td></tr>
<tr><th>작성일</th><td>${board.bo_date}</td></tr>
<tr><th>조회수</th><td>${board.bo_hit}</td></tr>
<tr><th>추천수</th><td>${board.bo_rcmd}</td></tr>
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
	<c:forEach var="replyList" items="${board.replyList }"> 
		<tr>
			<td>${replyList.rep_no }</td>
			<td>${replyList.rep_writer }</td>
			<td>${replyList.rep_date }</td>
			<td>${replyList.rep_content }</td>
		</tr>	
	</c:forEach>
	</tbody>
	<tfoot>
	<tr>
		<td colspan="1">
		<nav aria-label="Page navigation example" id="pagingArea">
			${pagingVO.pagingHTML }		
		</nav>
		</td>
	
	</tr>
	
	</tfoot>
</table>
</c:if>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">	
  <script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--
	PagingInfoVO pagingVO= (PagingInfoVO)request.getAttribute("pagingVO");
	List<MemberVO> memberList=pagingVO.getDataList();
--%>
  <script type="text/javascript">
  function ${pagingVO.funcName}(page) {
		document.searchForm.page.value=page;
		document.searchForm.submit();
	};
  	
  </script>
<title>member/memberList.jsp</title>
</head>
<body>
	<h4>회원 목록</h4>
<input type="button" class="button" value="신규등록"
	onclick="location.href='${pageContext.request.contextPath }/member/memberInsert.do'"
/>

	<div class="container">
	<table class="table">
		<thead class="thead-dark">
		<tr  >
			<th>회원아이딬</th>
			<th>회원명</th>
			<th>주소</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>마일리지</th>
		</tr>
		 </thead>
		 <tbody>
		 <c:set var="memberList" value="${pagingVO.dataList }"/>
			 <c:if test="${not empty memberList }">
				<c:forEach items="${memberList }" var="member">
		 			<tr>
		 			<c:url value="/member/memberView.do" var="viewURL">
		 				<c:param name="who" value="${member.mem_id }"></c:param>
		 			</c:url>
		 			<td>${member.mem_id }</td>
		 			<td><a href="${viewURL}">${member.mem_id }</a></td>
		 			<td>${member.address }</td>
		 			<td>${member.mem_hp }</td>
		 			<td>${member.mem_mail }</td>
		 			<td>${member.mem_mileage }</td>
		 			</tr>
				
				
				</c:forEach>
			 </c:if>
			<c:if test="${empty memberList }">
			<tr>
		 		<td colspan="6">회원목록없음</td>
		 	</tr>s
			</c:if>		 

		 </tbody>
		 <tfoot>
		 	<tr>
		 		<td colspan='6'>
		 			<nav aria-label="Page navigation example">
 						${pagingVO.pagingHTML}
					</nav>
					<form name="searchForm">
					<input type="text" name="page"/>
					<select name="searchType">
						<option value="all">전체</option>
						<option value="name">이름</option>
						<option value="addr">지역</option>
					</select>
					<script type="text/javascript">
						document.searchForm.searchType.value = "${empty pagingVO.searchType? 'all':pagingVO.searchType}";
					</script>
					<input type="text" name="searchWord" value="${pagingVO.searchWord }"/>
					<input type="submit" value="검색">
					</form>
		 		</td>
		 	</tr>
		 </tfoot>
	</table> 
	</div>
</body>
</html>
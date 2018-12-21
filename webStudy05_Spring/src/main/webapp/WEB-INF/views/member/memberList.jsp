<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

  <script type="text/javascript">
  function ${pagingVO.funcName}(page) {
		document.searchForm.page.value=page;
		document.searchForm.submit();
	};
  	
  </script>

	<h4>회원 목록</h4>
		
<input type="button" class="button" value="신규등록"
	onclick="location.href='${pageContext.request.contextPath }/member/memberInsert.do'"
/>

	<div class="container">
	<table class="table">
		<thead class="thead-dark">
		<tr  >
			<th><spring:message code="member.mem_id" /> </th>
			<th><spring:message code="member.mem_name" /></th>
			<th><spring:message code="member.mem_address" /></th>
			<th><spring:message code="member.mem_hp" /></th>
			<th><spring:message code="member.mem_mail" /></th>
			<th><spring:message code="member.mem_mileage" /></th>
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
		 			<spring:message code="member.mem_title" arguments="${member.mem_id },${member.mem_mileage } " var="mem_title" />
		 			<td><a href="${viewURL}" title="${mem_title }">${member.mem_id }</a></td>
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

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="message" class="java.lang.String" scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://jqueryui.com/resources/demos/style.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<title>Insert title here</title>
<%
	List<Map<String, Object>> lprodList = (List) request.getAttribute("lprodList");
%>

<script type="text/javascript">
	$(function() {
		<c:if test="${not empty message  }">
		<c:remove var="message" scope="session" />
		alert("${message}");
		</c:if>

		$("[type='date']").datepicker({
			dateFormat : "yy-mm-dd"

		});
		$("#delBtn").on('click', function() {
			var chk = confirm(" 정말로 탈퇴함? 리얼 투르? 실화냐 구라 즐.");
			if (chk) {
				var id = document.viewForm.buyer_id;
				document.delForm.buyer_id.value = id;
				document.delForm.submit();
			}

		})
	});
</script>
</head>
<body>
	<jsp:useBean id="buyer" class="kr.or.ddit.vo.BuyerVO" scope="request"></jsp:useBean>
	<c:set value="false" var="mutable"></c:set>
	<c:if test="${not empty authMember }">
		<c:set value="true" var="mutable"></c:set>
	</c:if>
	<c:if test="${mutable }">

		<form name="delForm"
			action="${pageContext.request.contextPath }/buyer/buyerDelete.do"
			method="post">
			<input type="hidden" name="buyer_id" value="<%=buyer.getBuyer_id()%>" />
		</form>


	</c:if>
	<jsp:useBean id="errors" class="java.util.HashMap" scope="request"></jsp:useBean>
	<table>

		<tr>
			<th>판매자아이디</th>
			<td>${buyer.buyer_id }</td>
		</tr>
		<tr>
			<th>판매처명</th>
			<td>${buyer.buyer_name }</td>
		</tr>
		<tr>
			<th>판매목록</th>
			<td>${buyer.buyer_lgu }</td>
		</tr>
		<tr>
			<th>은행</th>
			<td>${buyer.buyer_bank }</td>
		</tr>
		<tr>
			<th>계좌번호</th>
			<td>${buyer.buyer_bankno }</td>
		</tr>
		<tr>
			<th>판매자명</th>
			<td>${buyer.buyer_bankname }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${buyer.buyer_zip }</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${buyer.buyer_add1 }</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${buyer.buyer_add2 }</td>
		</tr>
		<tr>
			<th>전번</th>
			<td>${buyer.buyer_comtel }</td>
		</tr>
		<tr>
			<th>팩스</th>
			<td>${buyer.buyer_fax }</td>
		</tr>
		<tr>
			<th>이멜</th>
			<td>${buyer.buyer_mail }</td>
		</tr>
		<tr>
			<th>대표자</th>
			<td>${buyer.buyer_charger }</td>
		</tr>
		<tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${empty buyer.buyer_delete?"활":"탈"}</td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="뒤로가기"
				onclick="history.back();" />
	 <c:if test="${mutable }">

					<input type="submit" value="수정" onclick="location.href='${pageContext.request.contextPath }/buyer/buyerUpdate.do?who=${buyer.buyer_id }'" />
					<input type="reset" value="리셋" />
					<input type="button" value="탈퇴하기" id="delBtn" /></td>
		</tr>
	</c:if>
	</table>
	<h4>판매상품목록</h4>
	<table calss="table">
		<thead>
			<tr>
			<th>품번</th>
			<th>품명</th>
			<th>매입가</th>
			<th>가격</th>
			<th>상세정보?</th>
			</tr>
		</thead>
		<tbody>
		<c:set var="prodList" value="${prod.prodList }"></c:set>
		<c:choose>
			<c:when test="${not empty prodList }">
				<c:forEach items="${ prodList}" var="p">
				<tr>
				<td>${p.prod_id }</td>
				<td>${p.prod_name }</td>
				<td>${p.prod_cost }</td>
				<td>${p.prod_price }</td>
				<td>${p.prod_outline}</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5"> 구매 노 상 구매 노상 거ㅈ;ㅣ냐고</td>
				</tr>
			
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>

</body>
</html>
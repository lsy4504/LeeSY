<%@page import="java.util.List"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Objects"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="buyer" class="kr.or.ddit.vo.BuyerVO" scope="request"></jsp:useBean>
<jsp:useBean id="errors" class="java.util.HashMap" scope="request"></jsp:useBean>
<jsp:useBean id="message" class="java.lang.String" scope="request"></jsp:useBean>

<%
	//  	String message= request.getParameter("message");
	List<Map<String, Object>> lprodList = (List) request.getAttribute("lprodList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
<script type="text/javascript">
$( function() {
<c:if test="${not empty message }">

	alert("${message}");
</c:if>
	$("[type='date']").datepicker({
			dateFormat : "yy-mm-dd"

		});
	});
</script>
</head>
<body>
	<form method="post">
		<table>
			<tr>
				<th>판매자아이디</th>
				<td><input type="hidden" name="buyer_id"
					value="${buyer.buyer_id}" /><span class="error">${errors["buyer_id"] }</span></td>
			</tr>
			<tr>
				<th>판매처명</th>
				<td><input type="text" name="buyer_name"
					value="${buyer.buyer_name}" /><span class="error">${errors["buyer_name"] }</span></td>
			</tr>
			<tr>
				<th>판매항목</th>
				<td><select name="buyer_lgu" id='sel'>
						<option value="">분류</option>
						<c:forEach items="${lprodList }" var="lprod">
							<c:if test="${lprod['LPROD_GU'] eq prod.prod_lgu }">
								<option value="${lprod['LPROD_GU']}" selected="selected">${lprod["LPROD_NM"]}</option>
							
							</c:if>
							<c:if test="${lprod['LPROD_GU'] ne prod.prod_lgu }" >
								<option value="${lprod['LPROD_GU']}">${lprod["LPROD_NM"]}</option>
							
							</c:if>
						</c:forEach>


				</select></td>
			</tr>
			<tr>
				<th>은행</th>
				<td><input type="text" name="buyer_bank"
					value="${buyer.buyer_bank}" /><span class="error">${errors["buyer_bank"] }</span></td>
			</tr>
			<tr>
				<th>계좌번호</th>
				<td><input type="text" name="buyer_bankno"
					value="${buyer.buyer_bankno}" /><span class="error">${errors["buyer_bankno"] }</span></td>
			</tr>
			<tr>
				<th>판매자명</th>
				<td><input type="text" name="buyer_bankname"
					value="${buyer.buyer_bankname}" /><span class="error">${errors["buyer_bankname"] }</span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="buyer_zip"
					value="${buyer.buyer_zip}" /><span class="error">${errors["buyer_zip"] }</span></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="buyer_add1"
					value="${buyer.buyer_add1}" /><span class="error">${errors["buyer_add1"] }</span></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="buyer_add2"
					value="${buyer.buyer_add2}" /><span class="error">${errors["buyer_add2"] }</span></td>
			</tr>
			<tr>
				<th>전번</th>
				<td><input type="text" name="buyer_comtel"
					value="${buyer.buyer_comtel}" /><span class="error">${errors["buyer_comtel"] }</span></td>
			</tr>
			<tr>
				<th>팩스</th>
				<td><input type="text" name="buyer_fax"
					value="${buyer.buyer_fax}" /><span class="error">${errors["buyer_fax"] }</span></td>
			</tr>
			<tr>
				<th>이멜</th>
				<td><input type="text" name="buyer_mail"
					value="${buyer.buyer_mail}" /><span class="error">${errors["buyer_mail"] }</span></td>
			</tr>
			<tr>
				<th>대표자</th>
				<td><input type="text" name="buyer_charger"
					value="${buyer.buyer_charger}" /><span class="error">${errors["buyer_charger"] }</span></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="등록"> 
				<input type="reset" value="취소"></td>
			</tr>


		</table>


	</form>
</body>
</html>
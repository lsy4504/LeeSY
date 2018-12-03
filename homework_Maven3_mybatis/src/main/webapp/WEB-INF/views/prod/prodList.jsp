<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:useBean id="pagingVO" class="kr.or.ddit.vo.PagingInfoVO"
	scope="request"></jsp:useBean>
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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<c:url value="/prod/prodView.do" var="prodView"></c:url>
<script type="text/javascript">
  	function ${pagingVO.funcName}(page) {
  		$("[name='searchForm']").find("[name='page']").val(page);
// 		document.searchForm.page.value=page;
		$("[name='searchForm']").submit();	
// 		document.searchForm.submit();
	};
  
  	$(function() {
  		var prod_lguTag=$("[name='prod_lgu']");
  		var prod_buyerTag=$("[name='prod_buyer']");
		prod_lguTag.val("${pagingVO.searchVO.prod_lgu}");
		prod_buyerTag.val("${pagingVO.searchVO.prod_buyer}");
  		
		$("[name='prod_lgu']").on("change",function(){
			var lprod_gu =$(this).val()
			var buyerOptions = $("[name='prod_buyer']").find("option");
			$(buyerOptions).hide();
			if(lprod_gu){
			var buyerOptions2=$("[name='prod_buyer']").find("option."+lprod_gu);
			$(buyerOptions2).show();
			}else{
			$(buyerOptions).show();
			}
				
		});
		$("#listBody").on("click","tr",function(){
			var prod_id=$(this).find("td:first").text();
			location.href="${prodView}?what="+prod_id;
			
		});
		var listbody=$("#listBody");
		var nav=$("#page2");
		$("[name='searchForm']").on("submit",function(event){
			event.preventDefault();
			var data=$(this).serialize();//쿼리스트링 생성~
			$.ajax({
				data :data,
				dataType : "json",
				success : function(resp) {
					alert(resp.getPagingHTML);
					var tag="<tr>";
					var pattern="<td>%V</td>"
					$.each(resp.dataList,function(idx,p){
						tag+=pattern.replace("%V",p.prod_id);
						tag+=pattern.replace("%V",p.prod_name);
						tag+=pattern.replace("%V",p.lprod_nm);
						tag+=pattern.replace("%V",p.buyer_name);
						tag+=pattern.replace("%V",p.prod_price);
						tag+=pattern.replace("%V",p.prod_outline);
						tag+=pattern.replace("%V",p.prod_mileage);
					tag+="</tr>";
					});
					listbody.html(tag);
					nav.html(resp.pagingHTML);
					$("[name='page']").val(" ");
				},
				error : function(resp) {

				}
			});
			return false;
		});
	});
  
  </script>
<title>Insert title here</title>
</head>
<body>
	<%
		// 	List<ProdVO> prodList= pagingVO.getDataList();
		// 	List<Map<String,Object>> lprodList= (List)request.getAttribute("lprodList");
		// 	List<BuyerVO> buyerList=(List<BuyerVO>)request.getAttribute("buyerList");
	%>
	<!-- 	스크린사이즈 7 -->
	<!-- 	블럭사이즈 4  -->
	<div class="container">
		<form name='searchForm'>
			<input type="text" name="page" /> <select name="prod_lgu">
				<option value="">분류선택</option>
				<c:forEach items="${ lprodList}" var="lprod">
					<option value="${lprod['LPROD_GU']}">${lprod['LPROD_NM']}</option>
				</c:forEach>

			</select> <select name="prod_buyer">
				<option value="">거래처 선택</option>
				<c:forEach items="${buyerList}" var="buyer">
					<option value="${buyer.buyer_id }" class="${buyer.buyer_lgu }">${buyer.buyer_name }</option>

				</c:forEach>


			</select> <input type="text" name="prod_name"
				value="${pagingVO.searchVO.prod_name }"> <input
				type="submit" value="검색">

		</form>
		<input type="button" class="btn btn-info" value="신규 상품등록"
			onclick="location.href='${pageContext.request.contextPath }/prod/prodInsert.do'" />
		<input type="image" src="<c:url value='/images/korea.png'/>"
			onclick="location.href='?click=ko'" name="click" value="ko">
		<input type="image" src="<c:url value='/images/america.png'/>"
			onclick="location.href='?click=en'" name="click" value="en">
			<c:if test="${not empty param.click }">
	<fmt:setLocale value="${param.click }"/>
	</c:if>
		<table class="table">
			<thead class="thead-dark">
				<fmt:bundle basename="kr.or.ddit.msgs.message">
					<tr>
						<th><fmt:message key="prod.prod_id"></fmt:message> </th>
						<th><fmt:message key="prod.prod_name" /></th>
						<th><fmt:message key="prod.prod_lgu" /></th>
						<th><fmt:message key="prod.prod_buyer" /></th>
						<th><fmt:message key="prod.prod_price" /></th>
						<th><fmt:message key="prod.prod_outline" /></th>
						<th><fmt:message key="prod.prod_mileage" /></th>
					</tr>
				</fmt:bundle>
			</thead>
			<tbody id="listBody">
				<c:set var="prodList" value="${pagingVO.dataList }" scope="request"></c:set>
				<c:if test="${not empty prodList }">
					<c:forEach items="${prodList}" var="prod">
						<tr>
							<td>${prod.prod_id}</td>
							<td>${prod.prod_id}</td>
							<td>${prod.lprod_nm}</td>
							<td>${prod.buyer_name }</td>
							<td>${prod.prod_price }</td>
							<td>${prod.prod_outline }</td>
							<td>${prod.prod_mileage }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty prodList }">
					<tr>
						<td>해당상품없음</td>
					</tr>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7">
						<nav aria-label="Page navigation example" id='page2'>${  pagingVO.pagingHTML }
						</nav>
					</td>
				</tr>
			</tfoot>

		</table>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="pagingInfoVO" class="kr.or.ddit.vo.PagingInfoVO" scope="request"></jsp:useBean>
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
   <script type="text/javascript">
  	function ${pagingInfoVO.funcName}(page) {
  		$("[name='searchForm']").find("[name='page']").val(page);
// 		document.searchForm.page.value=page;
		$("[name='searchForm']").submit();	
// 		document.searchForm.submit();
	};
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
				alert(resp.dataList[0].prod_id);
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
  	
  </script>
<title>Insert title here</title>
</head>
<body>
	<h4>회원 목록</h4>
	<form name='searchForm'>
			<input type="hidden" name="page" />
			<select name="searchType">
				<option value="buyer_name">판매처명</option>
				<option value="buyer_add1">지역</option>
			</select>
			<input type="text" name="searchWord" />
			
			<input type="submit" value="검색"> 
			 
		</form>
<input type="button" class="button" value="신규등록"
	onclick="location.href='${pageContext.request.contextPath }/buyer/buyerInsert.do'"
/>

	<div class="container">
	<table class="table">
		<thead class="thead-dark">
		<tr  >
			<th>판매자아디</th>
			<th>판매처</th>
			<th>주소</th>
			<th>전번</th>
			<th>은행</th>
			<th>계좌번호</th>
		</tr>
		 </thead>
		 <tbody>
		 <c:set var="buyerList" value="${pagingInfoVO.dataList }"></c:set>
		 <c:if test="${not empty buyerList }">
		 	<c:forEach var="buyer" items="${buyerList }">
		 		<tr>
		 			<td>${buyer.buyer_id }</td>
		 			<td><a href="${pageContext.request.contextPath }/buyer/buyerView.do?who=${buyer.buyer_id}">${buyer.buyer_name }</a></td>
		 			<td>${buyer.address }</td>
		 			<td>${buyer.buyer_comtel }</td>
		 			<td>${buyer.buyer_bank }</td>
		 			<td>${buyer.buyer_bankno }</td>
		 		</tr>
		 	</c:forEach>
		 </c:if>
		 <c:if test="${empty buyerList }">
		 	<tr>
		 	<td colspan="6">회원이 없어..</td>
		 	</tr>
		 </c:if>
		 
		 </tbody>
		 <tfoot>
		 	<tr>
		 		<td colspan='6'>
		 			<nav aria-label="Page navigation example" id='page2'>${  pagingInfoVO.pagingHTML }
						</nav>
		 		</td>
		 	</tr>
		 	<tr>
		 		
		 	</tr>
		 </tfoot>
	</table> 
	</div>
</body>

</body>
</html>
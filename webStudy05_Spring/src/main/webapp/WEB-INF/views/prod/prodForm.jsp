<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
%>
<c:if test="${not empty message }">
			alert("${request.message}");
</c:if>
<script type="text/javascript">
	$(function() {
		$("[name$='date']").datepicker({
			dateFormat:"yy-mm-dd"
		})	;
		var pattern = "<option value=%V> %T</option>";
		var prod_buyerTag=$("[name='prod_buyer']");
		var prod_lguTag=$("[name='prod_lgu']");
		$("#sel").on('change', function() {
			var lgu=$(this).val();
			var url="${pageContext.request.contextPath }/prod/getBuyerList.do";
			alert(lgu);
			$.ajax({
				url : url,
				method : "get",
				data : {
					prod_lgu:lgu
					
				},
				dataType : "json",
				success : function(resp) {//우리가 받아온 제이슨배열
					alert(resp);
					var options=pattern.replace("%V","").replace("%T","선택해")
					$.each(resp, function(idx, buyer){
						options+=pattern.replace("%V",buyer.buyer_id)
									.replace("%T",buyer.buyer_name);
					});
					prod_buyerTag.html(options);
					prod_buyerTag.val("${member.mem_buyer }");
				},
				error : function(resp) {

				}
			});
		});
		prod_lguTag.val("${member.mem_lgu }");
				prod_lguTag.trigger("change");
			})
</script>
	<form:form method="post" enctype="multipart/form-data" modelAttribute="prod">
		<table>
			<tr>
				<td><input type="hidden" name="prod_id"
					value="${prod.prod_id }"></td>
			<tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="prod_name"
					value="${prod.prod_name }" /><form:errors path="prod_name" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>판매항목</th>
				<td><select name="prod_lgu" id='sel'>
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
				<th>판매자</th>
				<td><select name="prod_buyer">
				</select></td>
			</tr>
			<tr>
				<th>매입가</th>
				<td><input type="text" name="prod_cost"
					value="${prod.prod_cost }" /><form:errors path="prod_cost" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>판매가</th>
				<td><input type="text" name="prod_price"
					value="${prod.prod_price }" /><form:errors path="prod_price" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>특가</th>
				<td><input type="text" name="prod_sale"
					value="${prod.prod_sale }" /><form:errors path="prod_sale" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>상품정보</th>
				<td><input type="text" name="prod_outline"
					value="${prod.prod_outline }" /><form:errors path="prod_outline" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>디테일</th>
				<td><input type="text" name="prod_detail"
					value="${prod.prod_detail }" /><form:errors path="prod_detail" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>이미지</th>
				<td><input type="file" name="prod_image" />
				<form:errors path="prod_img" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>토탈스톡</th>
				<td><input type="text" name="prod_totalstock"
					value="${prod.prod_totalstock }" /><form:errors path="prod_totalstock" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>입고날자</th>
				<td><input class="" type="text" name="prod_insdate"
					value="${prod.prod_insdate }" /><form:errors path="prod_insdate" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>프롭스톡</th>
				<td><input type="text" name="prod_properstock"
					value="${prod.prod_properstock }" /><form:errors path="prod_properstock" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>사잊브</th>
				<td><input type="text" name="prod_size"
					value="${prod.prod_size }" /><form:errors path="prod_size" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>색</th>
				<td><input type="text" name="prod_color"
					value="${prod.prod_color }" /><form:errors path="prod_color" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>배달</th>
				<td><input type="text" name="prod_delivery"
					value="${prod.prod_delivery }" /><form:errors path="prod_delivery" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>유닛</th>
				<td><input type="text" name="prod_unit"
					value="${prod.prod_unit }" /><form:errors path="prod_unit" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>슈령</th>
				<td><input type="text" name="prod_qtyin"
					value="${prod.prod_qtyin }" /><form:errors path="prod_qtyin" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>이수진바보</th>
				<td><input type="text" name="prod_qtysale"
					value="${prod.prod_qtysale }" /><form:errors path="prod_qtysale" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="text" name="prod_mileage"
					value="${prod.prod_mileage }" /><form:errors path="prod_mileage" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="전송" /> <input
					type="reset" value="취소" /> <input type="button" value="목록으로"
					onclick="location.href='${pageContext.request.contextPath}/prod/prodList.do';" />
				</td>
			</tr>
		</table>
	</form:form>

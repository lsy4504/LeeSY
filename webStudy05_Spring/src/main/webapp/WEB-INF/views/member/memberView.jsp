<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<style type="text/css">
	#imageArea{
		float: right;
		width: 200px;
		height: 300px;
		border: 1px solid black;  
		
	}
	#imageArea>img{
		width: 100%;
		height: 100%;
	}
</style>
<script type="text/javascript">
$( function() {
<c:if test="${not empty message }">
	alert("${request.message}");
</c:if>

    $( "[type='date']" ).datepicker({
    	  dateFormat: "yy-mm-dd"
    
    });
    $("#delBtn").on('click',function(){
    	var chk=confirm(" 정말로 탈퇴함? 리얼 투르? 실화냐 구라 즐.");
    	if(chk){
    		var pass=prompt("비밀번호 입력하시오...");
    		if(pass){
	//     		document.delForm.mem_pass.value=pass;
				$("[name='mem_pass']").val(pass);
				document.delForm.submit();
    		}
    		
    	}
    });
    var imgArea=$("#imageArea");
    $('[name="mem_image"]').on("change",function(){
    	var files =$(this).prop("files");
    	if(!files) return;
    	for(var idx=0;idx<files.length; idx++){
//     		console.log(files[idx]);
			var reader= new FileReader();
			reader.onloadend=function(event){
// 				console.log(event.target.result);
				var imgTag=new Image();
				imgTag.src=event.target.result;
				imgArea.html(imgTag);
			}
			reader.readAsDataURL(files[idx]);
    	}
    		
    })
  } );
	
</script>
		<c:set var="mutable" value="false"></c:set>
		<c:if test="${not empty sessionScope.authMember and 'ROLE_ADMIN' ne sessionScope.authMember.mem_auth }">
			<c:if test="${sessionScope.authMember.mem_id eq member.mem_id }">
				<c:set value="${true }" var="mutable"></c:set>
			</c:if>
		</c:if>
<%-- 		${sessionScope.authMember } --%>
<c:set var="mutable" value="true"></c:set>		
<%-- 		<c:if test="${mutable }"> --%>
<form name="delForm" action="${pageContext.request.contextPath}/member/memberDelete.do" method="post">
	<input type="hidden" name="mem_id" value="${member.mem_id}"/>
	<input type="hidden" name="mem_pass"  value=""/>
</form>
<%-- </c:if> --%>
<form:form action="${pageContext.request.contextPath }/member/memberUpdate.do" method="post"
	enctype="multipart/form-data" modelAttribute="member">
	<table>
		
		<tr>
			<th>회원아이디</th>
			<td>${member.mem_id } <input type="hidden" name="mem_id"
				value="${member.mem_id }"  /><form:errors path="mem_id" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="mem_pass"
				value="${member.mem_pass }" /><form:errors path="mem_pass" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><input type="text" name="mem_name" 
				value="${member.mem_name }" /><form:errors path="mem_name" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
			<div id="imageArea">
			<c:if test="${not empty member.mem_img}">
			<img src="data:image/*;base64,${member.mem_imgToBase64 }"/>
			</c:if>
			</div>
				<input type="file" name="mem_image" accept="image/*" > 
			</td>
			
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><input type="text" name="mem_regno1" disabled="disabled"
				value="${member.mem_regno1 }" /><form:errors path="mem_regno1" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><input type="text" name="mem_regno2" disabled="disabled"
				value="${member.mem_regno2 }" /><form:errors path="mem_regno2" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input type="text" name="mem_bir" disabled="disabled"
				value="${member.mem_bir }" /><form:errors path="mem_bir" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="mem_zip"
				value="${member.mem_zip }" /><form:errors path="mem_zip" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input type="text" name="mem_add1"
				value="${member.mem_add1 }" /><form:errors path="mem_add1" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input type="text" name="mem_add2"
				value="${member.mem_add2 }" /><form:errors path="mem_add2" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>집번</th>
			<td><input type="text" name="mem_hometel"
				value="${member.mem_hometel }" /><form:errors path="mem_hometel" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>회번</th>
			<td><input type="text" name="mem_comtel"
				value="${member.mem_comtel }" /><form:errors path="mem_comtel" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>폰번</th>
			<td><input type="text" name="mem_hp"
				value="${member.mem_hp }" /><form:errors path="mem_hp" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>이멜</th>
			<td><input type="text" name="mem_mail"
				value="${member.mem_mail }" /><form:errors path="mem_mail" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" name="mem_job"
				value="${member.mem_job }" /><form:errors path="mem_job" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input type="text" name="mem_like"
				value="${member.mem_like }" /><form:errors path="mem_like" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><input type="text" name="mem_memorial"
				value="${member.mem_memorial }" /><form:errors path="mem_memorial" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input type="text" name="mem_memorialday"
				value="${member.mem_memorialday }" /><form:errors path="mem_memorialday" element="span" cssClass="error" /></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.mem_mileage}</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${empty member.mem_delete?"활":"탈"}</td>
		</tr>
		<tr>
		<td colspan="2">
			<input type="button" value="뒤로가기" onclick="history.back();"/> 
			<c:if test="${mutable }">
	
			<input type="submit" value="수정" 
			/> 
			<input type="reset" value="리셋"/> 
			<input type="button" value="탈퇴하기" id="delBtn" />
			</c:if>
		</td>
		</tr>
	</table>
<%-- <c:if test="${mutable }"> --%>
</form:form>
<%-- </c:if> --%>
<h4>구매상품목록</h4>
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
		<c:set var="prodList" value="${member.prodList }"></c:set>
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


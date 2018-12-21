<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- <jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean> --%>
<%-- <jsp:useBean id="errors" class="java.util.HashMap" scope="request"></jsp:useBean> --%>
<%-- <jsp:useBean id="message" class="java.lang.String" scope="request"></jsp:useBean> --%>
<%
	 	String message= request.getParameter("message");

%>

<script type="text/javascript">
$( function() {
	<c:if test="${not empty message}">
	alert("${message}");
	
	</c:if>
	    $( "[type='date']" ).datepicker({
    	  dateFormat: "yy-mm-dd"
    
    });
  } );
	
</script>
	<form:form method="post" enctype="multipart/form-data" modelAttribute="member">
		<table>
			<tr>
				<th>회원아이디</th>
				<td><input type="text" name="mem_id" required="required"
					value="${member.mem_id } " />
					<form:errors path="mem_id" element="span" cssClass="error"/>
					</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="mem_pass"
					value="${ member.mem_pass}" />
					<form:errors path="mem_pass" element="span" cssClass="error"/>
					</td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="mem_name"
					value="${member.mem_name }" /><form:errors path="mem_name" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>회원이미지</th>
				<td><input type="file" name="mem_image" accept="image/*" /></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" name="mem_regno1"
					value="${ member.mem_regno1}" /><span class="error">${ errors["mem_regno1"]}</span></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" name="mem_regno2"
					value="${ member.mem_regno2}" /><form:errors path="mem_regno2" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="mem_bir" 
					value="${ member.mem_bir}" /><form:errors path="mem_bir" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="mem_zip"
					value="${ member.mem_zip}" /><form:errors path="mem_zip" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="mem_add1"
					value="${ member.mem_add1}" /><form:errors path="mem_add1" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="mem_add2"
					value="${ member.mem_add2}" /><form:errors path="mem_add2" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>집번</th>
				<td><input type="text" name="mem_hometel"
					value="${ member.mem_hometel}" /><form:errors path="mem_hometel" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>회번</th>
				<td><input type="text" name="mem_comtel"
					value="${ member.mem_comtel}" /><form:errors path="mem_comtel" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>폰번</th>
				<td><input type="text" name="mem_hp"
					value="${ member.mem_hp}" /><form:errors path="mem_hp" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>이멜</th>
				<td><input type="text" name="mem_mail"
					value="${ member.mem_mail}" /><form:errors path="mem_mail" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="mem_job"
					value="${ member.mem_job}" /><form:errors path="mem_job" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="mem_like"
					value="${ member.mem_like}" /><form:errors path="mem_like" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" name="mem_memorial"
					value="${ member.mem_memorial}" /><form:errors path="mem_memorial" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" name="mem_memorialday"
					value="${ member.mem_memorialday}" /><form:errors path="mem_memorialday" element="span" cssClass="error" /></td>
			</tr>
			
			<tr>
			<td colspan="2"> 
			<input type="submit" value="등록">
			<input type="reset" value="취소">
			 
			</td>
		</tr>

		</table>


	</form:form>

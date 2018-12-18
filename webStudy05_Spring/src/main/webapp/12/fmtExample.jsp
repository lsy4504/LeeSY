<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.ddit.or.kr/dateFn" prefix="dateFn" %>
<%@ taglib uri="http://www.ddit.or/kr/makeSelect" prefix="mSel" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/fmtExample.jsp</title>

<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function changeHandler(){
		document.localeForm.submit();
	}
</script>
</head>
<body>
<h4>Locale에 따른 포멧팅 처리..</h4>
<c:set var="clientLocale" value="${pageContext.request.locale }"></c:set>
<c:set value="${dateFn:getDefault() }" var="clientTiemZone"/>
<c:choose>
	<c:when test="${not empty param.locale }">
		<c:set var="pageLocale" value="${clientLocale.forLanguageTag(param.locale) }"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="pageLocale" value="${clientLocale }"></c:set>
		
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${not empty param.timeZone  }">
		<c:set value="${clientTiemZone.getTimeZone(param.timeZone) }" var="pageTimeZone"></c:set>
	</c:when>
	<c:otherwise>
		<c:set value="${clientTiemZone}" var="pageTimeZone"></c:set>
	</c:otherwise>
</c:choose>
<form action="" name="localeForm" >
	<select name="locale" onchange="submit()" >
	<option value="">로케일 선택</option>
	<c:forEach items="${dateFn:getAvailableLocales() }" var="locale">
	<c:if test="${not empty locale.displayName }">
	
	<option value="${locale.toLanguageTag() }" }>${locale.displayName }</option> 
	</c:if>
	</c:forEach>
	</select>
	<mSel:makeTimeZoneSelect name="timeZone" eventHandler="changeHandler()"/>
	<%-- <select name="timeZone" onchange="submit()">
		<option value="">타임존 선택.</option>
		<c:forEach items="${dateFn:getAvailableIDs() }" var="zoneId">
			<option value="${zoneId }">${clientTiemZone.getTimeZone(zoneId).displayName } </option>
		</c:forEach>
		
	</select> --%>
</form>
<script type="text/javascript">
	document.localeForm.locale.value="${pageLocale.toLanguageTag()}";
	document.localeForm.timeZone.value="${pageTimeZone.getID()}";
</script>
<c:if test="${not empty param.locale }">
<fmt:setLocale value="${param.locale }"/>
<c:set value="${pageContext.request.locale.forLanguageTag(param.locale) }" var="lo" />
<%-- <c:set value="${lo1.forLanguageTag(param.locale) }" var="lo" /> --%>
</c:if>	

<table>
	<thead>
		<tr>
			<th>언어</th>
			<th>지역</th>
			<th>해당 지역의 통화(1000원...????????????????)</th>
			<th>현재 시각을 해당 언어 방식으로 </th>
			<th>선택한 타임존의 현재 시각 </th>
		</tr>
	</thead>
	<tbody>
			<tr>
				<td>${pageLocale.displayName }</td>
				<td>${pageLocale.displayCountry }</td>
				<td><fmt:formatNumber value="1000" type="currency"/>  </td>
				<td><fmt:formatDate value="${dateFn:getNow() }" type="both" dateStyle="long" timeStyle="long"/>  </td>
				<td>
				${pageTimeZone.displayName }
				<br>
				<fmt:formatDate value="${dateFn:getNow() }" type="both" dateStyle="long" timeStyle="long" timeZone="${pageTimeZone }"/>
				</td>
			</tr>	
	</tbody>

</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.green {
	color: green;
}

.yellow {
	color: yellow;
}

.red {
	color: red;
	background-color: red;
}
.blue {
	color: blue;
	background-color: blue;
}

.silver {
	color: silver;
}

.aqua {
	color: aqua;
}

.orange {
	color: orange;
}
</style>
</head>
<body>
	<!-- 	2단부터 9던꺼자 규규던울 숭슈 1~9까지 table 태그로 출력 -->
	<!-- 	첫번째 단은 퍼렁색 -->
	<!-- 	네번째 단은 빨강색 -->
	<form action="">
		주소입력: <input type="url" name="siteUrl" value="" placeholder="https://www.naver.com"/>
		<label><input type="checkbox"  name="toSource" value="source" ${param.toSource eq 'source' ? "checked":"" }/>소스로보기</label>
		<input type="submit" value="gg"/>
	</form>
		<c:set value="${param.siteUrl }"  var="url"></c:set>
		<c:set value="${param.toSource }"  var="pcheck"></c:set>
	<c:if test="${not empty url }">
		<c:set value="false"  var="check"></c:set>
		<c:if test="${not empty pcheck }">
		<c:set value="true"  var="check"></c:set>
		</c:if>
	
	<c:import url="${url }" var="naver"></c:import>
	<div style="border:1px solid red; ">
		<c:out value="${naver }" escapeXml="${check }"></c:out>
	</div>
	</c:if>
	<form>
		최소단<input type="number" min="2" name="min" value="${param.min }"/> 
		최대단 <input type="number" min="2" max="9" name="max" value="${param.max }" /> <input type="submit" value="전송">
	</form>
	<c:if test="${not empty param.min and not empty param.max }">
		<table>

			<c:forEach var="x" begin="${param.min }" end="${param.max }" step="1" varStatus="lts">
			<c:if test="${lts.first }">
				<c:set var="colorClz" value="blue"></c:set>
			</c:if>
			<c:if test="${lts.count eq 4 }">
				<c:set var="colorClz" value="red"></c:set>
			
			</c:if>
				<tr class="${colorClz }">
				<c:forEach var="y" begin="1" end="9" varStatus="" step="1">
					<td>${x}* ${y}=${x * y}</td>
				</c:forEach>
				</tr>
				<c:remove var="colorClz"/>
			</c:forEach>
		</table>
	</c:if>

	<form action="">
		당신의 나이는 ? <input type="number" name="age" value="" /> <input
			type="submit" value="전송" />
	</form>
	<!-- 	age라는 파라미터가 있더라면 -->
	<!-- 	1. age 가 20대면 "방갑다 친구야"  -->
	<!-- 	1. age 가 30대면 "방가 행님~"  -->
	<!-- 	1. age 가 40대면 "담배 끊고 건강관리~" -->
	<!-- 	1. age 가 50대면 "아부지..." -->
	<!-- 	1. age 가 그 이상이면 "누구세엽?" -->
	<!-- 	1. age 가 20대 미만이면 "나가 놀아라--" -->
	<c:set var="age" value="${param.age }"></c:set>
	<c:if test="${not empty age }">
		<c:choose>
			<c:when test="${age ge 20 and age lt 30 }">
				<c:set var="msg" value="방갑다친구야" />
				<c:set var="color" value="green" />
			</c:when>
			<c:when test="${age ge 30 and age lt 40 }">
				<c:set var="msg" value="행님" />
				<c:set var="color" value="yellow" />
			</c:when>
			<c:when test="${age ge 40 and age lt 50 }">
				<c:set var="msg" value="건강관리" />
				<c:set var="color" value="red" />
			</c:when>
			<c:when test="${age ge 50 and age lt 60 }">
				<c:set var="msg" value="아빠?" />
				<c:set var="color" value="silver" />
			</c:when>
			<c:when test="${age ge 60 }">
				<c:set var="msg" value="누구세요" />
				<c:set var="color" value="aqua" />
			</c:when>
			<c:otherwise>
				<c:set var="msg" value="나가" />
				<c:set var="color" value="orange" />
			</c:otherwise>

		</c:choose>

		<div id="msgArea">
			<p class="${color }">${msg }</p>
		</div>
	</c:if>





</body>
</html>

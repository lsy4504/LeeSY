c<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/inner</title>
</head>
<body>
	<h4>하위 경로에서 쿠키 확인~</h4>
	<table>
		<thead>
			<tr>
				<th>쿠키명</th>
				<th>쿠키값</th>

			</tr>
		</thead>
		<tbody>
			<%
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						out.println(String.format("<tr><td>%s</td><td>%s</td></tr>", cookie.getName(),URLDecoder.decode(cookie.getValue())));
					}
				}
			%>

		</tbody>
	</table>
</body>
</html>
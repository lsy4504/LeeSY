<%@page import="java.util.Map.Entry"%>
<%@page import="kr.or.ddit.vo.AlbasengVO"%>
<%@page import="kr.or.ddit.web.SimpleFormProcessServlet"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
모든알바생들의 대한 데이터 출력
<table>
	<thead>
		<tr>
			<th> 알바코드</th>
			<th> 이름</th>
			<th> 주소</th>
			<th> 연락처</th>
		</tr>
	</thead>
	<tbody>
		<%	
		
			//누군가 넣은 코드,
			Map<String,AlbasengVO> list= (Map<String , AlbasengVO>)application.getAttribute("albasengs");
			String pattern ="<td>%s</td>";
			for(Entry<String,AlbasengVO> entry: list.entrySet()){
			out.print("<tr>");
			out.print(String.format(pattern,entry.getValue().getCode()));				
			out.print(String.format(pattern,entry.getValue().getName()));				
			out.print(String.format(pattern,entry.getValue().getAddr()));				
			out.print(String.format(pattern,entry.getValue().getTel()));				
			out.print("</tr>");
			}
		%>
	</tbody>
</table>
</body>
</html>
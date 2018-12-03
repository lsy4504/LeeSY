<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<%
String minStr=request.getParameter("minDan");
String maxStr=request.getParameter("maxDan");
if(minStr==null||!minStr.matches("\\d") || maxStr==null||!maxStr.matches("\\d")){
	response.sendError(400, "니 잘못");
	return;
}


%>
<form action="<%=request.getContextPath()%>/02/numberTest.jsp">

	최소단:<input type="number" name="minDan" value="<%=minStr%>"/>
	최대단:<input type="number" name="maxDan" value="<%=maxStr%>"/>
	<input type="submit" value="전송"/>
</form>
<table class="table">
	<%
	int min=Integer.valueOf(minStr);
	int max=Integer.valueOf(maxStr);
		
		String pattern= "%d * %d = %d";
		for(int i=min; i<=max; i++){
			%><tr  class='success'>
			<%for(int j=1; j<=9; j++){
				%><td><%=String.format(pattern,i,j,i*j)%></td>
			<% 	
			}
			%>
			</tr>
			<% 
		}
	%>
</table>
</div>
<ul>
	<%
		for(int number=1; number<=50; number++){
	%>
	<li><%=number%></li>
	<%
		}
	%>	
</ul>

</body>
</html>
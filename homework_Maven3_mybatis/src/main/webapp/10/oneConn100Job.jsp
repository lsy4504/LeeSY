<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.or.ddit.db.ConnetionFactory"%>
<%@page import="org.apache.commons.dbcp.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	long statTime= System.currentTimeMillis();
	try(
		Connection conn=ConnetionFactory.getConnetion();
		Statement stmt= conn.createStatement();
	){
		String sql= "select mem_name from member where mem_id='a001'";
		
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()){
			for(int i=1; i<=100; i++){
			out.println(rs.getString(1));
			}
		}
		
	}
	long endTime=System.currentTimeMillis();
	

%>
소요시간 -> <%= (endTime-statTime) %>초

</body>
</html>
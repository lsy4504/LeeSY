<%@page import="kr.or.ddit.db.ConnetionFactory"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/jdbcDESC.jsp</title>

</head>
<body>
<h4>JDBC(Java Database Connectivity) </h4>
<pre>
	데이터베이스 연결 프로그래밍 단계
	1. 드라이버를 빌드패스에 추가 
	2. 드라이버  클래스 로딩
	3. 연결 객체(Connection) 생성 
	4. 쿼리 객체 생성
		Statment - 동적
		PreparedStatement - 정적
		CallableStatement - 함수, 프로시쳐 (절차적)
	5. 쿼리 실행(CRUD)
		ResultSet executeQuery : Select
		int executeUpdate : insert/update/delete (int 영향을 받은 컬럼의 수)
	6. 결과 집합 사용
	7. 자원의 해제 : finally 블럭/try~with-resource 구문	
	
	<% 
		
		
			String[] headers=null;
			List<DataBasePropertyVO> kr=null;
		try(
		Connection conn= ConnetionFactory.getConnetion();
		Statement stmt= conn.createStatement();
				){
		out.print(conn.getClass());
		StringBuffer sql= new StringBuffer();
		sql.append("SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION	");
		sql.append(" from database_properties ");
		ResultSet rs=stmt.executeQuery(sql.toString());
		ResultSetMetaData rsmd=rs.getMetaData();
		headers=new String[rsmd.getColumnCount()];
		for(int idx=1; idx<=rsmd.getColumnCount(); idx++){
			headers[idx-1]=rsmd.getColumnName(idx);
		}
		kr= new ArrayList<>();
		while(rs.next()){
			DataBasePropertyVO vo = new DataBasePropertyVO();
			vo.setProperty_name(rs.getString("PROPERTY_NAME"));
			vo.setProperty_value(rs.getString("PROPERTY_VALUE"));
			vo.setDescription(rs.getString("DESCRIPTION"));
			kr.add(vo);
		}//while end
		}//try block end
		
		
	%>	

</pre>
<table>
	<thead>
		<tr>
			
				<% 
					for(String head: headers){
						%>
							<th><%=head  %></th>
						<%
						
					}
				%>
			
		</tr>
	</thead>
	<tbody>
			<% 
				String pattern= "<td>%s</td><td>%s</td><td>%s</td>";
					for(DataBasePropertyVO vo:kr){
					out.print("<tr>");
					out.print(String.format(pattern, vo.getProperty_name(), vo.getProperty_value(),vo.getDescription()));
					out.print("</tr>");
					}
			%>
	</tbody>
</table>
</body>
</html>
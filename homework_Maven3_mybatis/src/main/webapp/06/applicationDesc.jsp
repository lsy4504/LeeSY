<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/applicationDesc.jsp</title>

</head>
<body>
<h4>Servletcontext</h4>
<pre>
	<%=application.hashCode() %>
	<a href="<%=request.getContextPath() %>/06/implicitObject.jsp">implicaitObject.jsp</a>
	<a href="<%=request.getContextPath() %>/desc">DescriptionSrervlet.java</a>
	: 컨텍스트와 해당 컨텍스트를 운영(관리) 중인 서버에 대한 정보를 가진 객체.
	
	1. 서버에대한 정보를 획득
		<%=application.getServerInfo()%>
		<%=application.getMajorVersion()%>.<%=application.getMinorVersion()%>
		<%=application.getMimeType("test.hwp") %>
	2. 로그 기록(logging)
		<%
			application.log("명시적으로 로그 기록");
		
		%>
	3. 컨텍스트 파라미터(어플리케이션의 초기화 파라미터) 획득
		<%= application.getInitParameter("param1") %>
		<%Enumeration<String> enumeration =application.getInitParameterNames(); 
			while(enumeration.hasMoreElements()){
				out.println(enumeration.nextElement());
			}
		%>
	4. 웹리소르를 획득: http://localhost/webstudy01/images/r.JPG
		<%=application.getRealPath("/images/r.JPG") %>
		<% 
			String fileSystemPath=application.getRealPath("/images/r.JPG");
			File srcFile= new File(fileSystemPath);
			String fileSystemPath2=application.getRealPath("/06/r.JPG");//파일은 없지만 이에 해당하는 경로를 가져올수있음
// 			out.println(srcFile.getAbsolutePath());
// 			FileOutputStream outputStream = new OutputStream();
			
			File targetFile= new File(fileSystemPath2);
			try(
// 			FileInputStream fileInputStream=new FileInputStream(srcFile);
								
			BufferedInputStream bufferedInputStream= new BufferedInputStream(application.getResourceAsStream(fileSystemPath));
			FileOutputStream outputStream =new FileOutputStream(fileSystemPath2);
			BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(outputStream);
					
					){
			int p=-1;
				byte[] buffer= new byte[1024];
				while((p=bufferedInputStream.read(buffer))!=-1){
					bufferedOutputStream.write(buffer,0,p);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			System.out.println(targetFile.exists());				
			
					
					
			
			
			
		%>
	</pre>
	<img alt="" src="<%=request.getContextPath() %>/06/r.JPG"/>
	<img alt="" src="<%=request.getContextPath() %>/images/r.JPG"/>
</body>
</html>
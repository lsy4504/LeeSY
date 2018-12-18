<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public Map<String, String[]> singerMap=new LinkedHashMap<>();
{
	singerMap.put("T001", new String[]{"다현","/WEB-INF/twice/dahyun.jsp"});
	singerMap.put("T002", new String[]{"모모","/WEB-INF/twice/momo.jsp"});
	singerMap.put("T003", new String[]{"사나","/WEB-INF/twice/sana.jsp"});
	singerMap.put("T004", new String[]{"정연","/WEB-INF/twice/jung.jsp"});
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function change() {
		var form=document.forms[0];
		form.submit();
	}
</script>
</head>
<body>
<form action="<%=request.getContextPath() %>/05/getTwice.jsp" method="post">
<select name="member" onchange="change();">
<option  value="">멤버 선택 </option>
	<%
		
		String pattern="<option value=%s>%s</option>";
		
		for(Entry<String,String[]> entry:singerMap.entrySet()){
			
			out.print(String.format(pattern, entry.getKey(),entry.getValue()[0] ));
		}
	%>
</select>
<!-- <script type="text/javascript"> -->
<!-- // 	change(); -->
<!-- </script> -->
</form>
</body>
</html>
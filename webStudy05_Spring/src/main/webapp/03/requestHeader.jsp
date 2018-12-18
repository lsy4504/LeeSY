<%@page import="java.util.Locale"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/requestHeader.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function change() {
		document.aa.submit();
	}

</script> 
</head>
<body>
		<%
			Locale[] locales= Locale.getAvailableLocales();
		%>
	<form action="" method="post" name="aa" >
	<select name="lang" onchange="change()">
		<option value="" >언어</option>
		<%
			String optPattern= "<option value=%s %s>%s</option>";
			for(Locale lo: locales)
			{	
				
// 				lo.toLanguageTag();// 코드?
// 				lo.getDisplayLanguage();//한국어 
					String sel= "";
// 				if(locale.equals(lo)){
// 					sel="selected";
// 				}
				if(lo.getDisplayLanguage()!=null && lo.getDisplayLanguage().trim().length()!=0)
				out.println(String.format(optPattern, lo.toLanguageTag(),sel ,lo.getDisplayLanguage())); 
			}
		%>
	</select>
	<input type="submit" value="전송"/> 
	</form>
<%
if(request.getParameter("lang")==null || request.getParameter("lang").trim().length()==0){
	return;
}
	String lang= request.getParameter("lang");
//		ex)ko_KR: 로케일 문자 언어_국가(지역)
	Locale locale= Locale.forLanguageTag(lang);
%>
<ul>
	<li>
		현재 사용자의 언어와 국가 정보
		:<%=request.getHeader("accept-language") %>
	</li>
	<li>
		<%
	
// 			ex)ko_KR: 로케일 문자 언어_국가(지역)

			String country= locale.getDisplayCountry(locale);
			String language= locale.getDisplayLanguage(locale);
		
		%>
		Locale 객체 활용 
		:<%=country %>,<%=language %>
	</li>

</ul>
	<table>
		<thead>
			<tr>
				<th>헤더명</th>
				<td>헤더값</td>
			</tr>
		</thead>
		<tbody>
			<%
				Enumeration<String> names=  request.getHeaderNames();
				String patten="<tr><td>%s</td><td>%s</td></tr>";
				while(names.hasMoreElements()){
					String headerName =names.nextElement();
					String headerValue=request.getHeader(headerName);
					out.println(String.format(patten, headerName, headerValue));
				}
			%>
		
		</tbody>

	</table>

</body>
</html>
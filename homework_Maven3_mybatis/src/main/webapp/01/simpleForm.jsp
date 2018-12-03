<%@page import="java.util.LinkedHashMap"%>
<%@page import="kr.or.ddit.vo.AlbasengVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Objects"%>
<%@page import="java.util.Map.Entry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%--
// 	Map<String,String> gradeMap=(Map<String,String>)application.getAttribute("gradeMap");
// 	Map<String,String> licenseMap=(Map<String,String>)application.getAttribute("licenseMap");
// 	AlbasengVO albaVO=(AlbasengVO)request.getAttribute("albaVO");
// 	Map<String, String> erros=(Map<String,String>)request.getAttribute("errors");
// 	if(albaVO==null) albaVO=new AlbasengVO();
// 	if(erros==null) erros=new LinkedHashMap<String,String>();
--%>
<jsp:useBean id="licenseMap" class="java.util.LinkedHashMap" scope="application"/>
<jsp:useBean id="gradeMap" class="java.util.HashMap" scope="application"/>
<jsp:useBean id="albaVO" class="kr.or.ddit.vo.AlbasengVO" scope="request"/>
<jsp:useBean id="erros" class="java.util.LinkedHashMap" scope="request"/>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
</script>
<title>Insert title here</title>
<style type="text/css">
	.error{
		color: red;
		
	}
</style>
</head>
<body>
<!-- 알바몬에서 알바생의 프로필을 입력받으려고 함 . -->
<!-- 이름, 나이, 주소, 전번, 성별, 경력, 학력, 자소서, 자격증 -->
<form action="<%=request.getContextPath() %>/albamon" method="post">
<table>
	<tr>
		<th>이름</th>
		<td>
			<input type="text" name="name" value="<%=Objects.toString(albaVO.getName(),"") %>"/>
		<span class='error'>
			<%=Objects.toString(erros.get("name"),"")%>
		</span>
		</td>
	</tr>
	<tr>
		<th>나이</th>
		<td>
			<input type="number" name="age" min="15" max="40" value="<%=Objects.toString(albaVO.getAge(),"") %>"/>
		</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>
			<input type="text" name="tel" placeholder="000-0000-0000" pattern="[0-9]{3}-[0-9]{3,4}-[0-9]{4}" value="<%=Objects.toString(albaVO.getTel(),"") %>"/>
			<span class='error'>
			<%=Objects.toString(erros.get("tel"),"")%>
		</span>
		</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>
			<input type="text" name="addr" value="<%=Objects.toString(albaVO.getAddr(),"") %>"/>
			<span class='error'>
			<%=Objects.toString(erros.get("addr"),"")%>
		</span>
		</td>
	</tr>
	<tr>
		<th>성별</th>
		<td>
			<label><input type="radio" name="gender" value="M" <%="M".equals(albaVO.getGender())?"check":"" %>/>남</label>
			<label><input type="radio" name="gender" value="F" <%="F".equals(albaVO.getGender())?"check":"" %>/>여</label>
		</td>
	</tr>
	<tr>
		<th>학력</th>
		<td>
			<select name="grade">
				<option value="">학력</option>
				<% 
					
					String partten="<option value=%s %s>%s</option>";
					for(Object obj : gradeMap.entrySet()){
						Entry e= (Entry)obj;
						String key= e.getKey().toString();	
						String sel="";
						if(key.equals(albaVO.getGrade())){
							sel="selected";
						}
						out.print(String.format(partten,e.getKey(),sel ,e.getValue()) );
						 
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<th>경력</th>
		<td>
			<textarea rows="3" cols="100" name="career"><%=Objects.toString(albaVO.getCareer(),"") %></textarea>
		</td>
	</tr>
	<tr>
		<th>자격증</th>
		<td>
			<select name="license" multiple="multiple" size="10">
			<%
			
				String partten2="<option value=%s %s>%s</option>";
				String l[] =albaVO.getLicense();
				
				if(l!=null){
					Arrays.sort(l);
					System.out.println(Arrays.toString(l));
				}
				for(Object obj : licenseMap.entrySet()){
					Entry e= (Entry)obj;
					String sel="";
					if(l!=null&&Arrays.binarySearch(l, e.getKey())>-1){
						sel="selected";
					}
						
					out.print(String.format(partten2, e.getKey(), sel,e.getValue()));
					//e.getKey 키값 가져오기 , e.getValue() 벨류값 가져오기 향상된포문
				}
			%>
			</select>
		</td>
	</tr>
	<tr >
	<td colspan="2">
	<input type="submit" value="등록"/>
	<input type="reset" value="취소"/>
	<input type="button" value="버튼"/>
	</td>
	</tr>
</table>

</form>

</body>
</html>
<%@page import="java.util.Objects"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.web.calculate.MimeType"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public String mashalling(Map<String,Object> originalData ){
		StringBuffer result=new StringBuffer();
		result.append("{");
		String jsonPattern="\"%s\":\"%s\",";
		for(Entry<String,Object> entry :originalData.entrySet()){
			String propName=entry.getKey();
			String propValue=Objects.toString(entry.getValue(), "");
			result.append(String.format(jsonPattern, propName, propValue));
		}
		int lastIndex=result.lastIndexOf(",");
		result.deleteCharAt(lastIndex);
		result.append("}");
		return result.toString();	
	}
%>
<% 
// 매 1초마다 현재 서버의 시각을 JSON형태로 전송.
// 응답으로 전송될 JSON객체 내에는
// 현재 서버의 시각을 의미하는 serverTime이라는 프로퍼티가 있음. --> -->
	response.setContentType(String.valueOf(MimeType.JSON));
	response.setHeader("Refresh", "1");
	
	Date serverTime= new Date();
	Map<String, Object> javaObject= new LinkedHashMap<>();
	javaObject.put("serverTime",serverTime);
	String json=mashalling(javaObject);
	out.print(json);
%> 

 
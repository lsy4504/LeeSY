<%@page import="java.util.Map.Entry"%>
<%@page import="com.sun.org.apache.regexp.internal.recompile"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%!public Map<String, String[]> singerMap = new LinkedHashMap<>();
	{
		singerMap.put("T001", new String[] { "다현", "/WEB-INF/twice/dahyun.jsp" });
		singerMap.put("T002", new String[] { "모모", "/WEB-INF/twice/momo.jsp" });
		singerMap.put("T003", new String[] { "사나", "/WEB-INF/twice/sana.jsp" });
		singerMap.put("T004", new String[] { "정연", "/WEB-INF/twice/jung.jsp" });
	}%>
<% 
// 1.파라미터확보
// 2.검증(필수데이터,유효데이터 검증)
// 3.불통
// 	1) 필수 데이터 누락  400
// 	2) 우리가 관리하지 않는 멤버 요구한 경우 404
// 4.통과
// 	1)요구하고있는 멤버의 개인페이지로 이동(맵에 있는 개인페이지, 클라이언트가 멤버 개인패이지의 주소를 모르도록)
// 	1)요구하고있는 멤버의 개인페이지로 이동(맵에 있는 개인페이지, getBTS에서 원본 요청을 모두 처리했을 경우 UI 페이지로 이동할때)

	String member= request.getParameter("member");
	String memAddr= null;
	RequestDispatcher rd= null;
	boolean check= false;
	if(member==null || member.trim().length()==0){
		response.sendError(400);
	}else{
	for(Entry<String,String[]> entry : singerMap.entrySet()){
		if(member.equals(entry.getKey())) {
			memAddr=singerMap.get(member)[1];
			check=true;		
		}
	}
	if(check){
			rd=request.getRequestDispatcher(memAddr);
			rd.forward(request, response);
// 		response.sendRedirect(request.getContextPath()+ memAddr);
	}else{
		response.sendError(404);
	}
	}
	
%>
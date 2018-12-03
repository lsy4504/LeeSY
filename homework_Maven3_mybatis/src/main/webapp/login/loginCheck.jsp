<%@page import="kr.or.ddit.ServiceResult"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.member.service.AuthenticateServiceImpl"%>
<%@page import="sun.net.www.protocol.http.AuthCacheImpl"%>
<%@page import="kr.or.ddit.member.service.IAuthenticateService"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.or.ddit.db.ConnetionFactory"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.or.ddit.utils.CookieUtil.TextType"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.utils.CookieUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%!
	
	%>
<%
request.setCharacterEncoding("UTF-8");
/* 1.파라미터 확인
2.검증  
3.불통
	이동(loginForm.jsp, 기존에 입력했던 아이디를 그대로 전달 할수 있는 방식.)
4.통과
	1)인증 (id==pass) true
		1)-1 인증 성공 : 웰컴 페이지로 이동(원본 요청을 제거하고 이동)
		1)-2 인증 실패 : 이동(loginForm.jsp,기존에 입력했던 아이디를 그대로 전달 할수 있는 방식. ) */
	String mem_id=request.getParameter("mem_id");
	String mem_pass=request.getParameter("mem_pass");
	String idChecked=request.getParameter("idChecked");
	
	RequestDispatcher rd= null;
	String goPage= null;
	boolean redirect=false;
// 	if(mem_id==null || mem_id.trim().length()==0){
// 		response.sendRedirect(request.getContextPath()+"/login/loginForm.jsp");
// 	}else if(mem_pass==null|| mem_pass.trim().length()==0){
		
// 		rd=request.getRequestDispatcher("/login/loginForm.jsp");
// 		rd.forward(request, response);
// 	}else if(mem_id.equals(mem_pass)){
// 		response.sendRedirect(request.getContextPath()+"/index.jsp");
// 	}else{
// 		rd=request.getRequestDispatcher("/login/loginForm.jsp");
// 		rd.forward(request, response);
		
// 	}
	if(mem_id==null || mem_id.trim().length()==0 || mem_pass==null||mem_pass.trim().length()==0){
		goPage="/?commend=login";
		redirect =true;
		session.setAttribute("message", "아이디나 비밀번호가 누락됨 ㅎ");
	}else{
		IAuthenticateService service = new AuthenticateServiceImpl();
		Object result=service.authenticate(new MemberVO(mem_id,mem_pass));
		if(result instanceof MemberVO){
			goPage="/";
			redirect=true;
			session.setAttribute("authMember", result);
			Cookie cookie=null;
			int maxAge=0;
			if(StringUtils.isNotBlank(idChecked)){
				maxAge=60*60*24*7;
			}
				cookie = CookieUtil.createCookie("id",mem_id,request.getContextPath(),TextType.PATH,maxAge);
			
				response.addCookie(cookie);
		}else if(result == ServiceResult.PKNOTFOUND){
		goPage="/?commend=login";
		redirect =true;
		session.setAttribute("message", "노존재!");
		}else{
		goPage="/?commend=login";
		redirect =true;
		session.setAttribute("message", "비번 다름");
			
		}
	}
	if(redirect){
		response.sendRedirect(request.getContextPath()+goPage);		
	}else{
		rd=request.getRequestDispatcher(goPage);
		rd.forward(request, response);
	}
	
%>
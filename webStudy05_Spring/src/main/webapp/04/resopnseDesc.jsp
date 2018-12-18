<%@page import="java.util.Calendar"%>
<%@page import="kr.or.ddit.web.calculate.MimeType"%>
<%@ page language="java" 
    pageEncoding="UTF-8"%>
<%
// 	response.setHeader("Content-type", "text/html;charset=UTF-8");
	response.setContentType(String.valueOf(MimeType.HTML));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/responseDesc.jsp</title>
</head>
<body>
<h4>Http Response</h4>
<pre>
	Http의 response 패키징 방식
	1) Response Line : Response Status Code, Protocol/ver
		Response Status Code(응삽 상태 코드)
		-100 : ing...,HTTP 1.1 부터 하위 프로토콜로 websocket이 사용됨(connectfull).
			**HTTP: Connectless, Stateless
		-200 : OK, SUCCESS(정상처리)
		-300 : 클라이언트의 추가 액션이 필요한 경우.
			304(Not Modified)
			301/302/307(Moved)
		-400 :Clinet 쪽 문제로 처리 실패
			404(Not Found), 400(Bad Request), 
			405(Method Not Allowed)
			401(UnAuthorized), 403(Forbidden)
		-500 :Server 쪽 문제로 처리 실패
			500(Internal Server Error)
		
	2) Response Header : 서버나 응답데이터에 대한 부가정보(metadata)
		resopnse header name : header value
		
	3) Response Body(Message Body) : 응답 컨텐츠
		**HttpServletResponse를 통한 응답 제어
		:서버에서 클라이언트로 전송되는 응답에 대한 모든 데이터를 가진 객체.
		1) 응답데이터를 전송하기 위한 출력 스트림 확보
			char stream(PrintWriter) getWriter() 
			byte stream(ServletOutputStream) getoutputStream()
		2) setStatus(status_code): 200/300 사용
		   sendError(status_code): 400/500 사용 서버의 에러페이지로 자동 연결.
		3) set[add]Header(header_name, header_value)	
		   set[add]IntHeader(header_name, header_value)	
		   set[add]DateHeader(header_name, long header_value)
		   
		   응답 헤더를 설정하는 경우.
		   a) MIME 설정 : Content-Type
		   		setHeader, setContentType, page 지시자의 contentType 속성.
		   
		   b) Cache 제어 : Cashe-Control(HTTP/1.1), Pragma(HTTP/1.0), Expires(만기시간)
		   	<%
		   	//캐시를 저장하지 않도록 설정하는 경우
// 		   		response.setHeader("Pragma", "no-cashe");
// 		   		response.setHeader("Cashe-control", "no-cashe");
// 		   		response.addHeader("Cashe-control", "no-store");//파이어폭스의 경우 헤더 해석방법이 다름
// 		   		response.setDateHeader("Expires", 0);//long 밀리
		   											//int 세컨드
			//캐시를 저장하도록 설정
				int maxAge=60*60*24*7;
				response.setHeader("Pragma", "public;max-age="+maxAge);
				response.setHeader("Cashe-control", "public;max-age="+maxAge);
				Calendar today=Calendar.getInstance();
				today.add(Calendar.SECOND, maxAge);
				response.setDateHeader("Expires", today.getTimeInMillis());
				
		   	%>	
		   c) Auto Request : Refresh
		   d) Page Move(Flow Control) : Location
			
</pre>
</body>
</html>
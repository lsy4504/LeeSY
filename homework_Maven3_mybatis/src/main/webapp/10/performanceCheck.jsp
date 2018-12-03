<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/performanceCheck.jsp</title>
</head>
<body>
<h4> 어플리케이션 처리 속도</h4>
	
<pre>
	소요시간(response time) = latency time(지연) + processing time(처리)
	한번 가져와서(latency time) 한번 처리( processing time)하는 모듈:30ms
	100번 가져와서 100번 처리하는 모듈:1300ms
	한번 가져와서 100번 처리하는 모듈:30

</pre>

</body>
</html>
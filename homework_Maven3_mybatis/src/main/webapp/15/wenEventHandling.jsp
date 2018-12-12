<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>15/webEventHandling</title>
</head>
<body>
<h4>서버사이드 웹 어플리케이션의 이벤트 처리</h4>
<pre>
	**이벤트 처리 단계
	1. 이벤트 타겟 결정(Button) : 웹 어플리케이션(context)
	2. 이벤트 종류 결정(click)
		1) request : 생성 / 소멸 (lifecycle event), requestScope(add, remove, replace) 
		2) session : 생성 / 소멸 (lifecycle event), sessionScope(add, remove, replace)
		3) application(ServletContext) : 생성 / 소멸 (lifecycle event), applicationScope(add, remove, replace)
		
	3. 이벤트 처리 핸들러 작성(function, method, listener)
		1)request
			lifeCycle : ServletRequestListner
			scope: ServletRequestAttributeListner
		2)session
			lifeCycle : HttpSessionListener
			scope: HttpSessionAttributeListener
			object binding: HttpSessionBindingListner
		3) application
			lifeCycle : ServletContextListener
			scope: ServletContextAttributeListner
			
	4. 이벤트 타겟에게 핸들러를 부착 부착 부착 착불(onclick, on, addListener)
	
	
	
	
	
	
	
</pre>
</body>
</html>
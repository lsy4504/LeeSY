<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<TITLE><tiles:getAsString name="title"/></TITLE>
<tiles:insertAttribute name="preScript"/>
 
</head>
<body>
	<div id='top' class="top">
		<tiles:insertAttribute name="top"/>
	</div>
	<div id='left' class="left">
		<tiles:insertAttribute name="left"/>
	</div>
	<div id='body' class="body">
		<tiles:insertAttribute name="body"/>
	</div>
	<div id='footer' class='footer'>
		<tiles:insertAttribute name="footer"/>
	</div>

</body>
</html>
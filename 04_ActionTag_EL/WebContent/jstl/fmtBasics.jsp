<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fmt basics</title>
</head>
<body>
	<h1>fmt basics</h1>
	<h2>숫자</h2>
	<c:set var="num1" value="1234567890"/>
	<p><fmt:formatNumber value="${num1}" pattern="#,###"/></p>
	<p><fmt:formatNumber value="${num1}" type="currency"/></p>
	
	<c:set var="num2" value="123.456" />
	<p><fmt:formatNumber value="${num2}" pattern="#.####"/></p>
	<p><fmt:formatNumber value="${num2}" pattern="0.0000"/></p>
	
	<c:set var="num3" value="0.02"/>
	<p><fmt:formatNumber value="${num3}" type="percent"/> </p>
	
	<h2>날짜</h2>
	<c:set var="now" value="<%= new Date() %>"/>
	<p><fmt:formatDate value="${now}" type="date"/></p>
	<p><fmt:formatDate value="${now}" type="time"/></p>
	<p><fmt:formatDate value="${now}" type="both"/></p>
	<p><fmt:formatDate value="${now}" pattern="yy/MM/dd(E)"/></p>
	<p><fmt:formatDate value="${now}" pattern="HH:mm:ss"/></p>
	
	
	
</body>
</html>
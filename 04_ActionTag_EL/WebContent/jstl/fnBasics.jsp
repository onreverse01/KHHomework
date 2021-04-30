<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fn basics</title>
</head>
<body>
	<h1>fn basics</h1>
	<c:set var="str1" value="Hello world, Hello JSTL"/>
	<c:set var="str2" value="Hello EL"/>
	
	<p>${fn:toUpperCase(str1)}</p>
	<p>${fn:toLowerCase(str2)}</p>
	<p>${fn:length(str1)} ${str1.length()}</p> <%-- java 메소드를 그냥 호출가능 --%>
	<p>${fn:contains(str1, "world")}</p>
	<c:if test="${fn:contains(str1, 'world')}">
		world 있다.
	</c:if>
	<c:if test="${not fn:contains(str1, 'world')}">
		world 없다.
	</c:if>
	
	<p>${fn:indexOf(str1, 'world')}</p>
	<p>${fn:indexOf(str1, 'water')}</p>
	<c:if test="${fn:indexOf(str1, 'world') > -1}">
		world 있다.
	</c:if>
	<c:if test="${not (fn:indexOf(str1, 'world') == -1)}"> <%-- not fn:indexOf(str1, 'world') 이부분이 먼저 연산이 된다.--%>
		world 없다.
	</c:if>
	
	<p>${fn:replace(str1, 'Hello', 'Goodbye')}</p>
	
	<c:set var="badScript"><script>document.body.style = "red";</script></c:set>
	${fn:escapeXml(badScript)} <%-- XSS방지용 --%>
	
</body>
</html>
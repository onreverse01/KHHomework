<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/standard/header.jsp">
	<jsp:param value="INCLUDE" name="title"/>
</jsp:include>
	<article>
		<h2>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Qui nostrum atque dolor consectetur aperiam sunt alias fugit deleniti ullam quam maiores tempore voluptatibus molestias ex magni quidem officiis beatae reprehenderit!</h2>
		<a href="${pageContext.request.contextPath}/standard/another.jsp">another</a>
	</article>
<jsp:include page="/standard/footer.jsp"></jsp:include>
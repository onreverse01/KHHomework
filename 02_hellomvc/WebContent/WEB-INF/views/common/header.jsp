<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="member.model.vo.Member"%>
<%@page import="member.model.service.MemberService"%>

<%
	String msg = (String)session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg"); //이것까지 해줘야 1회용이 완성
	String loc = (String)request.getAttribute("loc");
	System.out.println("msg@header.jsp = " + msg);
	Member loginMember = (Member)session.getAttribute("loginMember");
	
	//사용자 쿠키처리
	String saveId = null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie c : cookies){
			String name = c.getName();
			String value = c.getValue();
			//System.out.println(name + " : " + value);
			if("saveId".equals(name))
				saveId = value;
			
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<link rel="icon" type="image/png" sizes="16x16" href="/favicon.png">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<script>
<% if(msg != null) { %>
alert("<%= msg %>");
<% } %>

<% if(loc != null) {%>
	location.href = "<%= loc %>";
<% } %>

$(function(){
	/**
	* 로그인 폼 유효성 검사
	*/
	$("#loginFrm").submit(function(){
		var $memberId = $(memberId);
		var $password = $(password);
		
		if(/^.{4,}$/.test($memberId.val()) == false){
			alert("유효한 아이디를 입력하세요.");
			$memberId.select();
			return false;
		}
		if(/^.{4,}$/.test($password.val()) == false){
			alert("유효한 비밀번호를 입력하세요.");
			$memberId.select();
			return false;
		}
	});
});
</script>
</head>
<body>
	<div id="container">
		<header>
			<h1>Hello MVC</h1>

			<div class="login-container">
			<% if(loginMember == null) {%>
				<!-- 로그인폼 시작 -->
				<form id="loginFrm" action="<%= request.getContextPath() %>/member/login" method="POST">
					<table>
						<tr>
							<td><input type="text" name="memberId" id="memberId"
								placeholder="아이디" tabindex="1" value="<%= saveId != null ? saveId : "" %>"></td>
							<td><input type="submit" value="로그인" tabindex="3"></td>
						</tr>
						<tr>
							<td><input type="password" name="password" id="password"
								placeholder="비밀번호" tabindex="2"></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2"><input type="checkbox" name="saveId"
								id="saveId" <%= saveId != null ? "checked" : "" %>/> <label for="saveId">아이디저장</label>&nbsp;&nbsp; <input
								type="button" value="회원가입" onclick="location.href='<%= request.getContextPath() %>/member/memberEnroll'";></td>
						</tr>
					</table>
				</form>
				<!-- 로그인폼 끝-->
			<% } else { %>
				<%-- 로그인 성공시 --%>
				<table id="login">
					<tr>
						<td><%=loginMember.getMemberName() %>님, 안녕하세요.</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="내정보보기" onclick="location.href='<%= request.getContextPath() %>/member/memberView';"/>
							<input type="button" value="로그아웃" onclick="location.href='<%= request.getContextPath() %>/member/logout';" />
						</td>
					</tr>
				</table>
			
			
			<% } %>
			</div>
			<!-- 메인메뉴 시작 -->
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="<%=request.getContextPath()%>">Home</a></li>
					<li class="notice"><a href="#">공지사항</a></li>
					<li class="board"><a href="<%=request.getContextPath()%>/board/boardList">게시판</a></li>
					<%-- 관리자로그인시에만 관리자메뉴 노출 --%>
					<% if(loginMember != null && MemberService.ADMIN_ROLE.equals(loginMember.getMemberRole())){ %>
					<li class="members"><a href="<%= request.getContextPath() %>/admin/memberList">회원관리</a></li>
					<% } %>
				</ul>
			</nav>
			<!-- 메인메뉴 끝-->
		</header>

		<section id="content">
package com.kh.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestPerson2Servlet extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException ,IOException{
		//0. encoding 선언
		request.setCharacterEncoding("utf-8");
		
		//1. 사용자입력값 변수에 담기
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		String[] foodArr = request.getParameterValues("food");
		
		System.out.println("name = "+ name);
		System.out.println("color = "+ color);
		System.out.println("animal = "+ animal);
		System.out.println("foodArr = "+ Arrays.toString(foodArr));
		
		//2. 업무로직
		String recommendation = "";
		switch(color) {
		case "빨강" : recommendation = "빨간 짬뽕"; break;
		case "노랑" : recommendation = "노란 참외"; break;
		case "초록" : recommendation = "초록색 시금치"; break;
		case "파랑" : recommendation = "파란 파워에이드"; break;
		}
		
		//jsp에 새로 생성된 data를 전달하기 위해 request에 속성으로 저장한다.
		//key:String, value:Object 저장
		request.setAttribute("recommendation", recommendation);
		
		//3. html작성을 jsp에 위임.
		// /(슬래시)로 시작한다면, /WebContent에서 조회함.
		RequestDispatcher reqDispatcher
			= request.getRequestDispatcher("/servlet/testPersonEnd.jsp");
		reqDispatcher.forward(request, response);
		
		//3. 응답메시지처리 html
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개취 검사 결과</title>");		
		out.println("<style>");
		out.println(".recommendation { font-size: 3em; color: lime; text-decoration: underline; }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>개인 취향 검사 결과 jsp</h1>");
		out.println("<p>" + name + "님의 개인취향 검사 결과는 </p>");
		out.println("<p>" + color + "색을 좋아합니다. </p>");
		out.println("<p>좋아하는 동물은 " + animal + "입니다.</p>");
		out.println("<p>좋아하는 음식은 " + Arrays.toString(foodArr)+ "입니다. </p>");
		out.println("<hr>");
		out.println("<p class='recommendation'>오늘은 " + recommendation + " 어떠세요?</p>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
}

package com.kh.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuOrderServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		//1. 사용자 입력값 처리
		String main_menu = request.getParameter("main_menu");
		String side_menu = request.getParameter("side_menu");
		String drink_menu = request.getParameter("drink_menu");
		
		
		//2. 합계계산 switch문
		int price = 0;
		switch(main_menu) {
		case "한우버거" : price += 5000; break;
		case "밥버거" : price += 4500; break;
		case "치즈버거" : price += 4000; break;
		}
		switch(side_menu) {
		case "감자튀김" : price += 1500; break;
		case "어니언링" : price += 1700; break;
		}
		switch(drink_menu) {
		case "콜라" : price += 1000; break;
		case "사이다" : price += 1000; break;
		case "커피" : price += 1500; break;
		case "밀크웨이크" : price += 2500; break;
		}
		
		request.setAttribute("price", price);
		
		
		//3. jsp위임 처리 RequestDispatcher
		// /menu/menuOrder.jsp
		RequestDispatcher reqDispatcher
			= request.getRequestDispatcher("/menu/menuOrder.jsp");
		reqDispatcher.forward(request, response);
	}
}

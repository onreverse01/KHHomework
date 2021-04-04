package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberViewServlet
 */
@WebServlet("/member/memberView")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 업무로직 : 회원객체 가져오기
//		HttpSession session = request.getSession();
//		Member member = null;
//		if(session != null) 
//			member = (Member)session.getAttribute("loginMember");
//		if(member == null) {
//			session.setAttribute("msg", "로그인후 사용할 수 있습니다.");
//			response.sendRedirect(request.getContextPath());
//			return;
//		}
		
		//2. jsp 위임처리
		request.getRequestDispatcher("/WEB-INF/views/member/memberView.jsp")
			   .forward(request, response);
		
	}
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		
//		String btn = request.getParameter("btn");
//		
//		String memberId = request.getParameter("memberId");
//		String password = request.getParameter("password");
//		String memberName = request.getParameter("memberName");
//		String _birthday = request.getParameter("birthday");
//		String gender = request.getParameter("gender");
//		String email = request.getParameter("email");
//		String phone = request.getParameter("phone");
//		String address = request.getParameter("address");
//		
//		String[] hobbyArr = request.getParameterValues("hobby");
//		
//		String hobby = "";
//		
//		if(hobbyArr != null)
//			hobby = String.join(",", hobbyArr);
//		
//		Date birthday = null;
//		if (_birthday != null && !"".equals(_birthday))
//			birthday = Date.valueOf(_birthday);
//		
//		Member member = new Member(memberId, password, memberName, MemberService.MEMBER_ROLE, gender, birthday, email, phone, address, hobby, null);
//		
//		System.out.println("입력한 회원정보 : "+member);
//		
//		String msg = "";
//		
//		if(btn.equals("정보수정")) {
//			int result = new MemberService().updateMember(member);
//			if(result > 0) 
//				msg = "정보가 수정되었습니다.";
//			else
//				msg = "정보수정실패";
//		}
//		else if(btn.equals("탈퇴")) {
//			int result = new MemberService().deleteMember(memberId);
//			if(result > 0) 
//				msg = "회원탈퇴성공";
//			else
//				msg = "회원탈퇴실패";
//		}
//		
//		
//		
//	}

}

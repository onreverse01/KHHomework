package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 게시글 상세보기
	 * - board + attachment 조회
	 * - 조인없이 두번의 쿼리요청할 것
	 * 
	 * 게시글 등록 성공시 바로 상세보기 페이지로 이동할 것.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 사용자 입력값 : no
		
		//2. 업무로직 : board객체 조회(첨부파일 조회)
		
		//3. jsp forwarding
		request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp")
			   .forward(request, response);
	}

}

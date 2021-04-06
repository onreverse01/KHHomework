package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값처리
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
//		String attach = request.getParameter("attach");
		
		//1번째 방법
		Board b = new Board(0, title, writer, content, null, 0, null);
		
		//2번째 방법
//		Board b = new Board();
//		b.setTitle(title);
//		b.setTitle(writer);
//		b.setTitle(content);
		
		System.out.println("입력한 게시판정보 : " + b);
		//2. 업무로직 : db에 insert
		int result = boardService.insertBoard(b);
		String msg = (result > 0) ? "성공적으로 올렸습니다." : "게시판 등록 실패!";
//		String msg = "";
//		if(result>0) 
//			msg = "성공적으로 올렸습니다.";
//		else
//			msg = "게시판 등록 실패";
		
		
		//3. DML요청 : 리다이렉트 & 사용자피드백
		// /mvc/board/boardList
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/board/boardList");
	}

}

package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MvcFileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/boardUpdateForm.jsp")
			   .forward(request, response);
		
		//1.사용자 입력값 
		int no = Integer.parseInt(request.getParameter("no"));
				
		//2.업무로직
		Board board = boardService.selectOne(no);
				
		//3.jsp포워딩
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/views/board/boardUpdateForm.jsp")
			   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//1. MultipartRequest객체 생성
			// /WebContent/upload/board/업로드파일명.jpg
			// web rool dir를 절대경로로 반환
			String saveDirectory = getServletContext().getRealPath("/upload/board");
			System.out.println("saveDirectory@servlet = " + saveDirectory);
			
			//최대파일허용크기 10mb = 10 * 1kb * 1kb
			int maxPostSize = 10 * 1024 * 1024;
			
			//인코딩
			String encoding = "utf-8";
			
			//파일명 변경정책 객체
			//중복파일인 경우, numbering처리
	//		FileRenamePolicy policy = new DefaultFileRenamePolicy();
			FileRenamePolicy policy = new MvcFileRenamePolicy();
			
			MultipartRequest multipartRequest = new MultipartRequest(
																request, 
																saveDirectory, 
																maxPostSize, 
																encoding, 
																policy
															);
			//2. db에 게시글/첨부파일 정보 저장
			
			//2-1. 사용자 입력값처리
	//		String title = request.getParameter("title");
	//		String writer = request.getParameter("writer");
	//		String content = request.getParameter("content");
			
			int no = Integer.parseInt(multipartRequest.getParameter("no"));
			String title = multipartRequest.getParameter("title");
			String writer = multipartRequest.getParameter("writer");
			String content = multipartRequest.getParameter("content");
			
	//		String attach = request.getParameter("attach");
			
			//업로드한 파일명
			String originalFileName = multipartRequest.getOriginalFileName("upFile");
			String renameFileName = multipartRequest.getFilesystemName("upFile");;
			
			
			//1번째 방법 게시판 등록실패
	//		Board b = new Board(0, title, writer, content, null, 0, null);
			
			//2번째 방법
			Board b = new Board();
			b.setNo(no);
			b.setTitle(title);
			b.setWriter(writer);
			b.setContent(content);
	
			if(originalFileName != null) {
				Attachment attach = new Attachment();
				attach.setOriginalFileName(originalFileName);
				attach.setRenamedFileName(renameFileName);
				b.setAttach(attach);
			}
			
			System.out.println("입력한 게시판정보 : " + b);
			//2. 업무로직 : db에 insert
			int result = boardService.updateBoard(b);
			String msg = (result > 0) ? "게시글 수정 성공!" : "게시글 수정 실패!";
	//		String msg = "";
	//		if(result>0) 
	//			msg = "성공적으로 올렸습니다.";
	//		else
	//			msg = "게시판 등록 실패";
			String location = request.getContextPath() + "/board/boardView?no=" + b.getNo();
			
			//3. DML요청 : 리다이렉트 & 사용자피드백
			// /mvc/board/boardList
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			response.sendRedirect(location);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // was에게 다시 던져서 에러페이지로 전환함.
		}
	}

}

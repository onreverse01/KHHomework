package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MvcFileRenamePolicy;

/**
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * 0. form의 속성 enctype="multipart/form-data" 추가
	 * 1. MultipartRequest객체 생성 : 서버컴퓨터 파일 저장
	 * 		- request
	 * 		- 저장경로
	 * 		- encoding
	 * 		- 최대허용크기
	 * 		- 파일명 변경정책 객체
	 * 2. db에 파일정보를 저장
	 * 		- 사용자가 저장한 파일명 original_filename
	 * 		- 실제 저장된 파일명 renamed_filename
	 * 
	 * MultipartRequest객체를 사용하면, 
	 * 기존 HttpServletRequest에서는 사용자입력값에 접근할 수 없다.
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		b.setTitle(title);
		b.setTitle(writer);
		b.setTitle(content);

		if(originalFileName != null) {
			Attachment attach = new Attachment();
			attach.setOriginalFileName(originalFileName);
			attach.setRenamedFileName(renameFileName);
			b.setAttach(attach);
		}
		
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

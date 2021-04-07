package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
import board.model.vo.Board;

public class BoardService {

	private BoardDao boardDao = new BoardDao();

	public List<Board> selectList(int start, int end) {
		Connection conn = getConnection();
		List<Board> list = boardDao.selectList(conn, start, end);
		close(conn);
		return list;
	}

	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalContents = boardDao.selectBoardCount(conn);
		close(conn);
		return totalContents;
	}

	/**
	 * 첨부파일 있는 경우, attach객체를 attachment테이블에 등록한다.
	 * - board등록, attachment등록은 하나의 트랜잭션으로 처리되어야한다.
	 * - 하나의 Connection에 의해 처리되어야한다.
	 * @param b
	 * @return
	 */
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = 0;

		try {
			result = boardDao.insertBoard(conn, b);
			
			//생성된 board_no를 가져오기
			int boardNo = boardDao.selectLastBoardNo(conn);
			System.out.println("boardNo@service = " + boardNo);
			
			if(b.getAttach() != null) {
				//참조할 boardNo세팅
				b.getAttach().setBoardNo(boardNo);
				result = boardDao.insertAttachment(conn, b.getAttach());
			}
			commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
			result = 0;
		} finally {
			close(conn);
		}
//		if(result > 0) commit(conn);
//		else rollback(conn);
		
		return result;
	}
	
}

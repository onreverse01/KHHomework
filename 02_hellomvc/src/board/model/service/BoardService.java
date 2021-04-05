package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

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
	
}

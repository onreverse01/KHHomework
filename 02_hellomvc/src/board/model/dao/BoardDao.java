package board.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import board.model.vo.Board;
import member.model.dao.MemberDao;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		//board-query.properties의 내용읽어와서 prop에 저장
		//resource/sql/board-query.properties가 아니라
		//WEB-INF/clases/sql/board-query.properties에 접근해야함.
		String fileName = MemberDao.class
								   .getResource("/sql/board/borad-query.properties")
								   .getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Board> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> list = null;
		
		String query = prop.getProperty("selectPagedList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>(); //리스트 할당
			while (rset.next()) {
				Board b = new Board();
				b.setNo(rset.getInt("NO"));
				b.setTitle(rset.getString("TITLE"));
				b.setWriter(rset.getString("WRITER"));
				b.setContent(rset.getString("CONTENT"));
				b.setRegDate(rset.getDate("REG_DATE"));
				b.setReadCount(rset.getInt("READ_COUNT"));
				
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectBoardCount(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectBoardCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				totalContents = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}

	public int insertBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getWriter());
			pstmt.setString(3, b.getContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}

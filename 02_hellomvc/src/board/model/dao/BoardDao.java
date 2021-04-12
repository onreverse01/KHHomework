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

import board.model.exception.BoardException;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.BoardComment;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		//board-query.properties의 내용읽어와서 prop에 저장
		//resource/sql/board-query.properties가 아니라
		//WEB-INF/clases/sql/board-query.properties에 접근해야함.
		String fileName = BoardDao.class
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
//		List<Board> list = null;
		List<Board> list = new ArrayList<>();
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
//			list = new ArrayList<>(); //리스트 할당
			while (rset.next()) {
				Board board = new Board();
				board.setNo(rset.getInt("NO"));
				board.setTitle(rset.getString("TITLE"));
				board.setWriter(rset.getString("WRITER"));
				board.setContent(rset.getString("CONTENT"));
				board.setRegDate(rset.getDate("REG_DATE"));
				board.setReadCount(rset.getInt("READ_COUNT"));
				
				System.out.println(rset.getInt("attach_no"));
				//첨부파일이 있는 경우
				if(rset.getInt("attach_no") != 0) {
					Attachment attach = new Attachment();
					attach.setNo(rset.getInt("attach_no"));
					attach.setBoardNo(rset.getInt("no"));
					attach.setOriginalFileName(rset.getString("original_filename"));
					attach.setRenamedFileName(rset.getString("renamed_filename"));
					attach.setStatus("Y".equals(rset.getString("status")));
					board.setAttach(attach);
				}
				
				list.add(board);
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

	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시물 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastBoardNo(Connection conn) {
		int boardNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectLastBoardNo");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				boardNo = rset.getInt("board_no");
			}
		} catch (SQLException e) {
			throw new BoardException("게시물 등록 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return boardNo;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFileName());
			pstmt.setString(3, attach.getRenamedFileName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시물 첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
		
	}

	public Board selectOne(Connection conn, int no) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				board = new Board();
				board.setNo(rset.getInt("no"));
				board.setTitle(rset.getString("title"));
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setReadCount(rset.getInt("read_count"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return board;
	}
	
	public Attachment selectOneAttachment(Connection conn, int no) {
		Attachment attach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneAttachment");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("board_no"));
				attach.setOriginalFileName(rset.getString("original_filename"));
				attach.setRenamedFileName(rset.getString("renamed_filename"));
				attach.setStatus("Y".equals(rset.getString("status")) ?  true : false);
			}
		}catch(Exception e){
			throw new BoardException("첨부파일 조회 오류", e);
		}finally{
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public int deleteBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시물 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("게시물 수정 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteAttachment(Connection conn, String attachNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attachNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertBoardComment(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertBoardComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			//(no, comment_level, writer, content, board_no, comment_ref)
			pstmt.setInt(1, bc.getCommentLevel());
			pstmt.setString(2, bc.getWriter());
			pstmt.setString(3, bc.getContent());
			pstmt.setInt(4, bc.getBoardNo());
//			pstmt.setInt(5, bc.getCommentRef() == 0 ? null : bc.getCommentRef());
			pstmt.setObject(5, bc.getCommentRef() == 0 ? null : bc.getCommentRef());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException("댓글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<BoardComment> selectBoardCommentList(Connection conn, int no) {
		List<BoardComment> commentList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardCommentList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				BoardComment bc = new BoardComment();
				bc.setNo(rset.getInt("no"));
				bc.setCommentLevel(rset.getInt("comment_level"));
				bc.setWriter(rset.getString("writer"));
				bc.setContent(rset.getString("content"));
				bc.setBoardNo(rset.getInt("board_no"));
				bc.setCommentRef(rset.getInt("comment_ref"));
				bc.setRegDate(rset.getDate("reg_date"));
				commentList.add(bc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return commentList;
	}

}

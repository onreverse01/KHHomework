package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;

import static common.JDBCTemplate.close;
public class MemberDao {

	/**
	 *  Dao
	 * 
	 * 3. PreparedStatement객체 생성(미완성쿼리)
	 * 3.1 ? 값대입
	 * 4. 실행 : DML(executeUpdate)-> int, DQL(executeQuery) -> ResultSet
	 * 4.1 ResultSet -> Java객체 옮겨담기
	 * 5. 자원반납(생성역순 rset - pstmt)
	 *
	 */
	public List<Member> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member order by enroll_date desc";
		List<Member> list = null;
		
		try {
			//3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3.1 ? 값대입
			
			//4. 실행 : DML(executeUpdate)-> int, DQL(executeQuery) -> ResultSet
			rset = pstmt.executeQuery();
			//4.1 ResultSet -> Java객체 옮겨담기
			list = new ArrayList<>();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String gender= rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone= rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate= rset.getDate("enroll_date");
				Member member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. 자원반납(생성역순 rset - pstmt)
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, ?, default)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Member selectOne(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member where member_id = ?";
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				
				member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

	public List<Member> selectByName(Connection conn, String memberName) {
		List<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_name like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+memberName+"%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				memberName = rset.getString("member_name");
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				
				Member member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
				
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from member where member_id=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update member set "
					+ " password=?"
					+ ",email=?"
					+ ",phone=?"
					+ ",address=?"
					+ " where member_id=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getMemberId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
}

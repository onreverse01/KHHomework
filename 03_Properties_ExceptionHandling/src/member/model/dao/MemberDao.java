package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.exception.MemberException;
import member.model.vo.Member;

public class MemberDao {

	private Properties prop = new Properties();
	
	
	/**
	 * 1. MemberDao객체 생성시(최초1회) (딱 한 번!)
	 * member-query.properties의 내용을 읽어다 prop에 저장한다.
	 * 
	 * 2. dao메소드 호출시마다 prop으로부터 query를 가져다 사용한다.
	 * 
	 */
	public MemberDao() {
		//member-query.properties -> prop
		String fileName = "resources/member-query.properties";
		try {
			prop.load(new FileReader(fileName));
//			System.out.println(prop);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  Dao
	 * 
	 * 3. PreparedStatement객체 생성(미완성쿼리)
	 * 3.1 ? 값대입
	 * 4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
	 * 4.1 ResultSet -> Java객체 옮겨담기
	 * 5. 자원반납(생성역순 rset - pstmt) 
	 *
	 */
	public List<Member> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAll");
		List<Member> list = null;
		
		try {
			//3. PreparedStatement객체 생성(미완성쿼리)
			pstmt = conn.prepareStatement(sql);
			//3.1 ? 값대입
			
			//4. 실행 : DML(executeUpdate) -> int, DQL(executeQuery) -> ResultSet
			rset = pstmt.executeQuery();
			//4.1 ResultSet -> Java객체 옮겨담기
			list = new ArrayList<>();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
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
			//e.printStackTrace();
			//예외를 전환 : RuntimeException, 의미분명한 커스텀 예외객체로 전환
			throw new MemberException("회원 전체조회", e);
		} finally {
			//5. 자원반납(생성역순 rset - pstmt)
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			//PreparedStatment객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,m.getMemberId());
			pstmt.setString(2,m.getPassword());
			pstmt.setString(3,m.getMemberName());
			pstmt.setString(4,m.getGender());
			pstmt.setInt(5,m.getAge());
			pstmt.setString(6,m.getEmail());
			pstmt.setString(7,m.getPhone());
			pstmt.setString(8,m.getAddress());
			pstmt.setString(9,m.getHobby());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원 가입", e);
		} finally {
			close(pstmt);
		}
//		System.out.println("result@dao="+result);
		
		return result;
	}

	public Member selectOneMember(Connection conn, String memberId) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneMember");
		
		try {
			//1. PreparedStatement객체 생성, 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			//2. 쿼리 실행, ResultSet => Member
			rset = pstmt.executeQuery();
			while(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
			}
			
			
		} catch (SQLException e) {
			throw new MemberException("회원 아이디조회", e);
		} finally {
			//3. ResultSet, PreparedStatement객체 반납
			close(rset);
			close(pstmt);
		}
		
//		System.out.println("member@dao="+m);
		
		return m;
	}
	
	public List<Member> selectByName(Connection conn, String memberName) {
		ArrayList<Member> list = null;
		
		//사용후 반납해야할(close)자원들은 try~catch문 바깥에서 선언해야 한다.
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectByName");
		try {
			
			//3. 쿼리문을 실행할 statement객체 생성
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+memberName+"%");
			//4. 쿼리문 전송, 실행결과 받기
			rset = pstmt.executeQuery();
			
			//5. 받은 결과값들을 객체에 옮겨 저장하기
			list = new ArrayList<Member>();
			
			while(rset.next()){
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));

				list.add(m);
			}
			
		} catch (Exception e){
			throw new MemberException("회원 이름조회", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int updateMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("updateMember");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getMemberId());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new MemberException("회원 정보변경", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("deleteMember");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new MemberException("회원 탈퇴", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Member> delSelectAll(Connection conn) {
		List<Member> list = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("delSelectAll");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = rset.getString("password");
				String memberName = rset.getString("member_name");
				String gender = rset.getString("gender");
				int age = rset.getInt("age");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				String hobby = rset.getString("hobby");
				Date enrollDate = rset.getDate("enroll_date");
				Date delDate = rset.getDate("del_date");
				Member member = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate, delDate);
				list.add(member);
			}
		} catch (SQLException e) {
			throw new MemberException("탈퇴회원 조회", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

}

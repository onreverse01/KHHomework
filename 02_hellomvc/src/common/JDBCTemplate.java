package common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 
 * Service, Dao클래스의 공통부문을 static메소드 제공
 * 예외처리를 공통부분에서 작성하므로, 사용(호출)하는 쪽의 코드를 간결히 할 수 있다.
 */
public class JDBCTemplate {
	static String driverClass;
	static String url;
	static String user;
	static String password;
	
	static {
		//data-source.properties의 내용을 읽어서 변수에 대입
		Properties prop = new Properties();
		String fileName = JDBCTemplate.class	// 클래스 객체
									  .getResource("/data-source.properties") //url
									  .getPath(); //String객체 : 절대경로
		System.out.println("fileName@JDBCTemplate = " + fileName);
		try {
			prop.load(new FileReader(fileName));
//			System.out.println(prop);
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			//1. DriverClass등록(최초1회)
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * DBCP 이용버전
	 * 
	 * Resource등록 - JNDI를 통한 참조
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context ctx = new InitialContext();
			/**
			 * JNDI구조
			 * java:/comp/env/ + jdbc/myoracle
			 */
			DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/myoracle");
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/*
	public static Connection getConnection() {
		Connection conn = null;
		try {
			//2. Connection객체생성 url, user, password
			conn = DriverManager.getConnection(url, user, password);
			//2.1 자동커밋 false설정
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	*/
	
	public static void close(Connection conn) {
		//7. 자원반납(conn) 
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null)
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null)
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null)
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

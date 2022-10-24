package com.libraryApp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.libraryApp.domain.Book;
import com.libraryApp.domain.User;

public class UserDao {

	private Connection conn = null;
	private PreparedStatement pstmt= null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String db_url = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private String db_id = "root";
	private String db_pw = "ezen";
	
	public int insertUser(String name, String residenceNum) {
		int result = 0;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
			String sql = "insert into user_tbl(user_name, user_residence_num) values(?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, residenceNum);
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
				
		return result;
	}
	
	public User readUser(String residenceNum) {
		
		User user = null;
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
			String sql = "select * from user_tbl where user_residence_num =\""+residenceNum+"\"";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int num = rs.getInt("user_num");
				String name = rs.getString("user_name");
				user = new User(num, name, residenceNum);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
		
	}
}

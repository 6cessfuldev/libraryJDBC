package com.libraryApp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnTest {
	
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt= null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/bank?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
		String db_id = "root";
		String db_pw = "ezen";
		
		try {
			/* 1. driver 클래스 */
			Class.forName(driver); 
			
			/* 2. Conncetion 객체 생성 */
			conn = DriverManager.getConnection(db_url, db_id, db_pw);
			System.out.println(conn);
			
			/* 3. PreparedStatement 객체 생성 */
			String sql = "select * from user_tbl where user_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "육성민");
			
			/* 4. 받을 결과 값이 있으면 ResultSet 객체 생성 */
			rs = pstmt.executeQuery();
			
			
			/* 5. 예외 처리 */
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

			/* 6. 역순으로 닫기 */
		} finally {
			try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}

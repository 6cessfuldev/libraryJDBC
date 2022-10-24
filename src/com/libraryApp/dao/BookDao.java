package com.libraryApp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.libraryApp.domain.Book;

public class BookDao {

	private Connection conn = null;
	private PreparedStatement pstmt= null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String db_url = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private String db_id = "root";
	private String db_pw = "ezen";
	
	public int insertBook(String name, String author, Date date) {
		int result = 0;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
			String sql = "insert into book_tbl(book_name, book_author, book_regdate) values(?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, author);
			pstmt.setDate(3, date);
			
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
	
	public Book readBookByName(String name) {
		
		Book book = null;
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
			String sql = "select * from book_tbl where book_name =\""+name+"\"";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int num = rs.getInt("book_num");
				String author = rs.getString("book_author");
				Date date = rs.getDate("book_Regdate");
				boolean isRent = rs.getBoolean("book_isrent");
				book = new Book(num, name, author, date, isRent);
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

		return book;
		
	}
	
public ArrayList<Book> readBookByAuthor(String author) {
		
		ArrayList<Book> result = new ArrayList<>();
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
			String sql = "select * from book_tbl where book_author =\""+author+"\"";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int num = rs.getInt("book_num");
				String name = rs.getString("book_name");
				Date date = rs.getDate("book_Regdate");
				boolean isRent = rs.getBoolean("book_isrent");
				book = new Book(num, name, author, date, isRent);
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

		return result;
		
	}
	
	public int updateBookName(int num, String name) {
		
		int result = 0;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
			String sql = "update book_tbl set book_name = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);

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
	
	public int updateBookAuthor(int num, String author) {
	
		int result = 0;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
			String sql = "update book_tbl set book_author = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, author);

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
	
	public int deleteBook(String name) {
		int result = 0;
		
		try {
			
			Class.forName(driver);
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
			String sql = "delete from book_tbl where book_name = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);

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
	
}

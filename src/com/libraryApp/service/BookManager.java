package com.libraryApp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import com.libraryApp.dao.BookDao;
import com.libraryApp.dao.UserDao;
import com.libraryApp.domain.Book;
import com.libraryApp.domain.User;


public class BookManager {
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BookDao bookdao = new BookDao();
	private UserDao userdao = new UserDao();
	
//	public void Main () throws NumberFormatException, IOException, ParseException {
//		
//		int choice = 0;
//		
//		do {
//			System.out.println("==============");
//			System.out.println("1. 도서 관리");
//			System.out.println("2. 도서관 이용");
//			System.out.println("==============");
//			choice = Integer.parseInt(br.readLine());
//			
//			switch(choice) {
//			case 1 : libMenu(); break;
//			case 2 : userMenu(); break;
//				default : System.out.println("잘못된 입력입니다.");
//			}
//		} while(true);
//	}
//	
///*       도서 관리 메소드      */
	
	public void libMenu() throws IOException, ParseException {
		
		int choice = 0;
		
		System.out.println("==============");
		System.out.println("1. 도서 등록");
		System.out.println("2. 도서 목록");
		System.out.println("3. 도서 수정");
		System.out.println("4. 도서 삭제");
		System.out.println("==============");
		choice = Integer.parseInt(br.readLine());
		
		switch(choice) {
		case 1 : bookCreate(); break;
		case 2 : bookRead(); break;
		case 3 : bookUpdate(); break;
		case 4 : bookDelete(); break;
			default : System.out.println("잘못된 입력입니다.");
		}
	}
	
	public void bookCreate() throws IOException, ParseException {
		System.out.println("책 이름은 : ");
		String name = br.readLine();
		System.out.println("저자 이름은 : ");
		String author = br.readLine();
		System.out.println("등록 날짜는 :[yyyy-MM-dd]");
		String date = br.readLine();
		Date d = Date.valueOf(date);
		
		if(bookdao.readBook(br.readLine()) !=null) {
			System.out.println("이미 등록된 책입니다.");
			return;
		}
		
		int result = bookdao.insertBook(name, author, d);
		if(result > 0) {
			System.out.println("등록되었습니다.");
		} else {
			System.out.println("에러");
		}
	}
	
	public void bookRead() throws IOException {
		System.out.println("책의 이름을 입력해주세요.");
		Book book = bookdao.readBook(br.readLine());
		if(book == null) {
			System.out.println("검색 결과가 없습니다.");
			return;
		}
				
		System.out.println(book);
	}
	
	public void bookUpdate() throws IOException, ParseException {
		System.out.println("수정할 책 이름은 :");
		String name = br.readLine();
		
		boolean flag = false;
		Book rs = bookdao.readBook(name);
		if(rs == null) {
			System.out.println("검색 결과 없습니다.");
			return;
		}
		
		System.out.println("수정할 내용을 선택해주세요.");
		System.out.println("1. 도서명");
		System.out.println("2. 저자명");
		System.out.println("3. 등록일");
		
		int choice = Integer.parseInt(br.readLine());
		switch(choice) {
		case 1: 
			System.out.println("무엇으로 수정하시겠습니까?");
			name = br.readLine();
			bookdao.updateBookName(rs.getNum(), name);
			flag = true;
			break;
			
		case 2:
			System.out.println("무엇으로 수정하시겠습니까?");
			String author = br.readLine();
			bookdao.updateBookAuthor(rs.getNum(), author);
			flag = true;
			break;
			default : 
				System.out.println("잘못된 입력입니다.");
				return;
		}
	
		if(flag) {
			System.out.println("수정되었습니다.");
		} 
	}
	
	public void bookDelete() throws IOException {
		System.out.println("삭제할 책 이름은 :");
		String name = br.readLine();
		
		Book rs = bookdao.readBook(name);
		boolean flag = false;
		if(rs == null) {
			System.out.println("검색 결과 없습니다.");
			return;
		}
		
		if(bookdao.deleteBook(name) >0) {
			System.out.println("삭제되었습니다.");
		} else {
			System.out.println("에러");
		}
	}
//	
//	/* 도서관 이용 메서드 */
//	
//	public void userMenu() throws NumberFormatException, IOException {
//		
//		int choice = 0;
//		
//		System.out.println("==============");
//		System.out.println("1. 회원 가입");
//		System.out.println("2. 도서 검색");
//		System.out.println("3. 도서 대출");
//		System.out.println("4. 도서 반납");
//		System.out.println("==============");
//		choice = Integer.parseInt(br.readLine());
//			
//		switch(choice) {			
//		case 1 : signUp(); break;
//		case 2 : searchBook(); break;
//		case 3 : rentBook(); break;
//		case 4 : returnBook(); break;
//			default : System.out.println("잘못된 입력입니다.");
//		}
//	}

	public void signUp() throws IOException {
		System.out.println("이름을 입력하세요.");
		String name = br.readLine();
		System.out.println("주민번호를 입력하세요.(특수문자 없이)");
		String residence = br.readLine();

		User user = userdao.readUser(residence);
		if(user != null) {
			System.out.println("이미 가입되어 있습니다.");
			return;
		}
		
		userdao.insertUser(name, residence);
		System.out.println("회원 가입 되었습니다.");
	}
	
	public void searchBook() throws IOException {
		
		int choice = 0;
		System.out.println("===검색 내용===");
		System.out.println("1. 도서명 ");
		System.out.println("2. 저자명 ");
		choice = Integer.parseInt(br.readLine());
		if(choice == 1) {
			System.out.println("검색어를 입력");
			String name = br.readLine();
			Book book = bookdao.readBookByName(name);
			if(book != null) {
				System.out.println(book);
			} else {
				System.out.println("검색 결과 없습니다.");
				return;
			}
		} else if(choice == 2) {
			System.out.println("검색어를 입력");
			String writer = br.readLine();
			ArrayList<Book> res = bookdao.readBookByAuthor(writer);
			
			if(res != null ) {
				System.out.println(book);
				res.add(book);
			}
		} else {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		
		for (Book book : res) {
			System.out.println(book);
		}
	}
	
//	public void rentBook() throws IOException {
//		//유저 검색
//		System.out.println("회원 이름을 입력해주세요");
//		String userName = br.readLine();
//		boolean userFlag = false;
//		boolean bookFlag = false;
//		boolean rentFlag = false;
//		for (User user : userList) {
//			if(user.getName().equals(userName)) {
//				String name = br.readLine();
//				userFlag = true;
//				
//				for (Book book : bookList) {
//					if(book.getBook_name().equals(name)) {
//						
//						bookFlag = true;
//						if(book.isRent()== true) {
//							rentFlag = true;
//						} else {
//							user.addList(book);
//							book.setRent(true);
//						}
//					}
//				}
//			}
//		}
//		
//		if(userFlag == false) {
//			System.out.println("회원이 아닙니다.");
//		} else if(bookFlag == false){
//			System.out.println("책이 없습니다.");
//		} else if(rentFlag == true) {
//			System.out.println("이미 대출된 책입니다.");
//		} 
//		
//		if(userFlag && bookFlag) {
//			System.out.println("대출 완료");
//		}
//	}
//	
//	public void returnBook() throws IOException {
//		
//		System.out.println("회원 이름을 입력해주세요");
//		String userName = br.readLine();
//		boolean userFlag = false;
//		boolean bookFlag = false;
//		boolean rentFlag = false;
//		for (User user : userList) {
//			if(user.getName().equals(userName)) {
//				String name = br.readLine();
//				userFlag = true;
//				
//				for (Book book : user.getRentList()) {
//					if(book.getBook_name().equals(name)) {
//						
//						bookFlag = true;
//						if(book.isRent()== true) {							
//							user.getRentList().remove(book);
//							book.setRent(false);
//						}
//					}
//				}
//			}
//		}
//		
//		if(userFlag == false) {
//			System.out.println("회원이 아닙니다.");
//		} else if(bookFlag == false){
//			System.out.println("책이 없습니다.");
//		} 
//		
//		if(userFlag && bookFlag) {
//			System.out.println("반납 완료");
//		}
//	}
//	
//	public void displayNew() throws ParseException {
//		System.out.println("신간도서입니다.");
//		for (Book book : bookList) {
//			String date = "20220101";
//			Date d = new SimpleDateFormat("yyyyMMdd").parse(date);
//			int rs = book.getRegDate().compareTo(d);
//			if(rs>=0) {
//				System.out.println(book.toString());
//			}
//		}
//	}
	
}

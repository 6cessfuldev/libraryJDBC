package com.libraryApp.domain;

import java.sql.Date;

public class Book {

	private int num;
	private String book_name;
	private String writer;
	private Date regDate;
	private boolean isRent;
	
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public boolean isRent() {
		return isRent;
	}
	public void setRent(boolean isRent) {
		this.isRent = isRent;
	}
	
	public Book(int num, String book_name, String writer, Date regDate, boolean isRent) {
		super();
		this.num = num;
		this.book_name = book_name;
		this.writer = writer;
		this.regDate = regDate;
		this.isRent = isRent;
	}
	
	@Override
	public String toString() {
		return "Book [num=" + num + ", book_name=" + book_name + ", writer=" + writer + ", regDate=" + regDate
				+ ", isRent=" + isRent + "]";
	}
}

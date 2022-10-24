package com.libraryApp.domain;

import java.util.ArrayList;

public class User {

	private int num;
	private String name;
	private String residenceNum;

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResidenceNum() {
		return residenceNum;
	}
	public void setResidenceNum(String residenceNum) {
		this.residenceNum = residenceNum;
	}
	public User(int num, String name, String residenceNum) {
		super();
		this.num = num;
		this.name = name;
		this.residenceNum = residenceNum;
	}
}

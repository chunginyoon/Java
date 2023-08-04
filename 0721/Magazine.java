package com.ssafy.hw2011;

import bookmanage.Book;

public class Magazine extends Book {
	int year;
	int month;
	public Magazine(String isbn, String title, String author, String publisher, int price, String desc,int year, int month) {
		super(isbn, title, author, publisher, price, desc);
		this.year = year;
		this.month = month;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		String result = String.format("%-10s| %-10s| %-7s| %-10s| %-10d| %-15s| %-7d| %d",isbn, title, author, publisher, price, desc, year, month);
		return result;
	}
}

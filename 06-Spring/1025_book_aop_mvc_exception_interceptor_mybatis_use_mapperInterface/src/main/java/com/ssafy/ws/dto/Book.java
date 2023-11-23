package com.ssafy.ws.dto;

public class Book {

	private int no;
	private String title;
	private String author;
	private int price;
	private String content;
	
	public Book() {}

	public Book(int no, String title, String author, int price, String content) {
		this.no = no;
		this.title = title;
		this.author = author;
		this.price = price;
		this.content = content;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		return "Book [no=" + no + ", title=" + title + ", author=" + author + ", price=" + price + ", content="
				+ content + "]";
	}
	
	
}

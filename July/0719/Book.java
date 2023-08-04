package objarray;

public class Book {
	String isbn;
	String title;
	String author;
	String publisher;
	int price;
	String desc;
	
	//생성자
	public Book(String isbn, String title, String author, String publisher, int price, String desc) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return ""+isbn+"   | "+title+"   | "+author+" | "+publisher+"   | "+price+" | "+desc;
	}
}


package bookmanage;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookManagerImpl2 implements IBookManager {
	
    Map<String, Book> books = new HashMap<>();
    
    // 생성자 private-객체 생성 불가
    private BookManagerImpl2() { }
    
    //내부에서 자기 자신 객체 생성
    private static BookManagerImpl2 instance = new BookManagerImpl2();
    
    public static BookManagerImpl2 getInstance() {
		return instance;
	}
    
	@Override
	public void add(Book book) {
		books.put(book.getIsbn(), book);
	}
	
	@Override
	public void remove(String isbn) {
		//삭제한 요소의 인덱스 찾기
		for(String key : books.keySet())
			if(key.equals(isbn))
				books.remove(key);
	}
	
	@Override
	public Book[] getList() {
		 Collection<Book> values = books.values();
	     Book[] list = values.toArray(new Book[books.size()]);
		return Arrays.copyOf(list, books.size());
	}
	
	@Override
	public Book searchByIsbn(String isbn) {
		Book[] list = getList();
		for(int i=0; i<books.size(); i++) {
			if(list[i].getIsbn().equals(isbn))
				return list[i];
		}
		//조회했는데 없음
		return null;
	}
	
	@Override
	public Book[] searchByTitle(String title) {
		Book[] list = getList();
		Book[] list2 = new Book[books.size()];
		int size = 0;
		
		for(int i=0; i<books.size(); i++) {
			if(list[i].getTitle().contains(title)) {
				list2[size++] = list[i];
			}
		}
		return Arrays.copyOf(list2, size);
		
	}
	
	@Override
	public Magazine[] getMagazines() {
		int size = 0;
		Book[] list = getList();
		Magazine[] list2 = new Magazine[books.size()];
		
		for(int i=0; i<books.size(); i++) {
			if(list[i] instanceof Magazine) {
				list2[size++] = (Magazine) list[i];
			}
		}
		return Arrays.copyOf(list2, size);
	}
	
	@Override
	public Book[] getBooks() {
		int size=0;
		Book[] list = getList();
		Book[] list2 = new Book[books.size()];
		for(int i=0; i<books.size(); i++) {
			if(list[i] instanceof Book && !(list[i] instanceof Magazine)) {
				list2[size++] = list[i];
			}
		}
		return Arrays.copyOf(list2, size);
	}
	
	@Override
	public int getTotalPrice() {
		Book[] list = getList();
		int result = 0;
		for(int i=0; i<books.size(); i++) {
			result += list[i].getPrice();
		}
		return result;
	}
	
	@Override
	public double getPriceAvg() {
		Book[] list = getList();
		int result = 0;
		for(int i=0; i<books.size(); i++) {
			result += list[i].getPrice();
		}
		return result/books.size();
	}
}

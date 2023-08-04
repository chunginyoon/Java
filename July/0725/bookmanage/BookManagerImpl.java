package bookmanage;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class BookManagerImpl implements IBookManager {
	
    List<Book> books = new ArrayList<>();
    
    // 생성자 private-객체 생성 불가
    private BookManagerImpl() { }
    
    //내부에서 자기 자신 객체 생성
    private static BookManagerImpl instance = new BookManagerImpl();
    
    public static BookManagerImpl getInstance() {
		return instance;
	}
    
	@Override
	public void add(Book book) {
		books.add(book);
	}
	
	@Override
	public void remove(String isbn) {
		for(int i=0; i<books.size(); i++) {
			if(books.get(i).getIsbn().equals(isbn)) {
				books.remove(i);
				break;
			}
				
		}
				
	}
	
	@Override
	public Book[] getList() {
		Book[] list = new Book[books.size()];
		for(int i=0; i<books.size(); i++)
			list[i] = books.get(i);
		
		return Arrays.copyOf(list, books.size()); //return books.toArray(list);
	}
	
	@Override
	public Book searchByIsbn(String isbn) {
		for(int i=0; i<books.size(); i++) {
			if(books.get(i).getIsbn().equals(isbn))
				return books.get(i);
		}
		//조회했는데 없음
		return null;
	}
	
	@Override
	public Book[] searchByTitle(String title) {
		Book[] list = new Book[books.size()];
		int size = 0;
		for(int i=0; i<books.size(); i++) {
			if(books.get(i).getTitle().contains(title)) {
				list[size++] = books.get(i); //searchTitle.add(books.get(i));
			}
		}
		return Arrays.copyOf(list, size); //return searchTItle.toArray(new Book[0]));
										 //초기값 Book[0]으로 설정해놓으면 최종값이 0보다 클 경우 알아서 계산해서 6으로 바뀜
										//따라서 size를 사용하지 않아도 됨
		
	}
	
	@Override
	public Magazine[] getMagazines() {
		int size = 0;
		Magazine[] list = new Magazine[books.size()];
		
		for(int i=0; i<books.size(); i++) {
			if(books.get(i) instanceof Magazine) {
				list[size++] = (Magazine) books.get(i);
			}
		}
		return Arrays.copyOf(list, size);
	}
	
	@Override
	public Book[] getBooks() {
		int size=0;
		Book[] list = new Book[books.size()];
		for(int i=0; i<books.size(); i++) {
			if(books.get(i) instanceof Book && !(books.get(i) instanceof Magazine)) {
				list[size++] = books.get(i);
			}
		}
		return Arrays.copyOf(list, size);
	}
	
	@Override
	public int getTotalPrice() {
		int result = 0;
		for(int i=0; i<books.size(); i++) {
			result += books.get(i).getPrice();
		}
		return result;
	}
	
	@Override
	public double getPriceAvg() {
		int result = 0;
		for(int i=0; i<books.size(); i++) {
			result += books.get(i).getPrice();
		}
		return result/books.size();
	}
}

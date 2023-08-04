package step3;

import java.util.Arrays;

public class BookManagerImpl implements IBookManager {
	
	
    private static final int MAX_SIZE = 100;
    private Book[] books = new Book[MAX_SIZE];
    private int size = 0;
    
    
	@Override
	public void add(Book book) {
		books[size++] = book;
	}
	
	@Override
	public void remove(String isbn) {
		//삭제한 요소의 인덱스 찾기
		int deleteIdx = -1;
		for(int i=0; i<size; i++) {
			if(books[i].getIsbn().equals(isbn)) {
				deleteIdx = i;
				break;
			}
		}
		//삭제한 요소 위치와 마지막 요소 자리바꾸기
		books[deleteIdx] = books[size-1];
		books[size-1] = null;
		size--;
	}
	
	@Override
	public Book[] getList() {
		return Arrays.copyOf(books, size);
	}
	
	@Override
	public Book searchByIsbn(String isbn) {
		for(int i=0; i<size; i++) {
			// == 쓰면안됨
			if(books[i].getIsbn().equals(isbn)) {
				//조회해서 찾음
				return books[i];
			}
		}
		//조회했는데 없음
		return null;
	}
	
	@Override
	public Book[] searchByTitle(String title) {
		Book[] t_bks = new Book[MAX_SIZE];
		int t_size = 0;
		for(int i=0; i<size; i++) {
			if(books[i].getTitle().contains(title)) {
				t_bks[t_size++] = books[i];
			}
		}
		return Arrays.copyOf(t_bks, t_size);
		
	}
	
	@Override
	public Magazine[] getMagazines() {
		Magazine[] g_mgzs = new Magazine[MAX_SIZE];
		int m_size = 0;
		for(int i=0; i<size; i++) {
			if(books[i] instanceof Magazine) {
				g_mgzs[m_size++] = (Magazine) books[i];
			}
		}
		return Arrays.copyOf(g_mgzs, m_size);
		
	}
	
	@Override
	public Book[] getBooks() {
		Book[] g_bks = new Book[MAX_SIZE];
		int g_size = 0;
		for(int i=0; i<size; i++) {
			if(books[i] instanceof Book && !(books[i] instanceof Magazine)) {
				g_bks[g_size++] = books[i];
			}
		}
		return Arrays.copyOf(g_bks, g_size);
	}
	
	@Override
	public int getTotalPrice() {
		int result = 0;
		for(int i=0; i<size; i++) {
			result += books[i].getPrice();
		}
		return result;
	}
	
	@Override
	public double getPriceAvg() {
		int result = 0;
		for(int i=0; i<size; i++) {
			result += books[i].getPrice();
		}
		return result/size;
	}
	
	@Override
	public void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException{
		for(int i=0; i<size; i++)
			if(books[i].getIsbn().equals(isbn)) {
				if(books[i].quantity >= quantity) {
					books[i].quantity-=quantity;
					return;
				}
				
				else
					throw new QuantityException();
			}
		throw new ISBNNotFoundException(isbn);
	}
		

	
	@Override
	public void buy(String isbn, int quantity) throws ISBNNotFoundException{
		for(int i=0; i<size; i++)
			if(books[i].getIsbn().equals(isbn)) {
				books[i].quantity+=quantity;
				return;
			}
		throw new ISBNNotFoundException(isbn);
	}
}
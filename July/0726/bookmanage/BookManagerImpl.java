package bookmanage;

import java.util.Arrays;
import java.util.List;

import bookmanage.ISBNNotFoundException;
import bookmanage.QuantityException;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		
		return Arrays.copyOf(list, books.size());
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
				list[size++] = books.get(i);
			}
		}
		return Arrays.copyOf(list, size);
		
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
	
	@Override
	public void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException{
		for(int i=0; i<books.size(); i++)
			if(books.get(i).getIsbn().equals(isbn)) {
				if(books.get(i).getQuantity() >= quantity) {
					int q = books.get(i).getQuantity();
					q-=quantity;
					books.get(i).setQuantity(q);
					return;
				}
				
				else
					throw new QuantityException();
			}
		throw new ISBNNotFoundException(isbn);
	}
		

	
	@Override
	public void buy(String isbn, int quantity) throws ISBNNotFoundException{
		for(int i=0; i<books.size(); i++)
			if(books.get(i).getIsbn().equals(isbn)) {
				int q = books.get(i).getQuantity();
				q+=quantity;
				books.get(i).setQuantity(q);
				return;
			}
		throw new ISBNNotFoundException(isbn);
	}
	
	public void loadData() throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream("book.dat"); //파일에서 문자를 읽는다
		ObjectInputStream ois = new ObjectInputStream(fis); //읽어온 문자(데이터)를 객체로 변환한다
		
		books = (List<Book>) ois.readObject();
		
		//닫을 때는 쓰는 순서 반대로
		
		ois.close();
		fis.close();
		for(int i = 0; i<books.size(); i++)
			System.out.println(books.get(i));
	}
	
	public void saveData() throws IOException {
		//FileOutputStream fos = new FileOutputStream("obj.dat");
		//ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		//속도 up
		FileOutputStream fos = new FileOutputStream("book.dat"); //파일을 만든다?연다?
		BufferedOutputStream bos = new BufferedOutputStream(fos); //속도 up
		ObjectOutputStream oos = new ObjectOutputStream(bos); //객체를 직렬화된 데이터로 바꿔준다
		
		
		oos.writeObject(books); //객체를 받아서 직렬화된 데이터로 바꾼 후 파일에 쓴다
							 //여기까지 쓰기
		
		//파일 닫을 때는 여는 순서 반대로
		oos.close();
		bos.close();
		fos.close();
	}
	
	
}

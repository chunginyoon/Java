package bookarray;

import java.util.Arrays;

public class BookManager {
	int size = 0; //책의 총 권수
	int totalPrice = 0;
	final int MAX_SIZE = 100; //책의 최대 권수
	
	Book books[] = new Book[MAX_SIZE]; //객체들의 주소값을 저장하는 배열 생성
	Magazine magazines[] = new Magazine[MAX_SIZE];
	
	//도서 정보 등록
	public void add(Book b) {
		books[size++] = b;
	}
	
	
	//고유번호로 도서정보 삭제
	public void remove(String isbn) {
		
		for(int i = 0; i<size; i++)
			if(isbn.equals(books[i].isbn)) {
				books[i] = books[size - 1];
				books[size - 1] = null;
				size--;
				break;
			}
	}
	
	
	//고유번호로 도서정보 반환
	public Book searchByIsbn(String isbn) {
		for(int i = 0; i<size; i++) {
			if(isbn.equals(books[i].isbn)) {
				return books[i];
			}
		}
		return null;
	}
	
	
	//도서 제목을 포함 검색하여 반환
	public Book[] searchByTitle(String title) {
		Book list[] = new Book[MAX_SIZE];
		int size2 = 0;
		
		for(int i = 0; i<size; i++) {
			if(books[i].title.contains(title)) {
				list[i] = books[i];
			}
		}
	}
	
	
	//잡지 리스트 반환
	public Magazine[] getMagazines() {
		return Arrays.copyOf(magazines, size2);
	}
	
	
	//도서 리스트 반환
	public Book[] getBooks() {
		return Arrays.copyOf(books, size);
	}
	
	
	//전체 도서리스트 반환
	public void getList() {
		Book list[] = getBooks();
		for(Book b:list)
			System.out.println(b);
		
		Magazine list2[] = getMagazines();
		for(Magazine m:list2)
			System.out.println(m);
		}
	
	
	//가격의 총합 반환
	public int getTotalPrice() {
		
		for(int i = 0; i<size; i++)
			totalPrice+=books[i].price;
		
		for(int i = 0; i<size2; i++)
			totalPrice+=magazines[i].price;
		
		return totalPrice;
	}
	
	
	//가격의 총 평균 반환
	public double getPriceAvg() {
		return (double)totalPrice/(size + size2);
	}
}

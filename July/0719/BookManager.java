package objarray;

import java.util.Arrays;

public class BookManager {
	int size = 0; //책의 총 권수
	final int MAX_SIZE = 100; //책의 최대 권수
	
	Book books[] = new Book[MAX_SIZE]; //객체들의 주소값을 저장하는 배열 생성
	
	
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
	
	
	//도서리스트 반환
	public Book[] getList() {
		return Arrays.copyOf(books, size);
	}
	
	
	//고유정보로 도서정보 반환
	public Book searchByIsbn(String isbn) {
		for(int i = 0; i<size; i++) {
			if(isbn.equals(books[i].isbn)) {
				return books[i];
			}
		}
		return null;
	}
}

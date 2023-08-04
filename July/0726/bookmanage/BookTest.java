package bookmanage;

import java.io.IOException;

import bookmanage.Book;
import bookmanage.ISBNNotFoundException;
import bookmanage.Magazine;
import bookmanage.QuantityException;

public class BookTest {
	public static void main(String[] args) {
		BookManagerImpl bm = BookManagerImpl.getInstance();
		
		bm.add(new Book("21424", "Java Pro", "김하나", "jaen.kr", 15000, "Java 기본문법", 10));
		bm.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr", 25000, "Java 응용", 20));
		bm.add(new Book("35355", "분석설계", "소나무", "jaen.kr", 30000, "SW 모델링", 30));
		bm.add(new Magazine("45678", "월간 알고리즘", "홍길동", "jaen.kr", 10000, "1월 알고리즘", 40, 2021,1));
		System.out.println("***************************도서 전체 목록***************************");
	
		for(Book b: bm.getList()) {
			System.out.println(b);
		}

		
		System.out.println("***************************일반 도서 목록***************************");
		
		for(Book b: bm.getBooks()) {
			System.out.println(b);
		}
		
		System.out.println("***************************잡지 목록***************************");
		
		for(Magazine m: bm.getMagazines()) {
			System.out.println(m);
		}
//		
		System.out.println("***************************도서 제목 포함검색: Java***************************");
		
		for(Book b: bm.searchByTitle("Java")) {
			System.out.println(b);
		}
		System.out.println("도서 가격 총합 : "+ bm.getTotalPrice());
		System.out.println("도서 가격 평균 : "+ bm.getPriceAvg());
		
		//도서 판매
		String isbn = "21424";
		int quantity = 11;
		System.out.printf("**********도서판매: %s, %d개**********%n", isbn, quantity);
		try {
				bm.sell(isbn, quantity);
			} catch(QuantityException e) {
		System.out.println(e.getMessage());
			} catch(ISBNNotFoundException e) {
		System.out.println(e.getMessage());
			}
				
		//도서 구매
		String isbn2 = "21424";
		int quantity2 = 10;
		System.out.printf("**********도서구매: %s, %d개**********%n", isbn2, quantity2);
		
		
		//파일 읽어서 도서 리스트 출력하기
		try {
			bm.saveData();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("**********불러온 도서 전체 목록**********");
		try {
			bm.loadData();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("등록된 도서가 없습니다");
		}
		
	}
}

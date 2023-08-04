package bookarray;

public class BookTest {

	public static void main(String[] args) {
	
		BookManager bm = new BookManager();
		
		//도서 객체 등록
		bm.add(new Book("21424", "Java Pro", "김하나", "jaen.kr", 15000, "Java 기본 문법"));
		bm.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr", 25000, "Java 응용"));
		bm.add(new Book("35355", "분석설계", "소나무", "jaen.kr", 30000, "SW 모델링"));
		bm.add(new Magazine("45678","월간 알고리즘","홍길동","jaen.kr",10000,"1월 알고리즘",2021,1));
		
		
		//도서 전체 목록 반환
		System.out.println("**********도서 전체 목록**********");
		bm.getList();
		
		
		//도서 목록 반환
		System.out.println("**********일반 도서 목록**********");
		Book list[] = bm.getBooks();
		for(Book b : list)
			System.out.println(b);
		
		
		//잡지 목록 반환
		System.out.println("**********잡지 목록**********");
		Magazine list2[] = bm.getMagazines();
		for(Magazine m : list2)
			System.out.println(m);
		
		
		//도서 제목 포함 검색
		String title ="Java";
		System.out.printf("**********도서조회: %s**********\n", title);
		bm.searchByTitle(title);
		
		
		//도서 총 가격, 평균 출력
		System.out.println("도서 가격 총합 : "+bm.getTotalPrice());
		System.out.printf("도서 가격 평균 : %.1f", bm.getPriceAvg());

	}

}

package objarray;

public class BookTest {

	public static void main(String[] args) {
		
		BookManager bm = new BookManager();  //BookManager의 메소드를 쓰기 위해서 bm 생성
		
		//도서 객체 등록
		bm.add(new Book("21424", "Java Pro", "김하나", "jaen.kr", 15000, "Java 기본 문법"));
		bm.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr", 25000, "Java 응용"));
		bm.add(new Book("35355", "분석설계", "소나무", "jaen.kr", 30000, "SW 모델링"));
		
		
		//도서목록 반환
		Book list[] = bm.getList(); //getList 반환값이 Book[]배열 이므로
		System.out.println("**********도서목록**********");
		for(Book b:list)
			System.out.println(b);
		
		
		//고유번호로 도서정보 조회
		String isbn = "21424";
		Book book = bm.searchByIsbn(isbn); //searchByIsbn 반환값이 도서목록 하나 이므로  Book bk;
		System.out.printf("**********도서조회: %s**********\n", isbn);
		System.out.println(book);
		
		
		//고유번호로 도서정보 삭제 후 조회
		String isbn2 = "21424";
		bm.remove(isbn2);
		
		System.out.printf("**********도서삭제: %s**********\n", isbn2);
		System.out.println("**********도서목록**********");
		Book list2[] = bm.getList();
		for(Book b : list2)
			System.out.println(b);

	}

}

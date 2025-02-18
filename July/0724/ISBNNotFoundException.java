package step3;

class ISBNNotFoundException extends Exception {

	/**	존재하지 않는 도서 고유번호 */
	private String isbn;
	
	/** 고유번호를 받아 생성하는 생성자 */
	public ISBNNotFoundException(String isbn) {
		super(isbn+"고유번호는 존재하지 않습니다.");
		this.isbn = isbn;
	}
	/**
	 * 존재하지 않는 도서 고유번호를 반환한다.
	 * @return 존재하지 않는 도서 고유번호
	 */
	public String getIsbn() {
		return isbn;
	}
}
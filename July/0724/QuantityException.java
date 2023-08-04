package step3;

class QuantityException extends Exception {

	public QuantityException() {
		super("수량이 부족합니다.");
	}
}
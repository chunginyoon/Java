package monthlyTest;
public class Test3 {
	public static void main(String[] args) {
		int input = 10000; //투입금액
		int price = 2530; //상품금액
		int exchange = input - price; //거스름돈
		int[] coin = new int[5]; //1000원, 500원, 100원, 50원, 10원의 개수를 저장하는 배열
		
		//1000원 개수 구하기
		coin[0] = exchange/1000;
		exchange %= 1000;
		
		//500원 개수 구하기
		coin[1] = exchange/500;
		exchange %= 500;
		
		//100원 개수 구하기
		coin[2] = exchange/100;
		exchange %= 100;
		
		//50원 개수 구하기
		coin[3] = exchange/50;
		exchange %= 50;
		
		//10원 개수 구하기
		coin[4] = exchange/10;
		
		//출력
		System.out.printf("투입금액 : %d원%n상품금액 : %d원%n거스름돈 : %d원%n", input, price, exchange);
		System.out.println("------------------------");
		System.out.printf("1000원 : %d개%n", coin[0]);
		System.out.printf("500원 : %d개%n", coin[1]);
		System.out.printf("100원 : %d개%n", coin[2]);
		System.out.printf("50원 : %d개%n", coin[3]);
		System.out.printf("10원 : %d개%n", coin[4]);
	}
}
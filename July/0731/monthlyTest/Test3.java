package monthlyTest;
public class Test3 {
	public static void main(String[] args) {
		int input = 10000; //���Աݾ�
		int price = 2530; //��ǰ�ݾ�
		int exchange = input - price; //�Ž�����
		int[] coin = new int[5]; //1000��, 500��, 100��, 50��, 10���� ������ �����ϴ� �迭
		
		//1000�� ���� ���ϱ�
		coin[0] = exchange/1000;
		exchange %= 1000;
		
		//500�� ���� ���ϱ�
		coin[1] = exchange/500;
		exchange %= 500;
		
		//100�� ���� ���ϱ�
		coin[2] = exchange/100;
		exchange %= 100;
		
		//50�� ���� ���ϱ�
		coin[3] = exchange/50;
		exchange %= 50;
		
		//10�� ���� ���ϱ�
		coin[4] = exchange/10;
		
		//���
		System.out.printf("���Աݾ� : %d��%n��ǰ�ݾ� : %d��%n�Ž����� : %d��%n", input, price, exchange);
		System.out.println("------------------------");
		System.out.printf("1000�� : %d��%n", coin[0]);
		System.out.printf("500�� : %d��%n", coin[1]);
		System.out.printf("100�� : %d��%n", coin[2]);
		System.out.printf("50�� : %d��%n", coin[3]);
		System.out.printf("10�� : %d��%n", coin[4]);
	}
}
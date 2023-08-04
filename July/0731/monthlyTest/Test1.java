package monthlyTest;
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char ch = sc.next().charAt(0); //문자 입력 받기

		if (ch == '*') { // *을 입력했을 때 삼각형 출력
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < i; j++) {
					System.out.print("  ");
				}

				for (int j = 0; j < 4 - i; j++) {
					System.out.print("* ");
				}
				System.out.printf("%n");
			}
		}

		else { //A를 입력했을 때 삼각형 출력
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < i; j++)
					System.out.print("  ");

				for (int j = 0; j < 9 - 2 * i; j++) {
					System.out.printf("%c ", ch++);
				}
				System.out.printf("%n");
			}
		}

	}

}

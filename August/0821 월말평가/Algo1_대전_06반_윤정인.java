package test;

import java.io.*;

public class Algo1_대전_06반_윤정인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//T 입력
		int T = Integer.parseInt(br.readLine());
		int A = 0; //A,B,C의 사용된 횟수
		int B = 0;
		int C = 0;

		while (T > 0) { // T가 0보다 클때 반복

			if (T >= 300) { // T가 300이상일 때
				if (T % 300 == 0) { // T가 300의 배수이면 몫만큼 A 횟수 증가하고 T=0
					A += T / 300;
					T = 0;
				}

				else { // T가 300의 배수가 아니면 300을 빼고 A횟수+1
					T -= 300;
					A++;
				}
			}

			else if (60 <= T && T < 300) { // T가 60이상 300미만일 때
				if (T % 60 == 0) { // 60의 배수이면 몫만큼 B횟수 증가하고 T=0
					B += T / 60;
					T = 0;
				}

				else { // 60의 배수 아니면 60을 빼고 B횟수+1
					T -= 60;
					B++;
				}
			}

			else { // T가 60보다 작을 때
				if (T % 10 == 0) { // T가 10의 배수이면 몫만큼 C횟수 증가하고 T=0
					C += T / 10;
					T = 0;
				}

				else { // 10의 배수가 아니면 10을 빼고 C횟수+1
					T -= 10;
					C++;
				}
			}
		}

		// while문을 통과하고 나면 T는 0이하의 정수가 되어 있다
		// T=0인 경우 A,B,C의 합으로 만들 수 있는 경우이므로 결과 출력
		// T가 음수인 경우 -1 출력
		if (T == 0)
			System.out.printf("%d %d %d", A, B, C);
		else
			System.out.print(-1);
	}
}

package recursive;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_Problem_17478 {
	
	//앞부분 구현한 재귀함수
	private static void self(int k, int N)
	{
		if (k > N - 1)
			return;

		for (int i = 0; i < 4 * k; i++)
			System.out.printf("_");
		System.out.print("\"재귀함수가 뭔가요?\"n");

		for (int i = 0; i < 4 * k; i++)
			System.out.print("_");
		System.out.print("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");

		for (int i = 0; i < 4 * k; i++)
			System.out.print("_");
		System.out.print("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");

		for (int i = 0; i < 4 * k; i++)
			System.out.print("_");
		System.out.print("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");

		self(k+1, N);
	}
	
	//대답 부분 구현한 재귀함수
	private static void answer(int k, int N)
	{
		if (k < 0)
			return;

		for (int i = 0; i < 4 * k; i++)
			System.out.print("_");
		System.out.print("라고 답변하였지.\n");
		answer(k-1, N);
	}

	public static void main(String[] args) throws IOException{
		
		//N 입력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		//앞 부분 출력
		System.out.print("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		
		self(0, N);

		for (int i = 0; i < 4 * N; i++)
			System.out.print("_");
		System.out.print("\"재귀함수가 뭔가요?\"\n");
		
		//대답 부분 출력
		for (int i = 0; i < 4 * N; i++)
			System.out.print("_");
		System.out.print("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
		answer(N, N);
		
	}
}

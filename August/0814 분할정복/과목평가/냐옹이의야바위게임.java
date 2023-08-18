package test;

import java.io.*;
import java.util.*;

public class Algo1_대전_6반_윤정인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 입력
		int T = Integer.parseInt(br.readLine());

		// 테스트 케이스만큼 실행
		for (int tc = 1; tc <= T; tc++) {
			// N, X, K 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			boolean[] cup = new boolean[N + 1]; // 종이컵에 사탕이 들어있는지 아닌지 판단. 종이컵은 1~N까지
			cup[X] = true; // 처음엔 왼쪽에 X번째에 사탕이 들어있으므로 true

			// K번만큼 컵 스왑 실행
			for (int i = 0; i < K; i++) {
				// A, B 입력받기
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				// a와 b 사탕 위치 바꾸기
				swap(A, B, cup);
			}

			// 출력 저장
			sb.append("#" + tc + " ");
			for (int i = 1; i <= N; i++) // 사탕 위치 탐색
				if (cup[i]) {
					sb.append(i);
					sb.append('\n');
					break;
				}
		}
		// 출력
		System.out.print(sb);
	}

	// A와 B의 사탕 위치를 바꾸는 메소드
	private static void swap(int A, int B, boolean[] cup) {

		if (cup[A]) {// 왼쪽에서 A번째 컵에 사탕이 들어있으면
			cup[A] = false; // A에 있는 사탕을 B로 이동
			cup[B] = true;
		}

		else if (cup[B]) {// 왼쪽에서 B번째 컵에 사탕이 들어있으면
			cup[B] = false; // B에 있는 사탕을 A로 이동
			cup[A] = true;
		}
	}

}

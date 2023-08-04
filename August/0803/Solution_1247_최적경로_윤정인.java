package test;

import java.io.*;
import java.util.*;

public class Solution_1247_최적경로_윤정인 {

	static int N;
	static int[] arr; // 순열 저장할 배열
	static boolean[] visit; // 방문 체크
	static int minDistance; // 최소 거리

	static int officeX; // 회사의 X, Y 좌표
	static int officeY;
	static int homeX; // 집의 X, Y 좌표
	static int homeY;
	static int[] clientX; // 고객의 X, Y 좌표
	static int[] clientY;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			visit = new boolean[N];
			clientX = new int[N];
			clientY = new int[N];
			minDistance = Integer.MAX_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			officeX = Integer.parseInt(st.nextToken());
			officeY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				clientX[i] = Integer.parseInt(st.nextToken());
				clientY[i] = Integer.parseInt(st.nextToken());
			}
			// 실행
			permutation(0);
			System.out.printf("#%d %d\n", tc, minDistance);
		}

	}

	// 고객 순서 순열로 탐색
	private static void permutation(int depth) {

		// 재귀함수 중지하고 결과 출력
		if (depth == N) {
			// 초기값: 회사에서 첫 번째 고객까지의 거리
			int distance = Math.abs(officeX - clientX[arr[0]]) + Math.abs(officeY - clientY[arr[0]]);

			// 고객끼리의 거리
			for (int i = 1; i < N; i++)
				distance += (Math.abs(clientX[arr[i]] - clientX[arr[i - 1]])
						+ Math.abs(clientY[arr[i]] - clientY[arr[i - 1]]));

			// 마지막 고객과 집까지의 거리
			distance += (Math.abs(homeX - clientX[arr[N - 1]]) + Math.abs(homeY - clientY[arr[N - 1]]));

			// 최소거리 찾기
			minDistance = Math.min(minDistance, distance);

			return;
		}

		// 순열
		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true; // 방문
				arr[depth] = i; // 방문한 값 저장
				permutation(depth + 1); // 자식 노드 탐색
				visit[i] = false; // 방문 체크 해제
			}
		}
	}
}

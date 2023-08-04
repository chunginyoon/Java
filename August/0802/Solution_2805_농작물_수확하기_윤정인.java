package test;

import java.io.*;

public class Solution_2805_농작물_수확하기_윤정인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		int[] answer = new int[T];
		int N;
		int[][] farm;
		boolean[][] visit;
		
		/*
		 * 마름모의 꼭짓점 찾기
		 * 꼭짓점 연결해서 마름모 경계선 찾기 => visit을 true로 변경
		 * 각 행당 경계선 시작점과 끝나는 지점을 찾아서 그 이내 영역의 값 모두 더하기
		 */
		
		//테스트 케이스만큼 실행
		for (int testCase = 0; testCase < T; testCase++) {

			//입력
			N = Integer.parseInt(br.readLine());
			farm = new int[N][N];
			visit = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++)
					farm[i][j] = str.charAt(j) - '0';
			}

			//N=1이면 값이 하나
			if (N == 1)
				answer[testCase] = farm[0][0];

			//N이 3 이상인 경우 시행
			else {
				
				// 마름모의 꼭짓점 찾기
				visit[0][N / 2] = true; // 북
				visit[N / 2][0] = true; // 서
				visit[N - 1][N / 2] = true; // 남
				visit[N / 2][N - 1] = true; // 동

				// 북점에서부터 시계 반대방향으로 경계선 탐색 후 경계선의 visit 값 모두 true
				// 1. 북점에서 서점까지 경계선 탐색
				int x = 0;
				int y = N / 2;
				do {
					x++;
					y--;
					visit[x][y] = true;
				} while (y > 0);

				// 2. 서점에서 남점까지 경계선 탐색
				do {
					x++;
					y++;
					visit[x][y] = true;
				} while (x < N - 1);

				// 3. 남점에서 동점까지 경계선 탐색
				do {
					x--;
					y++;
					visit[x][y] = true;
				} while (y < N - 1);

				// 4. 동점에서 북점까지 경계선 탐색
				do {
					x--;
					y--;
					visit[x][y] = true;
				} while (x > 0);

				//각 행 탐색해서 경계선 시작지점과 끝 지점 탐색
				for (int i = 0; i < N; i++) {

					int start = 0;
					int end = 0;

					for (int j = 0; j < N; j++)
						if (visit[i][j]) {
							start = j;
							break;
						}

					for (int j = N - 1; j >= 0; j--)
						if (visit[i][j]) {
							end = j;
							break;
						}

					//경계선 이내 값 모두 더함
					for (int j = start; j <= end; j++)
						answer[testCase] += farm[i][j];
				}
			}

		}

		//출력
		for (int i = 0; i < T; i++)
			System.out.printf("#%d %d\n", i + 1, answer[i]);
	}

}

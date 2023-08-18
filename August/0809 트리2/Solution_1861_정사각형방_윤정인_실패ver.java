package test;
/*
 * 각 방을 사방탐색해서 자신보다 1 큰 값이 있는지 체크
 * 누적합 구하기
 * 시간복잡도: 
 */

import java.io.*;
import java.util.*;

public class Solution_1861_정사각형방_윤정인_실패ver {

	static int[][] matrix;
	static int[][] corrdinateX;
	static int[][] corrdinateY;
	static boolean[][] canMove;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());

			matrix = new int[N][N];
			corrdinateX = new int[N][N];
			corrdinateY = new int[N][N];
			canMove = new boolean[N][N];

			int room = Integer.MAX_VALUE;
			int max = 0;

			// 입력
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++)
					matrix[i][j] = Integer.parseInt(st.nextToken());
			}

			// 실행
			search(N);
			path(N, max, room);

			sb.append("#" + tc + " " + room + " " + max);
			sb.append('\n');
		}

		// 출력
		System.out.print(sb);
	}

	// 사방 탐색해서 이동 가능한지 체크, 다음 좌표 저장
	private static void search(int N) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				for (int d = 0; d < 4; d++) {

					int x = i + dx[d];
					int y = j + dy[d];

					if (x < N && x >= 0 && y < N && y >= 0)
						if (matrix[i][j] + 1 == matrix[x][y]) {
							canMove[i][j] = true; // 상하좌우에 +1인 값이 있어서 이동할 수 있는지 체크
							corrdinateX[i][j] = x; // 다음 이동가능한 값의 좌표 저장
							corrdinateY[i][j] = y;
						}

				}
			}
		}
	}

	// 경로 따라서 이동하고 최대 이동 가능 횟수 구하기
	private static void path(int N, int max, int room) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				int x = i;
				int y = j;
				int count = 1;

				while (canMove[x][y]) {
					x = corrdinateX[x][y];
					y = corrdinateY[x][y];
					count++;
				}

				if (count > max) {
					room = matrix[i][j];
					max = count;
				}

				else if (count == max)
					if (matrix[i][j] < room) {
						room = matrix[i][j];
						max = count;
					}
			}
		}
	}

}


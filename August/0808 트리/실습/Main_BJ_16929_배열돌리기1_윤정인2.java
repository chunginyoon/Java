package test;

import java.io.*;
import java.util.*;

public class Main_BJ_16929_배열돌리기1_윤정인2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] originalMatrix = new int[N][M];
		int[][] nextMatrix = new int[N][M];
		boolean[][] visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				originalMatrix[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int r = 0; r < R; r++) {
			for (int i = 0; i < N / 2; i++) {
				int x = i;
				int y = i;
				char direction = 'S';
				while (true) {
					if (direction == 'S') {
						if (x + 1 >= N || visit[x + 1][y]) {
							visit[x][y] = true;
							direction = 'E';
							nextMatrix[x][y + 1] = originalMatrix[x][y];
							y++;
						} else {
							visit[x][y] = true;
							nextMatrix[x + 1][y] = originalMatrix[x][y];
							x++;
						}
					}

					else if (direction == 'E') {
						if (y + 1 >= M || visit[x][y + 1]) {
							visit[x][y] = true;
							direction = 'N';
							nextMatrix[x - 1][y] = originalMatrix[x][y];
							x--;
						} else {
							visit[x][y] = true;
							nextMatrix[x][y + 1] = originalMatrix[x][y];
							y++;
						}
					}

					else if (direction == 'N') {
						if (x - 1 < 0 || visit[x - 1][y]) {
							visit[x][y] = true;
							direction = 'W';
							nextMatrix[x][y - 1] = originalMatrix[x][y];
							y--;
						} else {
							visit[x][y] = true;
							nextMatrix[x - 1][y] = originalMatrix[x][y];
							x--;
						}
					}

					else {
						visit[x][y] = true;
						nextMatrix[x][y - 1] = originalMatrix[x][y];
						if (visit[x][y - 1])
							break;
						else
							y--;
					}
				}
			}

			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					originalMatrix[i][j] = nextMatrix[i][j];
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				System.out.printf("%d ", originalMatrix[i][j]);
			System.out.println();
		}
	}

}

package test;

import java.io.*;
import java.util.*;

public class Main_BJ_10026_적록색약_윤정인 {

	static char[][] map;
	static int N;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		visited = new boolean[N][N];

		// 맵 입력
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++)
				map[i][j] = str.charAt(j);
		}
		// normal 인 경우
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}

		int normal_cnt = cnt;
		cnt = 0;
		visited = new boolean[N + 1][N + 1];

		// dltonism 인 경우
		// G를 R로 바꿔주고 돌린다.

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'G') {
					map[i][j] = 'R'; // G를 R로 바꿔준다.
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		int abnormal_cnt = cnt;
		System.out.println(normal_cnt + " " + abnormal_cnt);

	}

	public static void dfs(int x, int y) {
		visited[x][y] = true;
		char tmp_char = map[x][y]; // R
		for (int i = 0; i < 4; i++) {
			int nx = x + delta[i][0];
			int ny = y + delta[i][1];

			if (nx >= 0 && ny >= 0 && nx < N && ny < N)
				if (!visited[nx][ny] && map[nx][ny] == tmp_char)
					dfs(nx, ny);
		}
	}
}
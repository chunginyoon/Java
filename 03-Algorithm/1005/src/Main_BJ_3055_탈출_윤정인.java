import java.io.*;
import java.util.*;

public class Main_BJ_3055_탈출_윤정인 {

	static class Pos {
		int r;
		int c;
		int time;

		Pos(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static char[][] map;
	static int endR, endC, R, C;
	static int answer = Integer.MAX_VALUE;
	static Queue<Pos> space = new ArrayDeque<>();
	static Queue<Pos> water = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();

			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == 'S')
					space.offer(new Pos(i, j, 0));

				else if (map[i][j] == 'D') {
					endR = i;
					endC = j;
				}

				else if (map[i][j] == '*')
					water.offer(new Pos(i, j, 0));
			}
		}
	}

	private static void waterExpansion() {

		boolean[][] visited = new boolean[R][C];
		int len = water.size();

		for (int i = 0; i < len; i++) {

			Pos w = water.poll();

			int r = w.r;
			int c = w.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;

				if (visited[nr][nc])
					continue;

				if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
					map[nr][nc] = '*';
					visited[nr][nc] = true;
					water.offer(new Pos(nr, nc, 0));
				}
			}
		}
	}

	private static boolean bfs() {

		boolean[][] visited = new boolean[R][C];

		while (!space.isEmpty()) {

			waterExpansion();

//			System.out.println();
//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++)
//					System.out.printf("%c ", map[i][j]);
//				System.out.println();
//			}
			int len = space.size();

			for (int i = 0; i < len; i++) {

				Pos s = space.poll();

				int r = s.r;
				int c = s.c;
				int time = s.time;

				if (visited[r][c]) // 이미 방문한 지점이면 다시 뽑기
					continue;

				visited[r][c] = true; // 방문 처리
				if (map[r][c] == 'D') // 도착지점에 도착하면 탐색 종료
					break;

				for (int d = 0; d < 4; d++) { // 사방 탐색
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr < 0 || nr >= R || nc < 0 || nc >= C)
						continue;

					if (visited[nr][nc])
						continue;

					if (map[nr][nc] == '.' || map[nr][nc] == 'D')
						space.offer(new Pos(nr, nc, time + 1));
				}
			}
		}
		if (visited[endR][endC])
			return true;
		else
			return false;
	}
}
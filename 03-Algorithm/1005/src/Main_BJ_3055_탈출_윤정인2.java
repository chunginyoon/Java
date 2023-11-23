import java.io.*;
import java.util.*;

public class Main_BJ_3055_탈출_윤정인2 {

	static class Pos {
		int r;
		int c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static char[][] map;
	static int startR, startC, endR, endC;
	static int R, C, cnt;

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

				if (map[i][j] == 'S') {
					startR = i;
					startC = j;
				}

				else if (map[i][j] == 'D') {
					endR = i;
					endC = j;
				}
			}
		}
		bfs();
		System.out.print(cnt);
	}

	private static void bfs() {

		Queue<Pos> pos = new ArrayDeque<>();
		boolean[][] visited = new boolean[R][C];
		cnt = 0;

		pos.offer(new Pos(startR, startC));

		while (!pos.isEmpty()) {
//			System.out.println();
//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++)
//					System.out.printf("%c ", map[i][j]);
//				System.out.println();
//			}

			Pos p = pos.poll();

			int r = p.r;
			int c = p.c;

			
			if (visited[r][c]) // 이미 방문한 지점이면 다시 뽑기
				continue;
			
			visited[r][c] = true; // 방문 처리
			if (map[r][c] == 'D') // 도착지점에 도착하면 탐색 종료
				break;
			cnt++;

			for (int d = 0; d < 4; d++) { // 사방 탐색
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;

				if (visited[nr][nc])
					continue;

				if (map[nr][nc] == '.' || map[nr][nc] == 'D')
					pos.offer(new Pos(nr, nc));
			}
		}
	}
}
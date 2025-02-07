
import java.io.*;
import java.util.*;

/*
 * 말 이동 횟수의 제한과 말 이동 방법으로는 장애물을 뛰어넘을 수 있다는 조건을 생각하며 해결한다.
 * visited[x][y][horseJump] 처럼 3차원 배열을 사용하여 말 이동 횟수를 포함한 방문횟수를 확인한다.
 * 
 * BFS 탐색 시작
 * 1. 현재 위치(curX, curY)에서 원숭이 이동 방법으로 4 방향을 탐색한다. (nx, ny, p.hCnt)
 * 2. 말 이동 횟수(hCnt) 가 아직 남았다면?(K 보다 작다면?)
 * 	=> 현재 위치(curX, curY)에서 말 이동 방법으로 8가지 탐색하고 말 이동 횟수를 1 늘려준다. (nx, ny, p.hCnt + 1)
 * 3. 현재 위치가 목적지(W-1, H-1)이 되면 이동 횟수(p.mCnt)를 리턴하고 종료한다.
 * 모두 탐색을 해도 이동 횟수(p.mCnt)가 리턴되지 않으면 -1을 리턴한다.
 */

public class Main_BJ_말이되고픈원숭이_윤정인 {

	static class Pos {
		int x;
		int y;
		int horseJump; //말 점프 횟수
		int cnt; //이동 횟수

		Pos(int x, int y, int horseJump, int cnt) {
			this.x = x;
			this.y = y;
			this.horseJump = horseJump;
			this.cnt = cnt;
		}
	}

	static int[][] map;
	static boolean[][][] visited; //방문 했는지 체크
	static int W, H;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		visited = new boolean[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		System.out.print(bfs(0, 0, K));
	}

	private static int bfs(int x, int y, int K) {

		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; //원숭이 점프
		int[][] horse = { { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, -2 }, { 2, -1 }, { 1, 2 }, { 2, 1 } }; //말 점프

		Queue<Pos> pos = new ArrayDeque<>();

		pos.offer(new Pos(x, y, 0, 0));
		visited[x][y][0] = true;

		while (!pos.isEmpty()) {
			Pos p = pos.poll();

			int r = p.x;
			int c = p.y;

			if (r == H - 1 && c == W - 1) // 목적지에 도착하면 종료
				return p.cnt;

			// 원숭이처럼 한 칸 씩만 탐색
			for (int d = 0; d < 4; d++) {
				int nr = r + delta[d][0];
				int nc = c + delta[d][1];

				if (nr < 0 || nr >= H || nc < 0 || nc >= W)
					continue;
				if (visited[nr][nc][p.horseJump])
					continue;

				if (map[nr][nc] == 0) {
					visited[nr][nc][p.horseJump] = true;
					pos.offer(new Pos(nr, nc, p.horseJump, p.cnt + 1));
				}

			}

			// 말 점프 횟수 남아 있으면 말 점프로 탐색
			if (p.horseJump < K) {
				for (int h = 0; h < 8; h++) {
					int nr = r + horse[h][0];
					int nc = c + horse[h][1];

					if (nr < 0 || nr >= H || nc < 0 || nc >= W)
						continue;
					if (visited[nr][nc][p.horseJump + 1])
						continue;

					if (map[nr][nc] == 0) {
						visited[nr][nc][p.horseJump + 1] = true;
						pos.offer(new Pos(nr, nc, p.horseJump + 1, p.cnt + 1));
					}
				}
			}
		}
		return -1;
	}
}
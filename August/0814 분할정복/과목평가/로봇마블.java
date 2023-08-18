package test;

import java.io.*;
import java.util.*;

public class Algo2_대전_6반_윤정인 {
	static int N;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 입력
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// N 입력
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			int answer = 0; // 로봇이 총 이동할 수 있는 칸 수

			// 지도 입력
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					map[i][j] = st.nextToken().charAt(0);
			}

			// 지도의 모든 좌표를 탐색해서 A, B, C인 경우 로봇의 이동 횟수를 탐색한다
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 'A') {
						answer += moveA(i, j); // 로봇 A가 이동할 수 있는 횟수
					} else if (map[i][j] == 'B') {
						answer += moveB(i, j); // 로봇 B가 이동할 수 있는 횟수
					} else if (map[i][j] == 'C') {
						answer += moveC(i, j); // 로봇 C가 이동할 수 있는 횟수
					}
				}
			}
			// 출력 저장
			sb.append("#" + tc + " " + answer);
			sb.append('\n');
		}
		// 출력
		System.out.print(sb);
	}

	// 로봇 A의 이동 횟수를 체크하는 메소드
	private static int moveA(int i, int j) {

		int cnt = 0;
		// 로봇 A는 우측으로만 이동 할 수 있으므로 j의 값을 늘려서 탐색한다
		// A의 좌표값이 지도의 경계를 넘지 않고, B,C,벽이 아니면 cnt+1
		j++;
		while (j < N && map[i][j] != 'B' && map[i][j] != 'C' && map[i][j] != 'W') {
			cnt++;
			j++;
		}

		return cnt; // cnt 반환
	}

	// 로봇 B의 이동 횟수를 체크하는 메소드
	private static int moveB(int i, int j) {
		int x = i;
		int y = j;
		int cnt = 0;

		// 로봇 B는 상방, 또는 하방으로만 이동 가능하다
		// B의 좌표값이 지도의 경계를 넘지 않고, A,C,벽이 아니면 cnt+1
		// 상방 탐색
		x--;
		while (x >= 0 && map[x][y] != 'A' && map[x][y] != 'C' && map[x][y] != 'W') {
			cnt++;
			x--;
		}

		// 좌표 초기 설정 후 하방 탐색
		x = i + 1;
		y = j;
		while (x < N && map[x][y] != 'A' && map[x][y] != 'C' && map[x][y] != 'W') {
			cnt++;
			x++;
		}
		return cnt; // cnt 반환
	}

	// 로봇 C의 이동 횟수를 체크하는 메소드
	private static int moveC(int i, int j) {
		int x = i;
		int y = j;
		int cnt = 0;

		// 로봇 C는 대각선으로만 이동 가능
		// C의 좌표값이 지도의 경계를 넘지 않고, A,B,벽이 아니면 cnt+1
		// 좌상 탐색
		x--;
		y--;
		while (x >= 0 && y >= 0 && map[x][y] != 'A' && map[x][y] != 'B' && map[x][y] != 'W') {
			cnt++;
			x--;
			y--;
		}

		// 좌표 초기 설정 후, 우상 탐색
		x = i - 1;
		y = j + 1;

		while (x >= 0 && y < N && map[x][y] != 'A' && map[x][y] != 'B' && map[x][y] != 'W') {
			cnt++;
			x--;
			y++;
		}

		// 좌표 초기 설정 후, 좌하 탐색
		x = i + 1;
		y = j - 1;

		while (x < N && y >= 0 && map[x][y] != 'A' && map[x][y] != 'B' && map[x][y] != 'W') {
			cnt++;
			x++;
			y--;
		}

		// 좌표 초기 설정 후, 우하 탐색
		x = i + 1;
		y = j + 1;

		while (x < N && y < N && map[x][y] != 'A' && map[x][y] != 'B' && map[x][y] != 'W') {
			cnt++;
			x++;
			y++;
		}

		return cnt; // cnt 반환
	}
}

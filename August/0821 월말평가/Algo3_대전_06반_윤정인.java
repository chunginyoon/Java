package test;

import java.io.*;
import java.util.*;

class Empty { // 지워진 칸의 좌표를 저장하는 클래스
	int x;
	int y;
}

public class Algo3_대전_06반_윤정인 {

	static int R;
	static int C;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		Empty e = new Empty(); // 지워진 칸의 좌표 저장
		List<String> block = new ArrayList<>();

		for (int tc = 1; tc <= T; tc++) {
			// 입력
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++)
					map[i][j] = st.nextToken().charAt(0);
			}

			searchSpace(e);
		}
	}

	// 각 좌표를 탐색해서 블록 별로 어디가 지워진 곳인지 찾는 메소드
	private static void searchSpace(Empty e, List<String> block) { // 지워진 칸의 좌표
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {

				if (map[i][j] == '|') { // | 블록인 경우 위 또는 아래가 빈칸이면 지워진 것임
					if (map[i + 1][j] == '.' || map[i - 1][j] == '.') {
						e.y = j;
						if (map[i + 1][j] == '.') {
							e.x = i + 1;
							block.add("U");
						} else {
							e.x = i - 1;
							block.add("D");
						}
					}
				}

				else if (map[i][j] == '-') { // - 블록인 경우 왼쪽 또는 오른쪽인 빈칸이면 지워진 칸
					if (map[i][j - 1] == '.' || map[i][j + 1] == '.') {
						e.x = i;
						if (map[i][j - 1] == '.') {
							e.y = j - 1;
							block.add("R");
						} 
						
						else {
							e.y = j + 1;
							block.add("L");
						}
					}
				}

				else if (map[i][j] == '+') { // +블록인 경우 상하좌우 중 하나가 빈칸이면 지워진 칸
					if (map[i][j - 1] == '.' || map[i][j + 1] == '.' || map[i + 1][j] == '.' || map[i - 1][j] == '.') {
						if (map[i][j - 1] == '.') {
							e.x = i;
							e.y = j - 1;
							block.add("R");
						}

						else if (map[i][j + 1] == '.') {
							e.x = i;
							e.y = j + 1;
							block.add("L");
						}

						else if (map[i + 1][j] == '.') {
							e.x = i + 1;
							e.y = j;
						}

						else {
							e.x = i - 1;
							e.y = j;
						}
					}
				}

				else if (map[i][j] == '1') { // 1 블록인 경우
					if (map[i][j + 1] == '.' || map[i + 1][j] == '.') {

						if (map[i][j + 1] == '.') {
							e.x = i;
							e.y = j + 1;
						}

						else {
							e.x = i + 1;
							e.y = j;
						}

						return '1';
					}
				}

				else if (map[i][j] == '2') { // 2 블록인 경우
					if (map[i - 1][j] == '.' || map[i][j + 1] == '.') {

						if (map[i - 1][j] == '.') {
							e.x = i - 1;
							e.y = j;
						}

						else {
							e.x = i;
							e.y = j + 1;
						}

						return '2';
					}
				}

				else if (map[i][j] == '3') { // 3 블록인 경우
					if (map[i - 1][j] == '.' || map[i][j - 1] == '.') {

						if (map[i - 1][j] == '.') {
							e.x = i - 1;
							e.y = j;
						}

						else {
							e.x = i;
							e.y = j - 1;
						}

						return '3';
					}
				} else if (map[i][j] == '4') { // 4 블록인 경우
					if (map[i + 1][j] == '.' || map[i][j - 1] == '.') {

						if (map[i + 1][j] == '.') {
							e.x = i + 1;
							e.y = j;
						}

						else {
							e.x = i;
							e.y = j - 1;
						}

						return '4';
					}
				}

			}
		}
	}
}

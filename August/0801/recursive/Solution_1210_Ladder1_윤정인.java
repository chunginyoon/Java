package recursive;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1210_Ladder1_윤정인 {

	static int[][] ladder = new int[100][100]; //사다리 값 저장할 배열
	static int[] result = new int[10]; //각 테스트 케이스의 결과값을 저장할 배열
	static int end;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		
		for (int testCase = 0; testCase < 10; testCase++) { //테스트 케이스 10번만큼 실행
			
			// 입력
			int T = Integer.parseInt(bf.readLine());

			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < 100; j++)
					ladder[i][j] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < 100; i++)
				if (ladder[99][i] == 2) {
					end = i;
					break;
				}
			
			result[testCase] = coordinate(end);
		}
		
		for (int i = 0; i < 10; i++)
			System.out.printf("#%d %d%n", i+1, result[i]);

	}

	/*
	 * 좌표 x, y ladder[99][end]에서 탐색 시작: x=99, y=end 좌우 탐색 후 값이 1이면 x, y 좌표 바꾸고
	 * ladder 값도 탐색 완료했단 표시로 2로 바꿈 좌우가 모두 1이 아니면 위로 x=0 될 때까지 시행
	 */

	private static int coordinate(int end) {
		int x = 99;
		int y = end;

		while (x > 0) {
			
			if (y - 1 < 0) { // 좌 경계
				if (ladder[x][y + 1] == 1)
					y++;
				else
					x--;
			}

			else if (y + 1 >= 100) { // 우 경계
				if (ladder[x][y - 1] == 1)
					y--;
				else
					x--;
			}

			else { // 경계값 아닐 때
				if (ladder[x][y + 1] == 1)
					y++;

				else if (ladder[x][y - 1] == 1)
					y--;

				else
					x--;
			}
			
			ladder[x][y] = 2;
		}

		return y;
	}
}
package monthlyTest;

import java.util.Scanner;

public class Test5 {
	/*
	 * 승리하는 돌을 구하고 해당 돌의 가장 왼쪽, 위에 있는 가로, 세로 위치 출력
	 * 
	 * 문제 해결 프로세스
	 * 1. 바둑돌 찾기 => 0이 아니라면 바둑돌
	 * 2. 해당 돌이 오목인지 판단하기
	 * 	a. 해당 돌을 기준으로 4방 탐색(우상, 우, 우하, 하)
	 * 	b. 정해진 방향으로 한 칸씩 이동하며 연속된 돌의 개수 count => 기준 돌의 색과 같은 경우만 count 
	 */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		char[][] board = new char[19][19];
		int[][] d = { { 0, 1 }, { 1, 0 }, { 1, 1 }, { -1, 1 } };

		// 입력
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++)
				board[i][j] = sc.next().charAt(0);

		// 모든 칸에 대해 오목 완성 찾기
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (board[i][j] == '1' || board[i][j] == '2') {
					for (int k = 0; k < 4; k++) {
						int ax = i; // x좌표
						int ay = j; // y좌표
						int cnt = 1; // 일치하는 바둑알의 개수

						// 증가하는 방향 탐색
						while (true) {
							ax += d[k][0];
							ay += d[k][1];
							if (0 <= ax && ax < 19 && 0 <= ay && ay < 19) {
								if (board[i][j] == board[ax][ay])
									cnt++;
								else {
									break;
								}
							} else
								break;
						}
						ax = i;
						ay = j;

						// 증가하는 방향의 반대방향 탐색
						while (true) {
							ax -= d[k][0];
							ay -= d[k][1];
							if (0 <= ax && ax < 19 && 0 <= ay && ay < 19) {
								if (board[i][j] == board[ax][ay])
									cnt++;
								else
									break;

							} else
								break;
						}

						// 같은 오목눈이 5개라면
						if (cnt == 5) {
							System.out.println(board[i][j]);
							System.out.println((i + 1) + " " + (j + 1));
							return;
						}

					}
				}
			}
		}

//				아무도 이기지 않았을 경우
		System.out.println(0);

	}
}

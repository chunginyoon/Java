package test;

import java.io.*;
import java.util.*;
/*
 * 메소드 3개 만들기
 * -박스를 체크하는 메소드
 * -행을 체크하는 메소드
 * -열을 체크하는 메소드
 * 
 * int num[10] 배열을 생성해서 탐색을 한 후, 배열의 요소 중 하나의 값이 2 이상이면 false
 */

public class Solution_1974_스도쿠검증_윤정인 {

	static int[][] sudoku = new int[9][9]; // 스도쿠 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 입력
		int T = Integer.parseInt(br.readLine());

		// 테스트 케이스만큼 실행
		for (int tc = 1; tc <= T; tc++) {

			// 스도쿠 입력
			for (int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++)
					sudoku[i][j] = Integer.parseInt(st.nextToken());
			}

			// 실행
			sb.append("#" + tc + " ");
			if (checkRow()) {
				if (checkCol()) {
					
					if (checkBox())
						sb.append(1 + "\n");
					else
						sb.append(0 + "\n");
				} 
				else
					sb.append(0 + "\n");
			} 
			else
				sb.append(0 + "\n");
		}
		// 출력
		System.out.print(sb);
	}

	// 행에 1~9까지 중복이 있는지 체크하는 메소드
	private static boolean checkRow() {
		for (int i = 0; i < 9; i++) {

			int[] num = new int[10];

			for (int j = 0; j < 9; j++)
				num[sudoku[i][j]]++;

			for (int n = 1; n <= 9; n++)
				if (num[n] >= 2)
					return false;
		}
		return true;
	}

	// 열에 1~9까지 중복이 있는지 체크하는 메소드
	private static boolean checkCol() {
		for (int i = 0; i < 9; i++) {

			int[] num = new int[10];

			for (int j = 0; j < 9; j++)
				num[sudoku[j][i]]++;

			for (int n = 1; n <= 9; n++)
				if (num[n] >= 2)
					return false;
		}
		return true;
	}

	// 박스에 1~9까지 증복이 있는지 체크하는 메소드
	private static boolean checkBox() {
		for (int sr = 0; sr < 9; sr += 3) // 시작 좌표는 (0,0) (0,3) (0,6) (3,0) (3,3) ...
			for (int sc = 0; sc < 9; sc += 3) {

				int[] num = new int[10];

				for (int i = sr; i < sr + 3; i++)
					for (int j = sc; j < sc + 3; j++)
						num[sudoku[i][j]]++;

				for (int n = 1; n <= 9; n++)
					if (num[n] >= 2)
						return false;
			}
		return true;
	}
}
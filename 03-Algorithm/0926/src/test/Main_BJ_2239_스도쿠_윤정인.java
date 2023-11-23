package test;

import java.io.*;
import java.util.*;

public class Main_BJ_2239_스도쿠_윤정인 {

	static int[][] sudoku = new int[9][9]; // 스도쿠 저장할 배열
	static List<int[]> list = new ArrayList<int[]>(); //스도쿠의 빈 칸 좌표를 저장할 리스트

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//입력
		for (int i = 0; i < 9; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = str.charAt(j) - '0';

				//빈칸은 list에 추가
				if (sudoku[i][j] == 0)
					list.add(new int[] { i, j });
			}
		}
		//실행
		fill(0);
	}

	//스도쿠의 빈칸을 채우는 메소드
	public static void fill(int depth) {

		// 기저 조건: depth가 리스트의 크기만큼 되면 재귀 종료하고 출력
		if (depth == list.size()) {
			//결과 출력
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++)
					System.out.print(sudoku[i][j]);
				System.out.println();
			}
			System.exit(0);
		}

		int x = list.get(depth)[0];
		int y = list.get(depth)[1];

		boolean[] check = new boolean[10];

		// 행 체크
		for (int i = 0; i < 9; i++)
			if (sudoku[x][i] != 0)
				check[sudoku[x][i]] = true;

		// 열 체크
		for (int i = 0; i < 9; i++)
			if (sudoku[i][y] != 0)
				check[sudoku[i][y]] = true;

		// 박스 체크
		int startX = (x / 3) * 3;
		int startY = (y / 3) * 3;

		for (int i = startX; i < startX + 3; i++)
			for (int j = startY; j < startY + 3; j++)
				if (sudoku[i][j] != 0)
					check[sudoku[i][j]] = true;

		//1~9 중 선택되지 않은 수를 채운다
		for (int i = 1; i <= 9; i++)
			if (!check[i]) {
				sudoku[x][y] = i;
				fill(depth + 1);
				sudoku[x][y] = 0;
			}
	}
}
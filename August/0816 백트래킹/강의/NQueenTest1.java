package backTracking;

import java.io.*;

/*
 * 같은 행에는 퀸을 놓지 않는 버전
 * 놓아진 퀸의 열번호를 기록하는 버전
 */

public class NQueenTest1 {

	static int N;
	static int[] col;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new int[N + 1]; // 1열부터 사용
		ans = 0; // 가능한 경우의 수

		setQueen(1); // 첫 퀸을 1해에 놓아야 한다
		System.out.print(ans);
	}

	// 해당 퀸을 현재 행에 가능한 모든 곳에 놓아보기
	private static void setQueen(int row) { // row: 퀸을 놓으려는 행

		// 가지치기: 직전까지 놓아진 상태로
		if (!isAvailable(row - 1))
			return;
		// 기저조건
		if (row > N) {
			ans++;
			return;
		}
		// 유도파트
		for (int c = 1; c <= N; c++) { // 1열부터 N열까지 시도
			col[row] = c;
			setQueen(row + 1);
		}
	}

	private static boolean isAvailable(int row) { // 마지막으로 놓아진 퀸의 행
		for (int i = 1; i < row; i++)
			if (col[i] == col[row] || row - i == Math.abs(col[row] - col[i]))
				return false;
		return true;
	}
}

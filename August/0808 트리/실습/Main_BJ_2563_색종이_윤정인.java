package test;

/*
 * boolean 2차원 배열 만들어서 색종이가 차지하는 부분은 true로 바꾸고 cnt 1 증가
 * cnt 반환
 */
import java.io.*;
import java.util.*;

public class Main_BJ_2563_색종이_윤정인 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] visit = new boolean[100][100];
		int cnt = 0;

		for (int tc = 1; tc <= N; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int i = x; i < x + 10; i++)
				for (int j = y; j < y + 10; j++) {
					if (!visit[i][j]) {
						visit[i][j] = true;
						cnt++;
					} else
						continue;
				}
		}
		System.out.print(cnt);
	}
}
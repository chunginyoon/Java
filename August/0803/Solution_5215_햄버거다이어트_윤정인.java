package test;

import java.io.*;
import java.util.StringTokenizer;

/*
 * ingredient[N][2] 배열에 재료 점수, 칼로리 저장
 * NC0~NCN까지 조합 시행 각 재료 칼로리 전부 더해서 totalCal, totalScore 구하기
 * totalCal <=L이면 if(totalScore>maxScore) maxScore=totalScore
 * maxScore 출력
 */

public class Solution_5215_햄버거다이어트_윤정인 {

	static int N;
	static int L;
	static int[][] ingredient; // 재료 맛 점수와 칼로리 저장
	static boolean[] selected;
	static int maxScore = 0; // 최대 점수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			maxScore = 0;

			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			ingredient = new int[N][2];
			selected = new boolean[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredient[i][0] = Integer.parseInt(st.nextToken()); // 맛의 점수
				ingredient[i][1] = Integer.parseInt(st.nextToken()); // 칼로리
			}

			// 실행
			powerSet(0);

			// 출력
			System.out.printf("#%d %d\n", tc, maxScore);
		}

	}

	private static void powerSet(int idx) { // idx = 현재 선택한 원소의 인덱스

		if (idx == N) {
			// 하나의 부분 집합 완성
			int totalScore = 0;
			int totalCal = 0;

			for (int i = 0; i < N; i++) {
				if (selected[i]) {
					int score = ingredient[i][0];
					int cal = ingredient[i][1];
					totalScore += score;
					totalCal += cal;
				}
			}

			if (totalCal <= L)
				if (totalScore > maxScore)
					maxScore = totalScore;
			return;
		}

		// 선택 X
		selected[idx] = false;

		// 다음으로 넘김
		powerSet(idx + 1);

		// 선택
		selected[idx] = true;

		// 다음으로 넘김
		powerSet(idx + 1);
	}

}

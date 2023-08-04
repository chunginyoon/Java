package test;

import java.io.*;
import java.util.*;

public class Solution_4012_요리사_윤정인 {
	static int N;
	static int min; // A음식과 B음식의 시너지 차의 최소값
	static int[][] synergy; // 식재료의 시너지 저장
	static int[] A; // A음식에 들어갈 식재료
	static int[] B; // B음식에 들어갈 식재료

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			synergy = new int[N][N];
			A = new int[N / 2];
			B = new int[N / 2];

			min = Integer.MAX_VALUE;

			// 입력
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					synergy[i][j] = Integer.parseInt(st.nextToken());
			}
			// 실행
			combination(0, 0);
			// 출력
			System.out.printf("#%d %d\n", tc, min);
		}
	}

	// 각 음식의 시너지 구하기
	private static int total(int[] arr) {

		int result = 0;

		for (int i = 0; i < N / 2; i++)
			for (int j = i + 1; j < N / 2; j++)
				result += (synergy[arr[i]][arr[j]] + synergy[arr[j]][arr[i]]);

		return result;
	}

	// N개의 식재료를 절반으로 나누고 시너지 차 최소 구함
	private static void combination(int start, int depth) {
		if (depth == N / 2) {

			boolean[] picked = new boolean[N];
			int index = 0;

			for (int i = 0; i < N / 2; i++) // 총 N개의 인덱스 중 A가 뽑은 인덱스 체크
				picked[A[i]] = true;

			for (int i = 0; i < N; i++) // A가 뽑지 않은 나머지 인덱스는 B에 저장
				if (!picked[i])
					B[index++] = i;

			min = Math.min(min, Math.abs(total(A) - total(B))); // 음식의 시너지 차의 최소값 구하기
			return;
		}

		for (int i = start; i < N; i++) {
			A[depth] = i; // 배열 인덱스 0~N-1 중에 N/2개 뽑기
			combination(i + 1, depth + 1);
		}
	}
}

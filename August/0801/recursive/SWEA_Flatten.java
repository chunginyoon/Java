package recursive;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_Flatten {

	static int[] dump = new int[10];
	static int[] result = new int[10];
	static int[][] height = new int[10][100];

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			dump[i] = Integer.parseInt(bf.readLine());
			StringTokenizer st = new StringTokenizer(bf.readLine());

			for (int j = 0; j < 100; j++)
				height[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < 10; i++) {
			dump(i);
			System.out.printf("#%d %d%n", i + 1, result[i]);
		}

	}

	/*
	 * 최고점 max, 최저점 min 탐색 a. max와 min의 차이가 1이하이면 평탄화 중단 b. a가 아니면 덤프 횟수만큼 평탄화 진행 =>
	 * 최고점 높이 -1, 최저점 높이 +1
	 */

	private static void dump(int testCase) {
		int max = height[testCase][0];
		int min = height[testCase][0];
		int maxIndex = 0;
		int minIndex = 0;

		for (int j = 0; j < dump[testCase]; j++) {

			// 최대값 찾기
			for (int i = 1; i < 100; i++)
				if (height[testCase][i] > max) {
					maxIndex = i;
					max = height[testCase][i];
				}

			// 최소값 찾기
			for (int i = 1; i < 100; i++)
				if (height[testCase][i] < min) {
					minIndex = i;
					min = height[testCase][i];
				}

			// 최고점과 최저점의 높이 차
			result[testCase] = max - min;

			// 최고점과 최저점의 높이 차가 1이하이면 평탄화가 완료된 상태이므로 중단
			if (result[testCase] <= 1)
				break;

			// 평탄화 수행: 최고점 높이 -1, 최저점 높이 +1
			height[testCase][maxIndex]--;
			height[testCase][minIndex]++;

			// 덤프 수행 후 최고점과 최저점의 높이 차

			max = height[testCase][0];
			min = height[testCase][0];
			maxIndex = 0;
			minIndex = 0;

			// 최대값 찾기
			for (int i = 1; i < 100; i++)
				if (height[testCase][i] > max) {
					maxIndex = i;
					max = height[testCase][i];
				}

			// 최소값 찾기
			for (int i = 1; i < 100; i++)
				if (height[testCase][i] < min) {
					minIndex = i;
					min = height[testCase][i];
				}

			// 최고점과 최저점의 높이 차
			result[testCase] = max - min;
		}

	}
}

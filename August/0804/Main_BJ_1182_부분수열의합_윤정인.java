package test;

import java.io.*;
import java.util.*;

public class Main_BJ_1182_부분수열의합_윤정인 {

	static int N;
	static int S;
	static int count; // 합이 S인 부분수열의 개수
	static boolean[] selected; // 원소가 선택되었는지 체크
	static int[] num; // 입력받은 숫자 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		selected = new boolean[N];
		num = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());

		// 시행
		count = 0;
		subSet(0);

		// 출력
		System.out.print(count);

	}

	// 부분수열 뽑기
	private static void subSet(int idx) { // idx = 현재 선택한 원소의 인덱스

		if (idx == N) {
			// 하나의 부분 집합 완성
			int sum = 0; // 부분수열의 합
			boolean isEmptySet = true; // 공집합 체크

			for (int i = 0; i < N; i++)
				if (selected[i]) {
					sum += num[i];
					isEmptySet = false;
				}

			// 공집합 아니고 합이 S일 때
			if (!isEmptySet)
				if (sum == S)
					count++;

			return;
		}

		// 선택 X
		selected[idx] = false;

		// 다음으로 넘김
		subSet(idx + 1);

		// 선택
		selected[idx] = true;

		// 다음으로 넘김
		subSet(idx + 1);
	}
}

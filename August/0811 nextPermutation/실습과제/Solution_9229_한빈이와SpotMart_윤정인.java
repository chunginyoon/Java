package test_0810;

import java.io.*;
import java.util.*;

public class Solution_9229_한빈이와SpotMart_윤정인 {

	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 입력
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			// N,M 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			List<Integer> weight = new ArrayList<Integer>(); // 과자 무게 합 저장
			int[] snack = new int[N]; // 과자 무게
			int[] selected = new int[2]; // 조합으로 뽑힌 과자
			int max = -1; // 과자 무게 합의 최대값

			// 과자 무게 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				snack[i] = Integer.parseInt(st.nextToken());

			//과자 중 2개를 뽑는 조합을 실행하고 총 무게를 구한다
			combination(0, 0, snack, selected, weight);

			//뽑힌 과자의 무게 합이 전부 M보다 초과일 때는 -1 저장
			if (weight.size() == 0) {
				sb.append("#" + tc + " " + max);
				sb.append('\n');
			}

			else { //뽑힌 과자의 무게합이 M 이하일 때
				for (int i = 0; i < weight.size(); i++)
					max = Math.max(max, weight.get(i));

				sb.append("#" + tc + " " + max);
				sb.append('\n');
			}
		}

		//출력
		System.out.print(sb);
	}

	//과자 중에서 2개를 조합으로 뽑고 무게 합을 계산하는 메소드
	private static void combination(int start, int depth, int[] snack, int[] selected, List<Integer> weight) {

		if (depth == 2) {
			int total = selected[0] + selected[1];

			if (total <= M) //무게합이 M 이하일 때만 리스트에 추가
				weight.add(total);

			return;
		}

		for (int i = start; i < N; i++) {
			selected[depth] = snack[i];
			combination(i + 1, depth + 1, snack, selected, weight);
		}
	}

}

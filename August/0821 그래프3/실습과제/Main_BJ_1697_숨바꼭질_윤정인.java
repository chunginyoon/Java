package test;

import java.io.*;
import java.util.*;

public class Main_BJ_1697_숨바꼭질_윤정인 {

	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] dp = new int[100001];

		setDp(dp);
		System.out.print(dp[K]);
	}

	private static void setDp(int[] dp) {

		for (int i = N; i <= 100000; i++) { // N~100000까지

			int[] delta = { -1, 1, i };

			for (int d = 0; d < 3; d++) {
				int x = i + delta[d];
				if (x >= 0 && x <= 100000 && x != N) {
					if (dp[x] != 0)
						dp[x] = Math.min(dp[x], dp[i] + 1);

					else
						dp[x] = dp[i] + 1;
				}
			}
		}

		for (int i = N - 1; i >= 0; i--) { // 0~N-1까지

			int[] delta = { -1, 1, i };

			for (int d = 0; d < 3; d++) {
				int x = i + delta[d];
				if (x >= 0 && x <= 100000 && x != N) {
					if (dp[x] != 0)
						dp[x] = Math.min(dp[x], dp[i] + 1);

					else
						dp[x] = dp[i] + 1;
				}
			}
		}

		for (int i = 0; i <= 100000; i++) { // 0부터 100000까지

			int[] delta = { -1, 1, i };

			for (int d = 0; d < 3; d++) {
				int x = i + delta[d];
				if (x >= 0 && x <= 100000 && x != N)
					dp[x] = Math.min(dp[x], dp[i] + 1);
			}
		}
	}
}

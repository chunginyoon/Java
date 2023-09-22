package test;

import java.io.*;

public class Main_BJ_9095_123더하기_윤정인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] dp = new int[N + 1];
			int answer = dp(N, dp);
			sb.append(answer).append('\n');
		}
		System.out.print(sb);
	}

	private static int dp(int N, int[] dp) {
		if (dp[N] != 0)
			return dp[N];

		if (N == 1)
			return dp[N] = 1;
		else if (N == 2)
			return dp[N] = 2;
		else if (N == 3)
			return dp[N] = 4;

		return dp[N] = dp(N - 1, dp) + dp(N - 2, dp) + dp(N - 3, dp);
	}
}
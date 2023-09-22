package test;

import java.io.*;

public class Main_BJ_11727_2XN타일링2_윤정인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		System.out.printf("%d", dp(N, dp));
	}

	private static int dp(int N, int[] dp) {

		if (dp[N] != 0)
			return dp[N];
		if (N == 1)
			return dp[N] = 1;
		if (N == 2)
			return dp[N] = 3;

		return dp[N] = (2* dp(N - 2, dp) + dp(N - 1, dp)) % 10007;
	}
}
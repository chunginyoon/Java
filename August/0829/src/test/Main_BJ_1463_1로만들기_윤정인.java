package test;

import java.io.*;

public class Main_BJ_1463_1로만들기_윤정인 {

	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
//		Arrays.fill(dp, -1);
//		dp[0]=0;
//		dp[1]=0;
//		dp[2]=1;
//		dp[3]=1;
		System.out.printf("%d", dp(N));
	}

	private static int dp(int N) {

		if (dp[N] != 0)
			return dp[N];

		if (N == 0 || N == 1)
			return dp[N] = 0;

		if (N == 2 || N == 3)
			return dp[N] = 1;

//		int min = Integer.MAX_VALUE;
//		if (N % 3 == 0) min = dp(N / 3);
//		
//		if (N % 2 == 0) min = Math.min(min, dp(N / 2) );
//		return dp[N] = Math.min(min, dp(N-1)) + 1;
		
		
		if (N % 6 == 0)
			return dp[N] = Math.min(Math.min(dp(N / 3),dp(N / 2)),dp(N - 1)) + 1;

		else if (N % 3 == 0)
			return dp[N] = Math.min(dp(N / 3), dp(N - 1)) + 1;

		else if (N % 2 == 0)
			return dp[N] = Math.min(dp(N / 2), dp(N - 1)) + 1;

		else
			return dp[N] = dp(N - 1) + 1;
	}
}
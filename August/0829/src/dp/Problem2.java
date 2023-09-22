package dp;

import java.util.Arrays;

public class Problem2 {

	public static void main(String[] args) {

		int[] dp = new int[7];
		System.out.println(dp(6,dp));
		System.out.print(Arrays.toString(dp));
	}

	private static int dp(int n, int[] dp) {

		if (dp[n] != 0)
			return dp[n];
		if (n == 1)
			return dp[n] = 2;
		if (n == 2)
			return dp[n] = 5;

		return dp[n] = 2 * dp(n - 1, dp) + dp(n - 2, dp);
	}
}

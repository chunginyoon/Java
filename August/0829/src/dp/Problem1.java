package dp;

public class Problem1 {

	static int[][] dp;

	public static void main(String[] args) {
		dp = new int[2][9];

		for (int N = 1; N <= 8; N++)
			for (int color = 0; color < 2; color++)
				dp[color][N] = dp(color, N);
		
		System.out.printf("%d", f(8));
	}

	private static int f(int N) {
		return dp[0][N] + dp[1][N];
	}

	private static int dp(int color, int N) {

		if (N == 1)
			return 1;

		else { //N이 2 이상일 때
			if (color == 0) // 노란색
				return dp[0][N - 1] + dp[1][N - 1];
			else // 파란색
				return dp[0][N - 1];
		}
	}
}
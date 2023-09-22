package test;

import java.io.*;
import java.util.*;

public class Main_Bj_1149_RGB거리_윤정인 {

	static int[][] cost;
	static int[][] dp;
	static int red = 0;
	static int green = 1;
	static int blue = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		dp = new int[N + 1][3];
		cost = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i][red] = Integer.parseInt(st.nextToken());
			cost[i][green] = Integer.parseInt(st.nextToken());
			cost[i][blue] = Integer.parseInt(st.nextToken());
		}

		dp[1][red] = cost[1][red];
		dp[1][green] = cost[1][green];
		dp[1][blue] = cost[1][blue];

		System.out.printf("%d", Math.min(Math.min(dp(N, red), dp(N, green)), dp(N, blue)));
	}

	private static int dp(int N, int color) {
		if (dp[N][color] != 0)
			return dp[N][color];

		if (color == red)
			return dp[N][red] = Math.min(dp(N - 1, green), dp(N - 1, blue)) + cost[N][red];

		else if (color == green)
			return dp[N][green] = Math.min(dp(N - 1, red), dp(N - 1, blue)) + cost[N][green];

		else
			return dp[N][blue] = Math.min(dp(N - 1, red), dp(N - 1, green)) + cost[N][blue];

	}
}
package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_Bj_24416_알고리즘수업_피보나치수1 {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n + 1];
		fib(n);
		fibonacci(n);
		System.out.printf("%d %d", fib(n), n-2);
	}

	private static int fib(int n) { //재귀
		if (n == 1 || n == 2)
			return 1;
		else
			return (fib(n - 1) + fib(n - 2));
	}

	private static int fibonacci(int n) { //dp 이용
		if (dp[n] != 0)
			return dp[n];
		else if (n == 1 || n == 2)
			return 1;
		else {
			dp[n] = fib(n - 1) + fib(n - 2);
			return dp[n];
		}
	}
}
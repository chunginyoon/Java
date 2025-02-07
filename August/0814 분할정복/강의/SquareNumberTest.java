package lecture;

import java.io.*;
import java.util.*;

public class SquareNumberTest {

	static int callCnt1, callCnt2;
	static long X;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Long.parseLong(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		System.out.println(exp1(X, N));
		System.out.println("exp1 호출 횟수: " + callCnt1);
		System.out.println(exp2(X, N));
		System.out.println("exp2 호출 횟수: " + callCnt2);

	}

	// 재귀 : 분할 정복 미적용
	// X^n = X*X^n-1
	// X^n-1=X*X^n-2
	private static long exp1(long x, int n) {
		callCnt1++;
		if (n == 1)
			return x;
		return x * exp1(x, n - 1);
	}

	// 재귀: 분할 정복 적용
	// n: 짝수 X^n = X^n/2*X^n/2
	// n: 홀수 X^n = X^n-1/2*X^n-1/2*X^1
	// n: 홀수 X^n/2 ==>X^n-1/2와 같음
	private static long exp2(long x, int n) {
		callCnt2++;
		if (n == 1)
			return x;
		long y = exp2(x, n / 2);
		return (n % 2 == 0) ? y * y : y * y * x;
	}

}

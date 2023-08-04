package Test;
import java.util.Scanner;

public class Solution {
	
	public static long Combination(int n, int r) {
		long result = 1L;
		long factorial = 1L;
		
		for(int i = n; i > n - r ; i--)
			result*=i;
		
		for(int i = r; i>1; i--)
			factorial*=i;
		
		result/= factorial;
		
		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int result[] = new int[T];
		int n, m;
		
		for(int testCase = 1; testCase <= T; testCase++) {
			
			n = sc.nextInt();
			m = sc.nextInt();
			
			result[testCase - 1] = Combination(m, n);
		}
		
		for(int testCase = 0; testCase < T; testCase++)
			System.out.println(result[testCase]);
		
	}

}

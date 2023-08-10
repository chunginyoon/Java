package test;

import java.util.Arrays;

public class Combination_recursive {

	static int N = 5;
	
	static int[] arr = {1,3,5, 7, 9};
	static int R = 3;
	static int[] picked = new int[R];
	
	/**
	 * 
	 * 1 3 5
	 * 1 3 7
	 * 1 3 9
	 * 1 5 7
	 * 1 5 9
	 * 1 7 9
	 * 3 5 7
	 * @param args
	 */
	public static void main(String[] args) {

		

		
		combination(0, 0);
		
	}
	/**
	 * 숫자하나를 뽑고 다음 숫자 뽑기는 재귀호출로 넘김
	 * @param cnt 현재까지 뽑은 숫자의 개수 or 뽑은 숫자를 담을 인덱스
	 */
	private static void combination(int cnt, int start) {
		
		if(cnt == R) {
			//하나의 조합을 완성
			
			//사용
			System.out.println(Arrays.toString(picked));
			return;
		}
		
		for(int i=start; i<N; i++) {
			picked[cnt] = arr[i];	//숫자 뽑기
			combination(cnt+1, i+1);	//다음 숫자 뽑기는 재귀로 넘김
		}
		
	}
	
}

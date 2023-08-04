package permutation;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Permutation {
	
	static int[] arr = {1, 3, 5, 7, 9};
	static int[] picked;
	static boolean[] isSelected = new boolean[arr.length];
	static int R;
	//isSelected[2] = true 라면?
	//arr[2] 값이 사용중
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		R = Integer.parseInt(bf.readLine());
		picked = new int[R];
		
		System.out.println("8");
		permutation(0);
	}
	
	/*
	 * arr배열에서 요소 하나 뽑기
	 * @param idx 뽑을 숫자를 담을 인덱스 or 현재까지 뽑은 요소의 개수
	 */
	
	private static void permutation(int idx) {
		
		if(idx == R) {
			System.out.println(Arrays.toString(picked));
			return;
		}
		//inductive part(유도파트)
		for(int i = 0; i<arr.length; i++) {
			//중복제거
			if(isSelected[i]) continue;
			
			//요소 뽑기
			picked[idx] = arr[i];
			
			//사용처리
			isSelected[i] = true;
			
			//다음 숫자 뽑기는 재귀에 넘김
			permutation(idx+1);
			
			//다음 숫자를 뽑기 위해서 체크 해제
			isSelected[i] = false;
			
		}
	}
}

package nextPermutation;

import java.io.*;
import java.util.*;

public class PermutationBitTest {

	static int N, R, input[], numbers[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		input = new int[N];
		numbers = new int[R];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			input[i] = Integer.parseInt(st.nextToken());
		
//		long start = System.nanoTime();
		permutation(0, 0);
//		long end = System.nanoTime();
//		System.out.println((end/start)/1_000_000_000.);

	}

	private static void permutation(int cnt, int flag) { //cnt 자리에 들어갈 수를 뽑기
		
		if(cnt==R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i = 0; i<N; i++) {
			//중복 체크
			if((flag & 1<<i)!=0) continue;
			
			//수 선택
			numbers[cnt]=input[i];
			
			//다음자리 수 뽑기
			permutation(cnt+1, flag | 1<<i); //방문 흔적을 되돌릴 필요가 없다
		}
		
	}

}

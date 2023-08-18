package test;

import java.io.*;
import java.util.*;

public class Main_BJ_16435_스네이크버드_윤정인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// N, L 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] height = new int[N];

		// 높이 h 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			height[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(height);

		for (int i = 0; i < N; i++)
			if (L <= height[i])
				L++;

		// 출력
		System.out.print(L);
	}
}

package test;

import java.io.*;
import java.util.*;

public class Main_BJ_6603_로또_윤정인 {
	static int[] picked;
	static int k;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		k = 1;

		//k=1이면 중단
		while (k != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());

			if(k!=0) {
				arr = new int[k];
				picked = new int[6];

				for (int i = 0; i < k; i++)
					arr[i] = Integer.parseInt(st.nextToken());

				Arrays.sort(arr);
				combination(0, 0);
				System.out.println();
			}
		}
	}

	private static void combination(int start, int depth) {
		if (depth == 6) {
			for (int i = 0; i < 6; i++)
				System.out.printf("%d ", arr[picked[i]]);
			System.out.println();
			return;
		}

		for (int index = start; index < k; index++) {
			picked[depth] = index;
			combination(index + 1, depth + 1);
		}
	}

}

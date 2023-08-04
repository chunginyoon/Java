package test;

import java.io.*;
import java.util.*;

public class Test01 {

	static int arr[];
	static int num[];
	static boolean visit[];
	static int N;
	static int R;
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		visit = new boolean[N];
		num = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());
		
		R = Integer.parseInt(br.readLine());
		arr = new int[R];

		permutation(N, R, 0);
		
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
		System.out.println(cnt);
	}

	private static void permutation(int N, int R, int depth) {

		if (depth == R) {
			cnt++;
			for (int i : arr) {
				sb.append(i);
				sb.append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				arr[depth] = num[i];
				permutation(N, R, depth + 1);
				visit[i] = false;
			}
		}
		return;
	}
}
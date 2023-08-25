package test;

import java.io.*;
import java.util.*;

public class Solution_3289_서로소집합_윤정인 {

	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			make(n);
			sb.append("#" + tc + " ");

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int operation = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (operation == 1) {
					if (find(a) == find(b))
						sb.append(1);
					else
						sb.append(0);
				}

				else
					union(a, b);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	private static void make(int n) {
		parents = new int[n + 1];

		for (int i = 1; i <= n; i++)
			parents[i] = i;
	}

	private static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot)
			parents[bRoot] = aRoot;
	}
}

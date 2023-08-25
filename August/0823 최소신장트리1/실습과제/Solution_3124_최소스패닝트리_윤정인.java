package test;

import java.io.*;
import java.util.*;

public class Solution_3124_최소스패닝트리_윤정인 {

	private static class Edge implements Comparable<Edge> {
		int from, to, weight;

		private Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static Edge[] edgeList;
	static int V, E;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edgeList = new Edge[E];

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
			}

			Arrays.sort(edgeList);

			make();

			long result = 0L;
			int cnt = 0;

			for (int i = 0; i < E; i++)
				if (union(edgeList[i].from, edgeList[i].to)) {
					result += edgeList[i].weight;
					if (++cnt == V - 1)
						break;
				}

			sb.append("#" + tc + " " + result);
			sb.append('\n');
		}
		System.out.print(sb);
	}

	private static void make() {
		parents = new int[V + 1];

		for (int i = 1; i <= V; i++)
			parents[i] = i;
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}
}
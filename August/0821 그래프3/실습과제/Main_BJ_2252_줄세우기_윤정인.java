package test;

import java.io.*;
import java.util.*;

public class Main_BJ_2252_줄세우기_윤정인 {

	static int N, M;
	static int[] degrees;
	static List<Integer>[] adjList;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		degrees = new int[N + 1];
		adjList = new List[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());

			adjList[front].add(back);
			degrees[back]++;
		}

		BFS();

		System.out.println(sb);
	}

	private static void BFS() {
		Queue<Integer> q = new ArrayDeque<>();

		// 1. 진입 차수가 0 학생을 모두 큐에 집어넣기
		for (int i = 1; i <= N; i++) {
			if (degrees[i] == 0)
				q.offer(i);
		}

		while (!q.isEmpty()) {
			// 큐에서 학생을 하나 꺼냄
			// 앞에 올 수 있는 학생이 줄을 섰음
			int front = q.poll();

			sb.append(front).append(' ');

			// front 뒤에 서야하는 학생들의 진입차수를 -1 줄임
			for (int to : adjList[front]) {
				// 진입차수 0이 됐다는 것은 현재 수강할 수 있다는 의미이므로 큐에 넣어줌
				if (--degrees[to] == 0)
					q.offer(to);
			}
		}
	}
}

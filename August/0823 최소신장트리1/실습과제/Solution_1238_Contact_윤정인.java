package test;

import java.io.*;
import java.util.*;

public class Solution_1238_Contact_윤정인 {

	static List<Integer> nextNode;
	static int[][] graph;
	static int E;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 10번의 테스트 케이스만큼 실행
		for (int tc = 1; tc <= 10; tc++) {

			// 간선의 개수*2와 시작점을 입력 받는다
			StringTokenizer st = new StringTokenizer(br.readLine());
			E = Integer.parseInt(st.nextToken()) / 2;
			int start = Integer.parseInt(st.nextToken());

			nextNode = new ArrayList<>();
			graph = new int[101][101];

			// 인접 행렬
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				if (to != start)
					graph[from][to] = 1;
			}

			// 처음 시작 노드 추가
			nextNode.add(start);

			// 탐색
			dfs(0);

			// 뽑힌 마지막 노드들 오름차순 정렬
			Collections.sort(nextNode);

			// 최대값 저장
			int result = nextNode.get(nextNode.size() - 1);
			sb.append("#" + tc + " " + result).append('\n');
		}

		// 출력
		System.out.print(sb);
	}

	private static void dfs(int depth) {

		if (isAllLastNode()) // 뽑힌 모든 노드가 마지막 노드이면 재귀 중단
			return;

		if (depth == 100) { // 100번 시행했을 경우 재귀 중단
			return;
		}

		// temp를 시작노드로 탐색해야 하므로 전에 뽑은 다음 노드값 복사
		List<Integer> temp = new ArrayList<>();
		for (int i = 0; i < nextNode.size(); i++)
			temp.add(nextNode.get(i));
		nextNode.clear(); // 다음 노드 새로 뽑아야 하므로 초기화

		for (int i = 0; i < temp.size(); i++) {
			int start = temp.get(i);
			for (int j = 1; j <= 100; j++)
				if (graph[start][j] == 1) { // 다음 노드로 이동
					cutEdge(j); // 뽑힌 노드가 도착점인 간선 모두 제거
					nextNode.add(j); // 다음 노드에 추가
				}
		}
		dfs(depth + 1);
	}

	// 뽑힌 다음 노드가 모두 마지막 노드인지 체크하는 메소드
	private static boolean isAllLastNode() {
		for (int i = 0; i < nextNode.size(); i++)
			for (int j = 1; j <= 100; j++)
				if (graph[nextNode.get(i)][j] == 1)
					return false;
		return true;
	}

	// 뽑힌 노드가 도착점인 간선을 모두 끊는 메소드
	private static void cutEdge(int index) {
		for (int i = 1; i <= 100; i++)
			if (graph[i][index] == 1)
				graph[i][index] = 0;
	}
}
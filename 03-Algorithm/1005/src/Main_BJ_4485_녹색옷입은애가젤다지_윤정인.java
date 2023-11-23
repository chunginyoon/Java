import java.io.*;
import java.util.*;

public class Main_BJ_4485_녹색옷입은애가젤다지_윤정인 {

	static class Vertex { // 정점의 정보를 저장하는 클래스
		int x; // 좌표
		int y;
		int weight; // (0,0)으로부터의 최소 금액

		Vertex(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
	}

	static int N;
	static int[][] map; // 도둑루피
	static int[][] cost; // (0,0)으로부터의 최소 금액

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = 0; // 테스트 케이스 개수

		while (true) {
			N = Integer.parseInt(br.readLine()); // N입력

			if (N == 0) // N=0이면 종료
				break;

			tc++; // N이 0이 아니면 테스트 케이스 증가
			map = new int[N][N];

			// 도둑루피 입력
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			// 최소 금액 찾기
			findRoute();
			// 결과 저장
			sb.append("Problem ").append(tc + ": ").append(cost[N - 1][N - 1]).append('\n');
		}

		// 출력
		System.out.print(sb);
	}

	private static void findRoute() {

		cost = new int[N][N];
		boolean[][] visited = new boolean[N][N]; // 방문 기록 저장
		int[] dr = { -1, 1, 0, 0 }; // 사방 탐색
		int[] dc = { 0, 0, -1, 1 };
		PriorityQueue<Vertex> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight); // 최소금액이 작은 순으로 우선 순위 큐 정렬

		// 모든 좌표의 최소 금액을 최대값으로 설정해준다
		for (int i = 0; i < N; i++)
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		cost[0][0] = map[0][0]; // 시작 정점의 금액 설정

		pq.offer(new Vertex(0, 0, 0)); // 큐에 추가

		while (!pq.isEmpty()) {

			Vertex v = pq.poll(); // 큐에서 하나 뽑기

			int r = v.x; // 해당 정점 좌표 설정
			int c = v.y;

			if (visited[r][c]) // 이미 방문한 정점이면 다시 뽑음
				continue;

			if (r == N - 1 && c == N - 1) // 도착 지점에 도달했으면 반복문 종료
				break;

			visited[r][c] = true; // 정점 방문 처리

			for (int d = 0; d < 4; d++) { // 사방 탐색
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N) // 경계값 체크
					continue;

				if (visited[nr][nc]) // 방문한 정점이면 패스
					continue;

				if (cost[nr][nc] <= cost[r][c] + map[nr][nc]) // 이미 최소 금액으로 설정되어 있으면 패스
					continue;

				cost[nr][nc] = cost[r][c] + map[nr][nc];// 최소 금액 갱신
				pq.offer(new Vertex(nr, nc, cost[nr][nc])); // 큐에 넣기
			}
		}
	}
}
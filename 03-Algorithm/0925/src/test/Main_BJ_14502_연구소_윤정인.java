package test;

import java.io.*;
import java.util.*;

/*
 * 빈 칸과 바이러스가 있는 칸의 좌표를 저장할 클래스 생성
 * 빈칸의 위치는 List<Location> empty 에 저장한다
 * 바이러스의 위치는 Queue<Location> virus 에 저장한다
 * 새로 벽을 세울 empty 리스트의 인덱스 3개는 wall 배열에 저장한다
 * 
 * 
 * 1. 조합으로 새로 벽을 세울 empty 리스트의 인덱스 3개를 뽑아 wall에 저장한다
 * 2. 원본 맵을 복사한다
 * 3. 벽을 세운다 => 대응되는 맵의 값 1로 바꾼다
 * 4. bfs 탐색으로 큐 값을 꺼내 사방탐색을 하여 맵 경계 내에서 빈칸인 값을 찾아 바이러스를 퍼뜨린다(큐가 전부 빌 때까지 시행)
 * 		=> 해당 위치를 큐에 추가
 * 		=> 해당 위치의 맵의 값은 2로 바꾼다
 * 5. 안전 구역의 개수를 세서 최대값을 갱신한다.
 */

public class Main_BJ_14502_연구소_윤정인 {

	static class Location { //특정 지점의 좌표를 저장할 클래스
		int r;
		int c;

		Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, MaxArea = 0;
	static int[] wall = new int[3]; //벽을 새로 세울 곳. empty 리스트의 인덱스를 저장한다
	static int[][] map;
	static List<Location> empty = new ArrayList<>(); //빈칸(값이 0인 칸)의 위치를 저장할 리스트
	static Queue<Location> virus = new ArrayDeque<>(); //바이러스가 있는 곳의 위치를 저장할 큐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		//입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//값이 0인 칸은 벽을 세울 수 있는 후보가 되므로 좌표를 리스트에 추가한다
				if (map[i][j] == 0)
					empty.add(new Location(i, j));
			}
		}
		//실행
		Combination(0, 0);
		
		//출력
		System.out.println(MaxArea);
	}

	//empty 리스트 중에서 3개를 조합으로 뽑는 메소드 => 리스트의 인덱스 저장
	private static void Combination(int start, int depth) {

		//재귀 종료
		if (depth == 3) { //하나의 조합 완성
			int safeArea = bfs(); //안전지역의 개수
			MaxArea = Math.max(MaxArea, safeArea); //최대값 갱신
			return;
		}

		for (int i = start; i < empty.size(); i++) { //뽑을 수 있는 범위는 empty의 크기만큼
			wall[depth] = i; //뽑은 empty 리스트의 인덱스를 wall 배열에 저장한다
			Combination(i + 1, depth + 1);
		}
	}

	//바이러스가 퍼져나가는 것을 탐색하는 메소드 => 사방탐색 사용
	private static int bfs() {

		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int[][] copyMap = new int[N][M]; //원본 맵을 복사하여 사용할 배열

		//새로운 벽 조합을 뽑을 때마다 map이 달라지므로 복사해서 사용한다
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
				
				//바이러스가 들어있는 칸의 위치는 virus 큐에 저장한다
				if (copyMap[i][j] == 2)
					virus.offer(new Location(i, j));
			}

		//벽을 세로 새울 곳은 복사맵의 값을 1로 바꿔서 벽을 세운다
		for (int i = 0; i < 3; i++) {
			int idx = wall[i];
			int r = empty.get(idx).r;
			int c = empty.get(idx).c;

			copyMap[r][c] = 1;
		}

		//virus 큐가 비어있지 않을 때까지 사방 탐색 실행하고, 새로운 바이러스의 위치 큐에 추가
		while (!virus.isEmpty()) {
			Location tmp = virus.poll();

			for (int d = 0; d < 4; d++) { //사방 탐색 실행

				int nr = tmp.r + delta[d][0];
				int nc = tmp.c + delta[d][1];

				//사방 탐색한 위치가 map의 경계 내부이면서 빈칸이면 바이러스를 퍼뜨리고 큐에 좌표를 추가한다
				if (nr < N && nr >= 0 && nc < M && nc >= 0) 
					if (copyMap[nr][nc] == 0) {
						copyMap[nr][nc] = 2;
						virus.offer(new Location(nr, nc));
					}
			}
		}
		//안전구역이 몇 개인지 세고, 그 값을 반환한다
		return count(copyMap);
	}

	//안전구역이 몇 개인지 세는 메소드
	private static int count(int[][] map) {
		int cnt = 0;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] == 0)
					cnt++;

		return cnt;
	}
}
package SamsungSW;

import java.io.*;

/*
 * 모든 나라(좌표) 기준으로 사방 탐색해서 인구 차이가 L이상 R이하이면 연결
 * 연결된 다음 좌표를 기준으로 다시 사방 탐색해서 연결된 국가들 리스트를 만든다
 * 연결된 좌표들은 방문 처리를 하여 완전탐색 경우의 수를 줄인다
 * 
 * 1. 완전 탐색 실행
 * 2. 한 국가의 좌표를 기준으로 사방탐색을 실행하여 연결 여부 확인
 * 3. 연결할 수 있는 국가가 존재하면 리스트에 좌표를 추가한다
 * 4. 그 다음 국가를 기준으로 다시 사방탐색 후 연결 여부 확인, 리스트 추가 => 재귀 사용
 * 5. 더 이상 연결 할 수 없으면 자동으로 재귀는 종료된다
 * 
 * 6. 1~5의 과정을 반복한다
 * 	- 리스트의 size가 2 이상이면 국경을 개방할 수 있으므로 인구 이동을 시행한다
 * 	- 리스트의 size가 1이 될 때까지 반복 실행
 */
import java.util.*;

public class Main_BJ_16234_인구이동 {

	static int N, L, R;
	static boolean canMove; //인구 이동을 할 수 있는지 없는지 상태 저장
	static boolean[][] visited; //이미 탐색을 완료한 나라인지 체크
	static int[][] population; //인구 수 저장
	static List<int[]> connection; //연결된 나라의 좌표를 저장하는 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		population = new int[N][N];

		// 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				population[i][j] = Integer.parseInt(st.nextToken());
		}

		int day = 0; //인구 이동을 며칠 했는지 저장

		//실행
		while (true) {
			canMove = false;
			checkConnection();

			if (!canMove) //인구를 더 이상 이동할 수 없다면 반복문 종료
				break;

			day++; //인구 이동을 할 수 있는 경우면 날짜 증가
		}
		
		//출력
		System.out.println(day);
	}

	private static void checkConnection() {

		visited = new boolean[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (!visited[i][j]) { //아직 방문하지 않은 나라인 경우 탐색 시작

					visited[i][j] = true; //방문 처리
					connection = new ArrayList<int[]>(); //하나로 연결된 나라들의 좌표를 저장할 리스트

					dfs(i, j); //연결된 나라 탐색

					if (connection.size() > 1) { //두 개 이상의 나라가 연결되어 있다면 인구 이동 가능
						canMove = true; //이동 가능함 체크
						move(); //인구 이동
					}
				}
	}

	//dfs 탐색으로 (i,j)좌표 중심으로 사방 탐색을 시행해 연결된 나라들을 탐색하는 메소드
	private static void dfs(int i, int j) {

		int[][] delta = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }; //델타 탐색으로 사방 탐색

		connection.add(new int[] { i, j }); //리스트에 좌표 추가

		//사방 탐색 시작
		for (int d = 0; d < 4; d++) {
			
			int nr = i + delta[d][0];
			int nc = j + delta[d][1];

			//경계값 이내이면서 아직 방문하지 않은 나라인 경우만 연산 시행
			if (nr >= 0 && nr < N && nc >= 0 && nc < N)
				if (!visited[nr][nc]) {
					int diff = Math.abs(population[i][j] - population[nr][nc]); //인구 차 구하기
					if (diff >= L && diff <= R) { //인구 차가 조건을 만족하면 방문 처리 후 재귀 실행
						visited[nr][nc] = true;
						dfs(nr, nc); //새로운 좌표 기준으로 사방 탐색 시행
					}
				}
		}
	}

	//인구를 이동시키는 메소드
	private static void move() {

		int result = 0;

		//연결된 나라들의 인구 평균 구하기
		for (int i = 0; i < connection.size(); i++) {
			int x = connection.get(i)[0];
			int y = connection.get(i)[1];

			result += population[x][y];
		}
		result /= connection.size(); //소수점 버림

		//인구 수 평균으로 새로 인구 갱신
		for (int i = 0; i < connection.size(); i++) {
			int x = connection.get(i)[0];
			int y = connection.get(i)[1];

			population[x][y] = result;
		}
	}
}
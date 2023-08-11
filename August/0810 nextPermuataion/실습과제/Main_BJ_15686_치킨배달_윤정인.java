package test;

/* 폐업시키지 않고 냅둘 치킨집은 1~M개
 * 집 좌표를 저장할 class Home, 치킨집 좌표를 저장할 class Chicken
 * 
 * 1. 완전 탐색해서 집 개수, 집 좌표, 치킨집 개수, 치킨집 좌표 구하기
 * 2. 치킨집이 많으면 많을수록 도시의 치킨 거리는 작아짐
 * 	=> 치킨집은 최대 M개까지 냅둘 수 있으므로 M개일 때가 도시의 치킨 거리가 최소이다
 * 3. 치킨집의 M개의 원소로 이루어진 치킨집 조합과 각 집들의 최소 치킨거리를 구한다
 * 4. 3의 치킨 거리를 모두 더해서 도시의 치킨거리 구하기
 * 5. 2~4반복해서 도시의 치킨거리 최소값 갱신
 * 
 
 * 시간복잡도: 약 13 C 6
 */

import java.io.*;
import java.util.*;

class Home { // 집의 좌표를 저장할 클래스
	int x;
	int y;

	Home(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Chicken extends Home { // 치킨집의 좌표를 저장할 클래스

	Chicken(int x, int y) {
		super(x, y);
	}
}

public class Main_BJ_15686_치킨배달_윤정인 {

	static int N;
	static int M;
	static int[][] map; // 도시의 지도 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// N, M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		// 집, 치킨집, 치킨집 조합의 개수를 알 수 없으므로 리스트 선언
		List<Home> home = new ArrayList<>();
		List<Chicken> chicken = new ArrayList<>();
		List<Integer> distance = new ArrayList<>(); // 도시의 치킨거리 값을 저장할 리스트

		// 도시 지도 입력
		// 집, 치킨집 좌표 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1) // 집의 좌표 추가
					home.add(new Home(i, j));

				else if (map[i][j] == 2) // 치킨집의 좌표 추가
					chicken.add(new Chicken(i, j));
			}

		}

		// M개의 치킨집을 뽑는 조합 시행
		int[] arr = new int[M]; // 치킨집 중에서 M개를 뽑은 조합 저장
		combination(0, 0, arr, home, chicken, distance);

		// 도시의 최소 치킨 거리값 구하기
		int min = Integer.MAX_VALUE; // 도시의 치킨거리 최소값
		for (int i = 0; i < distance.size(); i++)
			min = Math.min(min, distance.get(i));

		// 출력
		System.out.print(min);
	}

	// 전체 치킨집에서 M개를 조합으로 뽑는 메소드
	private static void combination(int start, int depth, int[] arr, List<Home> home, List<Chicken> chicken,
			List<Integer> distance) {

		if (depth == M) { // 조합이 완성되면 도시의 치킨거리 구하고 distance 리스트에 추가
			distance.add(chickenDistanceOfCity(arr, home, chicken));
			return;
		}

		for (int i = start; i < chicken.size(); i++) { // 재귀
			arr[depth] = i;
			combination(i + 1, depth + 1, arr, home, chicken, distance);
		}
	}

	// 각 집의 최소 치킨거리를 구해서 도시의 치킨거리를 구하는 메소드
	// 각 집마다 M개의 치킨집과의 거리를 구해서 최소 치킨거리 갱신
	private static int chickenDistanceOfCity(int[] arr, List<Home> home, List<Chicken> chicken) {

		int totalDistance = 0; // 도시의 치킨거리

		for (int i = 0; i < home.size(); i++) {

			int min = Integer.MAX_VALUE; // 각 집의 최소 치킨거리

			for (int j = 0; j < M; j++) {
				int dx = Math.abs(home.get(i).x - chicken.get(arr[j]).x);
				int dy = Math.abs(home.get(i).y - chicken.get(arr[j]).y);
				min = Math.min(min, dx + dy);
			}
			totalDistance += min;
		}

		return totalDistance;
	}

}

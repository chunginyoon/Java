package test;

/*
 * 1번 카메라는 방향 경우의 수 4가지
 * 2번 카메라는 방향 경우의 수 2가지
 * 3번 카메라는 방향 경우의 수 4가지
 * 4번 카메라는 방향 경우의 수 4가지
 * 5번 카메라는 방향 경우의 수 1가지
 * 몇 번 카메라인지 나타내는 숫자, 좌표, 카메라의 방향을 저장하는 클래스 생성
 * 카메라의 정보를 저장하는 리스트 생성
 * 
 * 1. 입력 받을 때 카메라를 입력 받으면 리스트에 카메라 정보를 추가한다
 * 2. 카메라의 개수만큼 0,1,2,3 중에서 중복 순열을 뽑는다. 단, 2번 카메라의 경우는 0,1만 뽑을 수 있음
 * 	-각 숫자는 각 카메라의 방향을 의미한다 => 카메라 클래스의 방향 멤버변수에 저장
 * 3. 매 중복순열이 완성될 때마다 클래스 방향 멤버변수 값대로 각 카메라의 방향을 세팅한다
 * 4. 세팅된 카메라의 방향대로 지도에서 각 CCTV가 감시할 수 있는 위치를 탐색하여 #로 표시한다
 * 5. 맵을 완전 탐색하여 사각지대가 몇 개인지 세서 반환
 * 6. 사각지대의 최소값을 갱신한다
 */
import java.io.*;
import java.util.*;

class Camera { // 카메라 번호, 맵에서의 좌표, 방향을 저장하는 클래스
	char num;
	int x;
	int y;
	int direction;

	Camera(char num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
}

public class Main_BJ_15683_감시_윤정인 {

	static int N, M;
	static char[][] map; // 원본 맵
	static char[][] copyMap; // 복사해서 매 시행마다 사용할 맵
	static int min = Integer.MAX_VALUE; // 사각지대의 최소 개수
	static List<Camera> camera = new ArrayList<>(); // 카메라 정보를 저장할 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// N,M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		copyMap = new char[N][M];

		// 맵 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] != '0' && map[i][j] != '6') // 카메라가 입력되면 리스트에 번호, 좌표를 추가한다
					camera.add(new Camera(map[i][j], i, j));
			}
		}

		// 실행
		setDirection(0);

		// 출력
		System.out.print(min);
	}

	// 카메라 개수만큼 중복순열을 뽑는 메소드
	private static void setDirection(int index) {
		if (index == camera.size()) {// 카메라 개수만큼 중복 순열이 완성됐을 때
			init(); // 맵 초기화
			for (int i = 0; i < camera.size(); i++) // 모든 카메라의 방향을 세팅하고 감시 가능한 위치 탐색
				setCamera(i);
			min = Math.min(min, countArea()); // 사각지대 개수를 센 후, 최소 사각지대 개수 갱신
			return;
		}

		// 중복순열 뽑기
		if (camera.get(index).num != '2') { // 2번 카메라인 경우 0과 1중에서 하나의 값을 뽑는다
			for (int i = 0; i < 4; i++) {
				camera.get(index).direction = i; // 클래스의 멤버 변수에 저장
				setDirection(index + 1);
			}
		}

		else { // 2번 카메라가 아닌 경우 0,1,2,3 중에서 하나의 값을 뽑는다
			for (int i = 0; i < 2; i++) {
				camera.get(index).direction = i; // 클래스의 멤버 변수에 저장
				setDirection(index + 1);
			}
		}
	}

	// 맵을 원본맵대로 복사하여 초기화하는 메소드
	private static void init() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				copyMap[i][j] = map[i][j];
	}

	// 카메라의 방향을 세팅하는 메소드
	// 각 카메라의 방향을 monitor 메소드의 매개변수로 사용하여 감시 가능한 위치를 탐색한다
	// monitor 메소드의 delta 배열= { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }
	// 상우하좌-시계방향으로 설정되어 있다
	private static void setCamera(int index) {

		int cameraNum = camera.get(index).num; // 카메라 번호
		int d = camera.get(index).direction; // 카메라 방향, delta 2차원 배열의 행 값을 결정한다

		if (cameraNum == '1') // 1번 카메라
			monitor(index, d); // 설정된 방향으로만 전진

		else if (cameraNum == '2') { // 2번 카메라
			monitor(index, d); // 대칭으로 탐색: d=0,2 or d=1,3
			monitor(index, d + 2);
		}

		else if (cameraNum == '3') { // 3번 카메라. 90도 방향으로 탐색 가능

			if (d < 3) {
				monitor(index, d); // 연속된 행들 => d=0,1 or d=1,2 or d=2,3
				monitor(index, d + 1);
			}

			else { // d=3일 땐 d=0,3
				monitor(index, 0);
				monitor(index, 3);
			}
		}

		else if (cameraNum == '4') { // 4번 카메라

			for (int i = 0; i < 4; i++) // 세 방향으로 탐색
				if (i != d)
					monitor(index, i);
		}

		else { // 5번 카메라. 모든 방향으로 감시 가능해서 방향 값이 의미 없다
			for (int i = 0; i < 4; i++) // 모든 방향 탐색
				monitor(index, i);
		}
	}

	// 카메라가 설정된 방향대로 감시 가능한 위치를 탐색하여 맵에 #로 표시하는 메소드
	private static void monitor(int index, int d) { // 리스트에서 몇 번째 카메라인지, 그 카메라의 방향 값

		// 카메라에 세팅된 방향대로 탐색
		int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상우하좌 시계방향
		int nx = camera.get(index).x + delta[d][0];
		int ny = camera.get(index).y + delta[d][1];

		// 맵의 영역 안이면서 벽을 만나기 전까지 감시 할 수 있음
		while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
			if (copyMap[nx][ny] == '6')
				break;

			if (copyMap[nx][ny] == '0') // 카메라는 통과
				copyMap[nx][ny] = '#';

			nx += delta[d][0];
			ny += delta[d][1];
		}
	}

	// 사각지대의 개수를 구하는 메소드
	private static int countArea() {
		int cnt = 0;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (copyMap[i][j] == '0')
					cnt++;
		return cnt;
	}
}
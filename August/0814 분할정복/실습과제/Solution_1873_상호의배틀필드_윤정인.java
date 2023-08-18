package test;

/*
 * 탱크의 좌표와 방향을 저장할 클래스 생성
 * 지도를 입력받고 탱크의 위치를 파악해 탱크의 좌표와 탱크의 현재 방향을 저장한다
 * 사용자의 명령을 입력받고 명령을 수행한다
 * 
 * <문제 해결 프로세스>
 * 1. map[H][W]을 입력받고, 탱크의 위치 탐색 후 Tank 클래스에 탱크의 좌표, 방향 정보 저장
 * 2. 사용자의 명령 입력 받기
 * 3-1. 사용자의 명령이 S가 아닐 경우 ==> Tank 클래스의 정보를 이용하여 탐색
 * 	-명령이 U이면 지도의 상방 1칸 탐색 후 평지이면 이동한다
 * 	-명령이 D이면 지도의 하방 1칸 탐색 후 평지이면 이동한다
 * 	-명령이 L이면 지도의 좌측 1칸 탐색 후 평지이면 이동한다
 * 	-명령이 R이면 지도의 우측 1칸 탐색 후 평지이면 이동한다
 * 3-2. 사용자의 명령이 S일 경우 ==> Tank 클래스의 정보를 이용하여 탐색
 * 	-탱크의 현재 방향대로 벽이 나올때까지 or 지도의 경계까지 탐색한다
 * 	-강철 벽이 존재하면 아무일도 일어나지 않는다
 * 	-벽돌 벽이 존재하면 평지로 바꿔준다
 * 	-경계까지 벽이 존재하지 않으면 아무일도 일어나지 않는다
 * 4. 출력
 */

import java.io.*;
import java.util.*;

class Tank { // 탱크의 좌표와 현재 방향을 저장할 클래스
	int x;
	int y;
	char direction;
}

public class Solution_1873_상호의배틀필드_윤정인 {

	static int H;
	static int W;
	static char[][] map; // 지도의 값 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 입력
		int T = Integer.parseInt(br.readLine());

		// 테스트 케이스만큼 실행
		for (int tc = 1; tc <= T; tc++) {
			// H, W 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			// 지도 생성
			map = new char[H][W];

			// 탱크 클래스 생성
			Tank tank = new Tank();

			// 지도 입력
			for (int i = 0; i < H; i++) {
				String str = br.readLine();

				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);

					// 지도의 값 중에서 탱크의 위치를 찾았을 경우
					if (map[i][j] == '>' || map[i][j] == '<' || map[i][j] == '^' || map[i][j] == 'v') {

						// 탱크 클래스에 탱크의 좌표 저장
						tank.x = i;
						tank.y = j;

						// 현재 초기 방향 저장
						if (map[i][j] == '>')
							tank.direction = '>';
						else if (map[i][j] == '<')
							tank.direction = '<';
						else if (map[i][j] == '^')
							tank.direction = '^';
						else
							tank.direction = 'v';
					}
				}

			}

			// 사용자의 명령 입력
			int N = Integer.parseInt(br.readLine());
			String command = br.readLine();

			// 명령 N번만큼 실행
			// 명령이 S가 아닌 경우 탱크의 방향 정보를 갱신하고 사방탐색 중 하나를 결정하고, move 메소드를 실행한다
			// d=0(상방), d=1(하방), d=2(좌측), d=3(우측) 탐색
			// 명령이 S인 경우 shoot 메소드를 실행한다
			for (int i = 0; i < N; i++) {
				if (command.charAt(i) == 'U') { // 명령이 U인 경우
					tank.direction = '^';
					int d = 0; // 상방 탐색
					move(tank, d);
				} else if (command.charAt(i) == 'D') { // 명령이 D인 경우
					tank.direction = 'v';
					int d = 1; // 하방 탐색
					move(tank, d);
				} else if (command.charAt(i) == 'L') { // 명령이 L인 경우
					tank.direction = '<';
					int d = 2; // 좌측 탐색
					move(tank, d);
				} else if (command.charAt(i) == 'R') { // 명령이 R인 경우
					tank.direction = '>';
					int d = 3; // 우측 탐색
					move(tank, d);
				} else { // 명령이 S인 경우
					shoot(tank);
				}
			}
			// 출력 저장
			sb.append("#" + tc + " ");

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++)
					sb.append(map[i][j]);

				sb.append('\n');
			}
		}
		// 출력
		System.out.print(sb);
	}

	// 명령이 S가 아닌 경우, 이동하고 지도의 정보를 갱신하는 메소드
	private static void move(Tank tank, int d) { // tank 정보가 들어있는 클래스, 사방탐색 중 하나를 뜻하는 매개변수
		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 순서대로 상,하,좌,우 탐색

		int nx = tank.x + delta[d][0];
		int ny = tank.y + delta[d][1];

		map[tank.x][tank.y] = tank.direction; // 지도에서 현재 탱크의 방향 바꿔주기

		if (nx >= 0 && nx < H && ny >= 0 && ny < W)
			if (map[nx][ny] == '.') { // 현재 방향대로 한 칸 앞으로 전진이 가능하면

				map[tank.x][tank.y] = '.'; // 현재 칸은 평지로 바꿔주고
				map[nx][ny] = tank.direction; // 다음 칸에는 탱크가 이동한다

				tank.x = nx; // 탱크의 좌표 갱신
				tank.y = ny;
			}
	}

	// 명령이 S인 경우, 탱크의 현재 방향대로 벽이 나올때까지 or 지도의 경계까지 탐색하고 지도의 정보를 갱신하는 메소드
	private static void shoot(Tank tank) {
		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 순서대로 상,하,좌,우 탐색
		int d = 0; // 사방 탐색 중 하나 결정

		// 현재 탱크의 위치와 같은 방향으로 탐색 방향 결정
		if (tank.direction == 'U')
			d = 0;
		else if (tank.direction == 'D')
			d = 1;
		else if (tank.direction == 'L')
			d = 2;
		else
			d = 3;

		// 탐색 시작
		int nx = tank.x + delta[d][0];
		int ny = tank.y + delta[d][1];

		while (nx >= 0 && nx < H && ny >= 0 && ny < W) { // 현재 탱크가 경계값이 아닌 경우
			// 벽이 존재하지 않으면 자동으로 탐색 종료
			if (map[nx][ny] == '*') { // 벽돌 벽이 존재하면 평지로 바꿔주고 반복문 종료
				map[nx][ny] = '.';
				break;
			} else if (map[nx][ny] == '#') // 강철 벽이 존재하면 반복문 종료
				break;

			nx += delta[d][0];
			ny += delta[d][1];
		}
	}

}

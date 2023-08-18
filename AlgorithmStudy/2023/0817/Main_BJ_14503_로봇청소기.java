package week4;

import java.io.*;
import java.util.*;

class Robot { // 로봇의 현재 좌표와 바라보는 방향을 저장할 클래스
	int x; // 행
	int y; // 열
	int d; // 방향
}

public class Main_BJ_14503_로봇청소기 {

	static int[][] room; // 방의 정보를 저장할 배열
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Robot robot = new Robot(); // 로봇 클래스 생성

		// N,M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];

		// 로봇의 초기 좌표와 방향 입력
		st = new StringTokenizer(br.readLine());
		robot.x = Integer.parseInt(st.nextToken());
		robot.y = Integer.parseInt(st.nextToken());
		robot.d = Integer.parseInt(st.nextToken());

		// 방의 값 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				room[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // d = 0 ~ 3까지 차례대로 북동남서
		int clean = 0;

		while (room[robot.x][robot.y] != 1) { // 로봇이 벽에 닿지 않을 때까지 반복

			if (room[robot.x][robot.y] == 0) { // 현재 로봇이 있는 곳이 청소가 안되어 있는 경우
				clean++;
				room[robot.x][robot.y] = 2; // 청소 완료한 공간은 2로 바꿔준다
			}

			if (isClean(robot)) { // 현재 로봇이 위치한 곳에서 사방탐색한 곳이 전부 청소됐을 때
				robot.x -= delta[robot.d][0]; // 현재 방향으로 한 칸 후진
				robot.y -= delta[robot.d][1];
			}

			else {// 현재 로봇이 위치한 곳에서 사방탐색한 구역 중에 청소가 안 된 곳이 있을 때
					// 반시계 방향 90도 회전
				if (robot.d == 0)
					robot.d = 3;
				else
					robot.d--;

				int nx = robot.x + delta[robot.d][0];
				int ny = robot.y + delta[robot.d][1];

				if (room[nx][ny] == 0) { // 바라보는 방향으로 한 칸 앞이 청소가 안 되어 있으면 한 칸 전진
					robot.x = nx;
					robot.y = ny;
				}
			}
		}
		// 출력
		System.out.print(clean);
	}

	// 상하좌우가 모두 청소 완료됐는지 확인하는 메소드
	// 리턴 값이 true이면 상하좌우가 모두 청소 완료된 것
	private static boolean isClean(Robot robot) {
		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우

		for (int d = 0; d < 4; d++) { // 사방탐색
			int nx = robot.x + delta[d][0];
			int ny = robot.y + delta[d][1];

			if (nx >= 0 && nx < N && ny >= 0 && ny < M)
				if (room[nx][ny] == 0) {
					return false;
				}
		}
		return true;
	}
}
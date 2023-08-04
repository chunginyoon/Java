package test;

import java.io.*;

public class Solution_1954_달팽이숫자_윤정인 {

	static boolean[][] visit; //방문한 위치 체크
	static int[][] arr; //결과값 저장
	static int[] N;
	static int cnt = 1; //실행 횟수 카운트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테스트 케이스 입력
		int T = Integer.parseInt(br.readLine());
		N = new int[T];
		
		//입력
		for (int testCase = 0; testCase < T; testCase++)
			N[testCase] = Integer.parseInt(br.readLine());

		//실행
		for (int i = 0; i < T; i++) {

			visit = new boolean[N[i]][N[i]];
			arr = new int[N[i]][N[i]];

			//N=1인 경우 결과값은 1
			if (N[i] == 1)
				System.out.printf("#%d\n1\n", i + 1);

			//N이 1보다 큰 경우만 실행
			else {
				System.out.printf("#%d\n", i + 1);
				snail(N[i]);
			}

		}

	}

	private static void snail(int N) {
		int x = 0; //좌표
		int y = 0;
		char direction = 'E'; //시계방향으로 진행하며 동서남북 (EWSN) 사용. 처음엔 무조건 동쪽 방향이므로 E
		
		//시작점 (0,0)
		visit[0][0] = true;
		cnt = 1;
		arr[0][0] = 1;

		//실행 횟수가 N*N이 될 때까지 실행
		while (cnt < N * N) {
			if (direction == 'E') { //현재 진행 방향이 동쪽일 때
				if (y + 1 >= N || visit[x][y+1]) { //경계값이거나 현재 진행 방향으로 다음에 만날 값을 이미 방문했으면
					direction = 'S'; //방향을 남쪽으로 전환
					x++;
					visit[x][y] = true;
					arr[x][y] = ++cnt;
				}

				else { //경계값이 아니거나 현재 진행 방향으로 다음에 만날 값을 방문한 적이 없으면 방향 유지
					y++;
					visit[x][y] = true;
					arr[x][y] = ++cnt;
				}
			}

			//동, 남, 서쪽 방향은 매커니즘 전부 똑같음
			else if (direction == 'S') { //현재 진행 방향이 남쪽일 때
				if (x + 1 >= N || visit[x+1][y]) {
					direction = 'W';
					y--;
					visit[x][y] = true;
					arr[x][y] = ++cnt;
				}

				else {
					x++;
					visit[x][y] = true;
					arr[x][y] = ++cnt;
				}
			}

			else if (direction == 'W') { //현재 진행 방향이 서쪽일 때
				if (y - 1 < 0 || visit[x][y-1]) {
					direction = 'N';
					x--;
					visit[x][y] = true;
					arr[x][y] = ++cnt;
				}

				else {
					y--;
					visit[x][y] = true;
					arr[x][y] = ++cnt;
				}
			}

			else { //현재 진행 방향이 북쪽일 때
				if (visit[x - 1][y]) { //현재 진행 방향이 북쪽인데 방향을 바꾸는 경우는 무조건 현재 방향 다음 값을 이미 방문한 경우이므로
					direction = 'E'; //진행 방향 동쪽으로 변경
					y++;
					visit[x][y] = true;
					arr[x][y] = ++cnt;
				}

				else {
					x--;
					visit[x][y] = true;
					arr[x][y] = ++cnt;
				}
			}

		}

		//출력
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++)
				System.out.printf("%d ", arr[i][j]);
			System.out.println();
		}
	}
}

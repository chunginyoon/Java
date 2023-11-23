package SamsungSW;

/*
 * 구름의 좌표를 저장할 클래스 생성
 * 
 * <M번 시행>
 * 1. d, s 입력받기
 * 2. 구름 4개 생성, 좌표 4개 저장
 * 3. 각 구름을 d 방향으로 s번 이동하고 좌표 갱신
 * 4. 각 구름이 위치한 칸의 water 값 +1
 */
import java.io.*;
import java.util.*;

class Cloud {
	int r;
	int c;

	Cloud(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main_BJ_21610_마법사상어와비바라기 {

	static int N;
	static int[][] water;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		water = new int[N + 1][N + 1];
		List<Cloud> cloud = new ArrayList<>(); // 초기 구름 위치 설정
		cloud.add(new Cloud(N, 1));
		cloud.add(new Cloud(N, 2));
		cloud.add(new Cloud(N - 1, 1));
		cloud.add(new Cloud(N - 1, 2));

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				water[i][j] = Integer.parseInt(st.nextToken());
		}


		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			moveClouds(cloud, d, s);
			
			waterBug(cloud);
			
			makeClouds(cloud);
			
		}
		
		int cnt = 0;
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				if (water[i][j] > 0)
					cnt += water[i][j];

		System.out.print(cnt);
	}

	private static void moveClouds(List<Cloud> cloud, int d, int s) {
		int[][] delta = { { 0, 0 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
				{ 1, -1 } };

		for (int i = 0; i < cloud.size(); i++) {
			int nr = cloud.get(i).r;
			int nc = cloud.get(i).c;
			int cnt = 0;

			while (cnt++ < s) {
				nr += delta[d][0];
				nc += delta[d][1];

				if (nr == 0)
					nr = N;
				else if (nr == N + 1)
					nr = 1;

				if (nc == 0)
					nc = N;
				else if (nc == N + 1)
					nc = 1;
			}

			water[nr][nc]++;

			cloud.get(i).r = nr;
			cloud.get(i).c = nc;
		}
	}

	private static void waterBug(List<Cloud> cloud) {

		int[][] delta = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

		for (int i = 0; i < cloud.size(); i++) {
			int cnt = 0;

			for (int d = 0; d < 4; d++) {
				int nr = cloud.get(i).r + delta[d][0];
				int nc = cloud.get(i).c + delta[d][1];

				if (nr >= 1 && nr <= N && nc >= 1 && nc <= N)
					if (water[nr][nc] > 0)
						cnt++;
			}
			water[cloud.get(i).r][cloud.get(i).c] += cnt;
		}
	}

	private static void makeClouds(List<Cloud> cloud) {
		List<Cloud> copy = new ArrayList<>();
		copy.addAll(cloud);
		cloud.clear();

		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++) {

				boolean isPossible = true;

				if (water[i][j] >= 2) {
					for (int c = 0; c < copy.size(); c++)
						if (i == copy.get(c).r && j == copy.get(c).c) {
							isPossible = false;
							break;
						}
				}

				else
					isPossible = false;

				if (isPossible) {
					cloud.add(new Cloud(i, j));
					water[i][j] -= 2;
				}
			}
	}
}
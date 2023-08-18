package week4;

import java.io.*;
import java.util.*;

class Thing { //물건의 무게와 가치를 저장할 클래스
	int w; //무게
	int v; //가치
}

public class Main_BJ_12865_평범한배낭 {

	static Thing[] thing;
	static int N;
	static int K;
	static int maxV = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//입력
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		thing = new Thing[N];
		int[][] dp = new int[N + 1][K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			thing[i] = new Thing();
			thing[i].w = Integer.parseInt(st.nextToken());
			thing[i].v = Integer.parseInt(st.nextToken());
		}

		setDP(dp, thing);
		//출력
		System.out.print(dp[N][K]);
	}

	private static void setDP(int[][] dp, Thing[] thing) { // 열 기준

		for (int j = 1; j <= K; j++) { // 수용 가능한 최대 무게
			for (int i = 1; i <= N; i++) { // i-1은 탐색한 인덱스의 마지막 값
				if (thing[i - 1].w > j) // 현재 물건의 무게가 최대 무게를 넘어서면
					dp[i][j] = dp[i - 1][j]; //이전의 dp값 활용
				
				else { //현재 물건의 무게가 최대 무게 이하이면
					int w = j - thing[i - 1].w; //최대 무게-현재 무게
					//이전 dp 값과 무게의 차의 dp값+현재의 가치 비교해서 큰 값으로 갱신
					dp[i][j] = Math.max(dp[i - 1][j], thing[i - 1].v + dp[i - 1][w]);
				}
			}
		}
	}
}
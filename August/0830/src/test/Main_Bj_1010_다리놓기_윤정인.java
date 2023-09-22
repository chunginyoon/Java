package test;

import java.io.*;
import java.util.*;

public class Main_Bj_1010_다리놓기_윤정인 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] B = new int[M + 1][M + 1];

			for (int i = 0; i <= M; i++)
				for (int j = 0, end = Math.min(i, N); j <= end; j++) {
					if (j == 0 || i == j)
						B[i][j] = 1;
					else
						B[i][j] = B[i - 1][j - 1] + B[i - 1][j];
				}
			sb.append(B[M][N]).append('\n');
		}
		System.out.print(sb);
	}

}

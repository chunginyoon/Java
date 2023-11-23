package SamsungSW;

import java.io.*;
import java.util.*;

public class Main_BJ_21608_상어초등학교 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] student = new int[N + 1][4];

		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++)
				student[num][j] = Integer.parseInt(st.nextToken());
		}
	}
}

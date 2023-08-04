package test;

import java.io.*;
import java.util.*;

public class Solution_1247_�������_������ {

	static int N;
	static int[] arr;
	static boolean[] visit;
	static int minDistance;
	static int officeX;
	static int officeY;
	static int homeX;
	static int homeY;
	static int[] clientX;
	static int[] clientY;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			visit = new boolean[N];
			clientX = new int[N];
			clientY = new int[N];
			minDistance = Integer.MAX_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			officeX = Integer.parseInt(st.nextToken());
			officeY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				clientX[i] = Integer.parseInt(st.nextToken());
				clientY[i] = Integer.parseInt(st.nextToken());
			}
			// ����
			permutation(0);
			System.out.printf("#%d %d\n", tc, minDistance);
		}

	}

	private static int sumAbs(int x, int y) {
		return Math.abs(x - y);
	}

	private static void permutation(int depth) {

		// ����Լ� �����ϰ� ��� ���
		if (depth == N) {
			int distance = sumAbs(officeX, clientX[arr[0]]) + sumAbs(officeY, clientY[arr[0]]);

			for (int i = 1; i < N; i++)
				distance += (sumAbs(clientX[arr[i]], clientX[arr[i - 1]])
						+ sumAbs(clientY[arr[i]], clientY[arr[i - 1]]));

			distance += (sumAbs(homeX, clientX[arr[N - 1]]) + sumAbs(homeY, clientY[arr[N - 1]]));

			minDistance = Math.min(minDistance, distance);

			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true; // �湮
				arr[depth] = i; // �湮�� �� ����
				permutation(depth + 1); // �ڽ� ��� Ž��
				visit[i] = false; // �湮 üũ ����
			}
		}
	}

}

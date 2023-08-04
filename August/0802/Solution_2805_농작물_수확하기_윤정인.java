package test;

import java.io.*;

public class Solution_2805_���۹�_��Ȯ�ϱ�_������ {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		int[] answer = new int[T];
		int N;
		int[][] farm;
		boolean[][] visit;
		
		/*
		 * �������� ������ ã��
		 * ������ �����ؼ� ������ ��輱 ã�� => visit�� true�� ����
		 * �� ��� ��輱 �������� ������ ������ ã�Ƽ� �� �̳� ������ �� ��� ���ϱ�
		 */
		
		//�׽�Ʈ ���̽���ŭ ����
		for (int testCase = 0; testCase < T; testCase++) {

			//�Է�
			N = Integer.parseInt(br.readLine());
			farm = new int[N][N];
			visit = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++)
					farm[i][j] = str.charAt(j) - '0';
			}

			//N=1�̸� ���� �ϳ�
			if (N == 1)
				answer[testCase] = farm[0][0];

			//N�� 3 �̻��� ��� ����
			else {
				
				// �������� ������ ã��
				visit[0][N / 2] = true; // ��
				visit[N / 2][0] = true; // ��
				visit[N - 1][N / 2] = true; // ��
				visit[N / 2][N - 1] = true; // ��

				// ������������ �ð� �ݴ�������� ��輱 Ž�� �� ��輱�� visit �� ��� true
				// 1. �������� �������� ��輱 Ž��
				int x = 0;
				int y = N / 2;
				do {
					x++;
					y--;
					visit[x][y] = true;
				} while (y > 0);

				// 2. �������� �������� ��輱 Ž��
				do {
					x++;
					y++;
					visit[x][y] = true;
				} while (x < N - 1);

				// 3. �������� �������� ��輱 Ž��
				do {
					x--;
					y++;
					visit[x][y] = true;
				} while (y < N - 1);

				// 4. �������� �������� ��輱 Ž��
				do {
					x--;
					y--;
					visit[x][y] = true;
				} while (x > 0);

				//�� �� Ž���ؼ� ��輱 ���������� �� ���� Ž��
				for (int i = 0; i < N; i++) {

					int start = 0;
					int end = 0;

					for (int j = 0; j < N; j++)
						if (visit[i][j]) {
							start = j;
							break;
						}

					for (int j = N - 1; j >= 0; j--)
						if (visit[i][j]) {
							end = j;
							break;
						}

					//��輱 �̳� �� ��� ����
					for (int j = start; j <= end; j++)
						answer[testCase] += farm[i][j];
				}
			}

		}

		//���
		for (int i = 0; i < T; i++)
			System.out.printf("#%d %d\n", i + 1, answer[i]);
	}

}

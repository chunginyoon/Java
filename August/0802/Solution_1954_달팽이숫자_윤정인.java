package test;

import java.io.*;

public class Solution_1954_�����̼���_������ {

	static boolean[][] visit; //�湮�� ��ġ üũ
	static int[][] arr; //����� ����
	static int[] N;
	static int cnt = 1; //���� Ƚ�� ī��Ʈ

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//�׽�Ʈ ���̽� �Է�
		int T = Integer.parseInt(br.readLine());
		N = new int[T];
		
		//�Է�
		for (int testCase = 0; testCase < T; testCase++)
			N[testCase] = Integer.parseInt(br.readLine());

		//����
		for (int i = 0; i < T; i++) {

			visit = new boolean[N[i]][N[i]];
			arr = new int[N[i]][N[i]];

			//N=1�� ��� ������� 1
			if (N[i] == 1)
				System.out.printf("#%d\n1\n", i + 1);

			//N�� 1���� ū ��츸 ����
			else {
				System.out.printf("#%d\n", i + 1);
				snail(N[i]);
			}

		}

	}

	private static void snail(int N) {
		int x = 0; //��ǥ
		int y = 0;
		char direction = 'E'; //�ð�������� �����ϸ� �������� (EWSN) ���. ó���� ������ ���� �����̹Ƿ� E
		
		//������ (0,0)
		visit[0][0] = true;
		cnt = 1;
		arr[0][0] = 1;

		//���� Ƚ���� N*N�� �� ������ ����
		while (cnt < N * N) {
			if (direction == 'E') { //���� ���� ������ ������ ��
				if (y + 1 >= N || visit[x][y+1]) { //��谪�̰ų� ���� ���� �������� ������ ���� ���� �̹� �湮������
					direction = 'S'; //������ �������� ��ȯ
					x++;
					visit[x][y] = true;
					arr[x][y] = ++cnt;
				}

				else { //��谪�� �ƴϰų� ���� ���� �������� ������ ���� ���� �湮�� ���� ������ ���� ����
					y++;
					visit[x][y] = true;
					arr[x][y] = ++cnt;
				}
			}

			//��, ��, ���� ������ ��Ŀ���� ���� �Ȱ���
			else if (direction == 'S') { //���� ���� ������ ������ ��
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

			else if (direction == 'W') { //���� ���� ������ ������ ��
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

			else { //���� ���� ������ ������ ��
				if (visit[x - 1][y]) { //���� ���� ������ �����ε� ������ �ٲٴ� ���� ������ ���� ���� ���� ���� �̹� �湮�� ����̹Ƿ�
					direction = 'E'; //���� ���� �������� ����
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

		//���
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++)
				System.out.printf("%d ", arr[i][j]);
			System.out.println();
		}
	}
}

package recursive;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_Flatten {

	static int[] dump = new int[10];
	static int[] result = new int[10];
	static int[][] height = new int[10][100];

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			dump[i] = Integer.parseInt(bf.readLine());
			StringTokenizer st = new StringTokenizer(bf.readLine());

			for (int j = 0; j < 100; j++)
				height[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < 10; i++) {
			dump(i);
			System.out.printf("#%d %d%n", i + 1, result[i]);
		}

	}

	/*
	 * �ְ��� max, ������ min Ž�� a. max�� min�� ���̰� 1�����̸� ��źȭ �ߴ� b. a�� �ƴϸ� ���� Ƚ����ŭ ��źȭ ���� =>
	 * �ְ��� ���� -1, ������ ���� +1
	 */

	private static void dump(int testCase) {
		int max = height[testCase][0];
		int min = height[testCase][0];
		int maxIndex = 0;
		int minIndex = 0;

		for (int j = 0; j < dump[testCase]; j++) {

			// �ִ밪 ã��
			for (int i = 1; i < 100; i++)
				if (height[testCase][i] > max) {
					maxIndex = i;
					max = height[testCase][i];
				}

			// �ּҰ� ã��
			for (int i = 1; i < 100; i++)
				if (height[testCase][i] < min) {
					minIndex = i;
					min = height[testCase][i];
				}

			// �ְ����� �������� ���� ��
			result[testCase] = max - min;

			// �ְ����� �������� ���� ���� 1�����̸� ��źȭ�� �Ϸ�� �����̹Ƿ� �ߴ�
			if (result[testCase] <= 1)
				break;

			// ��źȭ ����: �ְ��� ���� -1, ������ ���� +1
			height[testCase][maxIndex]--;
			height[testCase][minIndex]++;

			// ���� ���� �� �ְ����� �������� ���� ��

			max = height[testCase][0];
			min = height[testCase][0];
			maxIndex = 0;
			minIndex = 0;

			// �ִ밪 ã��
			for (int i = 1; i < 100; i++)
				if (height[testCase][i] > max) {
					maxIndex = i;
					max = height[testCase][i];
				}

			// �ּҰ� ã��
			for (int i = 1; i < 100; i++)
				if (height[testCase][i] < min) {
					minIndex = i;
					min = height[testCase][i];
				}

			// �ְ����� �������� ���� ��
			result[testCase] = max - min;
		}

	}
}

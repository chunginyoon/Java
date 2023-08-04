package test;

import java.io.*;
import java.util.*;

public class Main_BJ_1244_����ġ�Ѱ����_������ {

	static int[] light; //����ġ ����

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); //����ġ ����
		light = new int[N + 1];
		int student; //�л� ��
		int gender; //�л��� ����
		int studentsNum; //�л��� ���� ����

		// ����ġ �ʱ� ���� �Է�
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			light[i] = Integer.parseInt(st.nextToken());

		//�л� ���� �Է�
		student = Integer.parseInt(br.readLine());

		for (int i = 0; i < student; i++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken()); // ����
			studentsNum = Integer.parseInt(st.nextToken()); // �л��� ���� ����

			if (N == 1) //N=1�̸� �л� ����ŭ ����ġ ���� ����
				for (int j = 0; j < student; j++)
					switchLight(1);

			else { //N�� 1���� Ŭ ��
				if (gender == 1) { // ���л�
					for (int j = studentsNum; j <= N; j++) //���л��� ���� ������ ����̸� ����ġ ���� ����
						if (j % studentsNum == 0)
							switchLight(j);
				}

				else { // ���л�
					int index = 0;
					switchLight(studentsNum);

					for (int j = 1; j <= N / 2; j++) { //��Ī ã�� ���� ����
						if (studentsNum - j >= 1 && studentsNum + j <= N) {
							if (light[studentsNum - j] == light[studentsNum + j])
								index = j;
							else
								break;
						}
						else
							break;
					}

					if (index != 0) {
						for (int j = 1; j <= index; j++) {
							switchLight(studentsNum + j);
							switchLight(studentsNum - j);
						}
					}
				}
			}

		}

		// ���
		for (int i = 1; i <= N; i++) {
			if (i % 20 == 0)
				System.out.printf("%d\n", light[i]);
			else
				System.out.printf("%d ", light[i]);
		}
	}

	private static void switchLight(int index) { //����ġ ���¸� �ٲٴ� �Լ�
		if (light[index] == 0)
			light[index] = 1;

		else
			light[index] = 0;
	}
}
package monthlyTest;
import java.util.Scanner;

public class Test4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int[] N = new int[T]; //���� ���� ������ �迭
		int cnt; //����� �ʰ��� ������ ���� ���� ���� ������ ����
		double[] result = new double[T]; //������� ������ �迭
		double[] avg = new double[T]; //�� ���� ��հ��� ������ �迭
		int[][] score = new int[T][1000]; //�� ���� �л����� ������ ������ �迭

		// �Է�
		for (int testCase = 1; testCase <= T; testCase++) {

			N[testCase - 1] = sc.nextInt();
			for (int i = 0; i < N[testCase - 1]; i++)
				score[testCase - 1][i] = sc.nextInt();
		}

		
		for (int testCase = 1; testCase <= T; testCase++) {
			
			//�� �� �л����� ������ ��
			for (int i = 0; i < N[testCase - 1]; i++)
				avg[testCase - 1]+=score[testCase - 1][i];
			
			//�� ���� ���
			avg[testCase - 1]/=N[testCase - 1];
			
			//�� �� ����� �ʰ��� ������ ���� ���� �� �ʱ�ȭ
			cnt = 0;
			
			//�� ���� ��պ��� �ʰ��� ������ ���� ������ üũ
			for (int i = 0; i < N[testCase - 1]; i++)
				if(score[testCase - 1][i] > avg[testCase - 1])
					cnt++;
			
			//���� ���ϱ�
			result[testCase - 1] = (double)cnt/N[testCase - 1]*100;
			
			//���
			System.out.printf("#%d %.3f%%%n", testCase, result[testCase - 1]);
			
		}
	}
}

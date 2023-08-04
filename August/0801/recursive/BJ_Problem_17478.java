package recursive;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_Problem_17478 {
	
	//�պκ� ������ ����Լ�
	private static void self(int k, int N)
	{
		if (k > N - 1)
			return;

		for (int i = 0; i < 4 * k; i++)
			System.out.printf("_");
		System.out.print("\"����Լ��� ������?\"n");

		for (int i = 0; i < 4 * k; i++)
			System.out.print("_");
		System.out.print("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.\n");

		for (int i = 0; i < 4 * k; i++)
			System.out.print("_");
		System.out.print("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.\n");

		for (int i = 0; i < 4 * k; i++)
			System.out.print("_");
		System.out.print("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"\n");

		self(k+1, N);
	}
	
	//��� �κ� ������ ����Լ�
	private static void answer(int k, int N)
	{
		if (k < 0)
			return;

		for (int i = 0; i < 4 * k; i++)
			System.out.print("_");
		System.out.print("��� �亯�Ͽ���.\n");
		answer(k-1, N);
	}

	public static void main(String[] args) throws IOException{
		
		//N �Է�
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		//�� �κ� ���
		System.out.print("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.\n");
		
		self(0, N);

		for (int i = 0; i < 4 * N; i++)
			System.out.print("_");
		System.out.print("\"����Լ��� ������?\"\n");
		
		//��� �κ� ���
		for (int i = 0; i < 4 * N; i++)
			System.out.print("_");
		System.out.print("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"\n");
		answer(N, N);
		
	}
}

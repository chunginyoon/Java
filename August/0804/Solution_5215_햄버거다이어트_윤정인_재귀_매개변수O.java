package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5215_햄버거다이어트_윤정인_재귀_매개변수O {

	static int N, L; //����� ��, Į�θ� ����
	static int maxPref; //�ְ� ��ȣ�� ����
	
	static int[][] ingredient; //����� ���� ���� 0:��ȣ�� , 1:Į�θ�
	
	public static void main(String[] args) throws IOException {
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			maxPref = 0; //�ְ� ��ȣ�� �ʱ�ȭ
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());	//����
			L = Integer.parseInt(st.nextToken());	//Į�θ� ����
			
			ingredient = new int[N][2];
			
			//����� ���� �Է�
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredient[i][0] = Integer.parseInt(st.nextToken());
				ingredient[i][1] = Integer.parseInt(st.nextToken());
			}
			
			subSet(0, 0, 0);
			
			System.out.println("#"+tc+" "+maxPref);
		}

	}
	/**
	 * ����� ���� �޼ҵ�
	 * @param cnt ����/���� ������ ����� �ε���
	 * @param score ������� ���õ� ����� ����
	 * @param calorie ������� ���õ� ����� Į�θ�
	 */
	private static void subSet(int cnt, int score, int calorie) {
		//���� ġ��
		//������� ���õ� ��ᰡ �̹� ���� Į�θ��� �Ѿ�ٸ�? ���� �ε����� �����ʹ� Ȯ���� �ʿ䰡 ����
		if(calorie>L) return;
		
		//���� ����
		//step 01. N-1�ε��������� ��� ��� ó�� �Ϸ� ��, �ܹ��� ����� ������ �ϼ���
		if(cnt==N) {
			
			//step 03. ��Į�θ��� L�����̰� �ܹ��� ��ȣ���� �ִ��̶�� ����
			maxPref = Math.max(score, maxPref);
			
			return;
		}
		
		
		
		
		//cnt�� �ε����� ��Ҹ� ����
		//���õ� ����̱� ������ score�� calorie �� ���� ��Ű��
		subSet(cnt+1, score+ingredient[cnt][0] , calorie+ingredient[cnt][1]);	//���� �ε����� ��� ������ ����Լ� ȣ��
		
		//cnt�� �ε����� ��Ҹ� ����
		//���õ��� ���� ����̹Ƿ� score�� calorie�� ����
		subSet(cnt+1, score, calorie);
		
	}

	
}


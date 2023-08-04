package test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5215_햄버거다이어트_윤정인_재귀_매개변수X {

	static int N, L; //����� ��, Į�θ� ����
	static int maxPref; //�ְ� ��ȣ�� ����
	
	static int[][] ingredient; //����� ���� ���� 0:��ȣ�� , 1:Į�θ�
	static boolean[] isSelected;	//����� ���� ���� ���� - true:����, false:����
	
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
			
			isSelected = new boolean[N];
			
			subSet(0);
			
			
			System.out.println("#"+tc+" "+maxPref);
		}

	}
	/**
	 * ����� ���� �޼ҵ�
	 * @param cnt ����/���� ������ ����� �ε���
	 */
	private static void subSet(int cnt) {
		
		//���� ����
		//step 01. N-1�ε��������� ��� ��� ó�� �Ϸ� ��, �ܹ��� ����� ������ �ϼ���
		if(cnt==N) {
			
			//step 02. �ܹ����� ��ȣ���� �� Į�θ� ���ϱ�
			int score = 0; 	//�ܹ��� ��ȣ��
			int kcal = 0;	//�� Į�θ�
			for(int i=0; i<N; i++) {
				if(isSelected[i]) {
					score += ingredient[i][0];
					kcal += ingredient[i][1];
				}
			}
			
			
			//step 03. ��Į�θ��� L�����̰� �ܹ��� ��ȣ���� �ִ��̶�� ����
			if(kcal <= L) maxPref = Math.max(score, maxPref);
			
			return;
		}
		
		
		//cnt�� �ε����� ��Ҹ� ����
		isSelected[cnt] = true;
		subSet(cnt+1);	//���� �ε����� ��� ������ ����Լ� ȣ��
		
		//cnt�� �ε����� ��Ҹ� ����
		isSelected[cnt] = false;
		subSet(cnt+1);
		
	}

	
}

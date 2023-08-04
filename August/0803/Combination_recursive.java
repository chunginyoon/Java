package test;

import java.util.Arrays;

public class Combination_recursive {

	static int N = 5;
	
	static int[] arr = {1,3,5, 7, 9};
	static int R = 3;
	static int[] picked = new int[R];
	
	/**
	 * 
	 * 1 3 5
	 * 1 3 7
	 * 1 3 9
	 * 1 5 7
	 * 1 5 9
	 * 1 7 9
	 * 3 5 7
	 * @param args
	 */
	public static void main(String[] args) {

		

		
		combination(0, 0);
		
	}
	/**
	 * �����ϳ��� �̰� ���� ���� �̱�� ���ȣ��� �ѱ�
	 * @param cnt ������� ���� ������ ���� or ���� ���ڸ� ���� �ε���
	 */
	private static void combination(int cnt, int start) {
		
		if(cnt == R) {
			//�ϳ��� ������ �ϼ�
			
			//���
			System.out.println(Arrays.toString(picked));
			return;
		}
		
		for(int i=start; i<N; i++) {
			picked[cnt] = arr[i];	//���� �̱�
			combination(cnt+1, i+1);	//���� ���� �̱�� ��ͷ� �ѱ�
		}
		
	}
	
}

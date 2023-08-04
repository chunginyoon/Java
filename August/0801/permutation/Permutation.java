package permutation;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Permutation {
	
	static int[] arr = {1, 3, 5, 7, 9};
	static int[] picked;
	static boolean[] isSelected = new boolean[arr.length];
	static int R;
	//isSelected[2] = true ���?
	//arr[2] ���� �����
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		R = Integer.parseInt(bf.readLine());
		picked = new int[R];
		
		System.out.println("8");
		permutation(0);
	}
	
	/*
	 * arr�迭���� ��� �ϳ� �̱�
	 * @param idx ���� ���ڸ� ���� �ε��� or ������� ���� ����� ����
	 */
	
	private static void permutation(int idx) {
		
		if(idx == R) {
			System.out.println(Arrays.toString(picked));
			return;
		}
		//inductive part(������Ʈ)
		for(int i = 0; i<arr.length; i++) {
			//�ߺ�����
			if(isSelected[i]) continue;
			
			//��� �̱�
			picked[idx] = arr[i];
			
			//���ó��
			isSelected[i] = true;
			
			//���� ���� �̱�� ��Ϳ� �ѱ�
			permutation(idx+1);
			
			//���� ���ڸ� �̱� ���ؼ� üũ ����
			isSelected[i] = false;
			
		}
	}
}

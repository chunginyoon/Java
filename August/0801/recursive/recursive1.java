package recursive;

public class recursive1 {

	public static void main(String[] args) {
		
		int n=1;
		
		/*while(n<=5)
			System.out.println(n);*/
			
		
		recursive(n);
		
	}
	
	/*
	 * n���� �� �� ��� �ϰ� ���� ���� ����� ��ͷ� �ѱ�
	 */
	
	private static void recursive(int n) {
		
		//���� ����
		if(n>5) return;
		
		
		//�ݺ� ��Ʈ(inductive part)
		
		
		//n�� ���
		System.out.println(n);
		
		//���� ȣ���� ��� ȣ�⿡ �ñ��
		recursive(n+1);
	}

}

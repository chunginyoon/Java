package recursive;

public class recursive1 {

	public static void main(String[] args) {
		
		int n=1;
		
		/*while(n<=5)
			System.out.println(n);*/
			
		
		recursive(n);
		
	}
	
	/*
	 * n값을 한 번 출력 하고 다음 값의 출력은 재귀로 넘김
	 */
	
	private static void recursive(int n) {
		
		//종료 조건
		if(n>5) return;
		
		
		//반복 파트(inductive part)
		
		
		//n을 출력
		System.out.println(n);
		
		//다음 호출은 재귀 호출에 맡긴다
		recursive(n+1);
	}

}

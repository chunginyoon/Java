package test;

public class Combination {

	static int N = 5;
	
	static int[] arr = {1,3,5, 7, 9};
	static int R = 3;
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

		
		for(int i=0; i<N; i++) {//0��° �ڸ� ���� �̱�
			
			for(int j=i+1; j<N; j++) { //1��° �ڸ� ���� �̱�
				
				for(int k=j+1; k<N; k++) {	//2��° �ڸ� ���� �̱�
					
					System.out.println(arr[i]+" "+arr[j]+" "+arr[k]);
					
				}
				
			}
			
		}
		
		
		
	}

}

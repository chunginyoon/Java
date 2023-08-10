package test;

public class PowerSetBinaryCounting {

	static int N = 5;
	static int[] arr = { 1, 3, 5, 7, 9 };

	public static void main(String[] args) {

//		for(int flag = 0; flag <= Math.pow(2,  N)-1; flag++)
		//flag : 각 부분집합의 정보
		for (int flag = 0; flag < 1 << N; flag++) {
			System.out.printf("%5s\n", Integer.toBinaryString(flag));

			// i번째 요소가 선택됐는지
			for (int i = 0; i < N; i++)
				if ((flag & 1<<i) > 0)
					System.out.print(arr[i]);
			
			System.out.println();
				
		}

	}

}

package recursive;

public class recursive2 {
	
	static int[] arr = {1, 3, 5, 7, 9};
	public static void main(String[] args) {
		// TODO Auto-generated method stub\
		int idx = 0;
		/*while(idx!=arr.length) {
			System.out.println(arr[idx]);
			idx++;
		}*/
		
		recursive(0, arr);
	
	}
	
	private static void recursive(int idx, int[] arr) {
		if(idx == arr.length)
			return;
		System.out.println(arr[idx]);
		recursive(idx+1, arr);
	}
	
	private static void recursive1(int i) {
		if(i== arr.length)
			return;
		System.out.println(arr[i]);
		recursive1(i+1);
	}

}

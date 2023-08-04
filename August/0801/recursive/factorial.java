package recursive;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class factorial {
	
	static int result = 1;
	static int n;

	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(bf.readLine());
		
		/*for(int i = 2; i<=n; i++)
			result *=i;*/
	}
	
	public static void factorial(int i) {
		
		if(i>n) {
			System.out.println(result);
			return;
		}
		
		//반복되는 부분
		result*=i;
		factorial(i+1);
	}

}

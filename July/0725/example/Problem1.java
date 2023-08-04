package example;

import java.util.Arrays;

public class Problem1 {
	public static void main(String[] args) {
		//1. double 형 배열을 오름차순으로 정렬한 뒤, 출력해보세요.
		double[] num = {-0.5, 2.10, 30.11, -10.22, 13.44};
		Arrays.sort(num);
		for(double i: num)
        	System.out.print(i+" ");

	}

}

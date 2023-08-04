package example;

import java.util.Arrays;

public class Problem2 {
	public static void main(String[] args) {
		//2. String 배열을 오름차순으로 정렬한 뒤, 출력해보세요.
		String[] strs = {"ㄹ", "ㅇ","ㄷ","ㄴ","ㅎ","ㄲ"};
		Arrays.sort(strs);
		for(String s: strs)
        	System.out.println(s);
	}
}

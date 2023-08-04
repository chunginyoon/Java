package example;

import java.util.Arrays;
import java.util.Collections;

public class Problem3 {
	public static void main(String[] args) {
		//3. String 배열을 내림차순으로 정렬한 뒤 출력해보세요.
		String[] strs = {"ㄹ", "ㅇ","ㄷ","ㄴ","ㅎ","ㄲ"};
		Arrays.sort(strs, Collections.reverseOrder());
		for(String s: strs)
        	System.out.println(s);

	}

}

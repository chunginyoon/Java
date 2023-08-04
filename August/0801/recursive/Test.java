package recursive;

import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int[] array = new int[10];


		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < 10; i++) {
			
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < 10; i++)
			System.out.println(array[i]);
	}

}

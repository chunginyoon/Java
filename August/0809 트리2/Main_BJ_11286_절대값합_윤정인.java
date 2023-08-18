package test;

import java.io.*;
import java.util.*;
/*
 * 시간 복잡도 NlogN
 */

public class Main_BJ_11286_절대값합_윤정인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> { // o1-o2가 오름차순
			int abs1 = Math.abs(o1);
			int abs2 = Math.abs(o2);

			if (abs1 == abs2) {
				return o1 - o2;
			}
			return abs1 - abs2;
		});

		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(br.readLine());
			if (val == 0) {
				if (queue.isEmpty())
					System.out.println("0");
				else
					System.out.println(queue.poll());
			} else {
				queue.add(val);
			}

		}

	}
}

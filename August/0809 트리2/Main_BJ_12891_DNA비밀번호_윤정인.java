package test;

/*
 * 투포인터 사용
 */

import java.io.*;
import java.util.*;

public class Main_BJ_12891_DNA비밀번호_윤정인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[] dna = new int[4];
		int[] cnt = new int[4];
		int answer = 0;
		boolean check = true;

		String str = br.readLine().trim();

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 4; i++)
			dna[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < P; i++) {
			if (str.charAt(i) == 'A')
				cnt[0]++;
			else if (str.charAt(i) == 'C')
				cnt[1]++;
			else if (str.charAt(i) == 'G')
				cnt[2]++;
			else
				cnt[3]++;
		}
		
		for (int i = 0; i < 4; i++)
			if (cnt[i] < dna[i]) {
				check = false;
				break;
			}

		if (check)
			answer++;

		// 실행
		for (int start = 1; start <= S - P; start++) {
			check = true;
			int end = start + P - 1;

			if (str.charAt(start - 1) == 'A')
				cnt[0]--;
			else if (str.charAt(start - 1) == 'C')
				cnt[1]--;
			else if (str.charAt(start - 1) == 'G')
				cnt[2]--;
			else
				cnt[3]--;

			if (str.charAt(end) == 'A')
				cnt[0]++;
			else if (str.charAt(end) == 'C')
				cnt[1]++;
			else if (str.charAt(end) == 'G')
				cnt[2]++;
			else
				cnt[3]++;

			for (int i = 0; i < 4; i++)
				if (cnt[i] < dna[i]) {
					check = false;
					break;
				}

			if (check)
				answer++;
		}
		// 출력
		System.out.print(answer);
	}

}

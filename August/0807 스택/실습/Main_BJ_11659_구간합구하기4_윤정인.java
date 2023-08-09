package test;
/*
 * num[N]에 입력받은 숫자 저장
 * arr[N]에 0부터 N-1 인덱스까지의 합 전부 저장
 * sum = arr[j-1] - arr[i-1] + num[i-1] => M번 시행
 */

import java.io.*;
import java.util.*;

public class Main_BJ_구간합구하기_윤정인 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] num = new int[N];
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());

		arr[0] = num[0];

		for (int i = 1; i < N; i++)
			arr[i] = arr[i - 1] + num[i];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int sum = arr[end] - arr[start] + num[start];
			System.out.println(sum);
		}

	}

}
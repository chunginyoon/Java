package week4;

import java.io.*;
import java.util.*;

class Rock { //각 돌의 작은 점프와 큰 점프 에너지 저장할 클래스
	int small;
	int big;
}

public class Main_BJ_21317_징검다리건너기 {

	static int N;
	static int K;
	static int min = Integer.MAX_VALUE;
	static int[] arr;
	static Rock[] rock;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력
		N = Integer.parseInt(br.readLine());
		rock = new Rock[N - 1];
		arr = new int[N - 1];

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			rock[i] = new Rock();
			rock[i].small = Integer.parseInt(st.nextToken());
			rock[i].big = Integer.parseInt(st.nextToken());
		}
		K = Integer.parseInt(br.readLine());
		//실행
		dupPerm(0, 3);
		
		//출력
		System.out.print(min);
	}

	//1,2,3중에서 N-1개를 나열하는 중복 순열을 구하는 메소드
	//단, 3은 최대 1번만 사용할 수 있음
	private static void dupPerm(int depth, int end) {
		if (depth == N - 1) {
			if(getEnergy(arr)!=-1)
				min=Math.min(min, getEnergy(arr));
			return;
		}

		for (int i = 1; i <= end; i++) {
			arr[depth] = i;
			if (i == 3)
				end = 2;
			dupPerm(depth + 1, end);
		}

	}

	//에너지의 합 구하는 메소드
	private static int getEnergy(int[] arr) {

		int index = 0;
		int result = 0;

		for (int i = 0; i < N - 1; i++) {
			//1은 작은 점프, 2는 큰 점프, 3은 매우 큰 점프를 의미함
			if (arr[i] == 3)
				result += K;
			else if (arr[i] == 2)
				result += rock[index].big;
			else
				result += rock[index].small;

			index += arr[i]; //뽑은 에너지의 크기만큼 점프해야 하므로 인덱스 이동

			if (index >= N - 1)
				break;
		}

		if (index == N - 1) //마지막 인덱스의 값이 N-1이면 무사히 도착한 것
			return result;
		else //마지막 인덱스의 값이 N-1 초과이면 도착지점을 지나친 것
			return -1;
	}
}
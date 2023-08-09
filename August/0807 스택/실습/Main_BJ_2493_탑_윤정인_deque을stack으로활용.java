package test;
/*
 * <조건>
 * 높이가 서로 다른 N개의 탑 => 높이가 같은 경우 X
 * 각 탑들이 레이저를 왼쪽으로 발사했을 때, 수신하는 탑의 번호 출력
 * 
 * <문제 해결 방법>
 * 1. 완전 탐색 : 현재 탑 이전 탑부터 ~0번까지 탐색하며 더 높은 탑을 발견했을 때 번호를 출력
 * 	시간 복잡도
 * 	-모든 탑들이 자신보다 큰 탑을 찾지 못하는 경우
 * 	-탑의 개수 N이 최대값 500,000인 경우 0+1+2+...+499,999
 * 	-공차가 1인 등차수열 500000*250000 =1250억 => 불가능
 * 2. 탐색의 경우의 수 줄이기
 * 	-모든 탑을 탐색할 필요가 없음
 * 	-비교할 탑의 정보를 저장할 공간 필요 =>비교하는 탑의 정보가 나중에 저장한 것을 먼저 사용해야 하므로 후입선출(LIFO) 즉 스택을 사용해야 한다
 * 
 * <문제 해결 프로세스>
 * 각 탑의 레이저를 수신할 탑 탐색
 * 1. 스택이 비어있는 경우(비교할 탑 정보가 없는 경우)
 * 	-0 출력 후, 스택에 현재 탑 정보 저장
 * 2. 스택이 비어있지 않은 경우(비교할 탑 정보가 존재)
 * 	-탑 정보 하나를 꺼내서 높이 비교
 * 	a. 현재 탑 높이 > 꺼낸 탑 높이(수신할 탑을 찾지 못함)
 * 		-비교중인 탑 정보 스택에 제거, 탑 정보를 하나 꺼내서 탐색을 이어감
 * 	b. 현재 탑 높이 < 비교할 탑 높이(수신할 탑 찾음)
 * 		-비교할 탑 높이를 출력하고, 현재 탑 정보를 스택에 저장
 * 
 * <최적화>
 * -수신할 탑을 탐색할 때, 뒤쪽의 탑 정보를 참조할 필요가 없고, 스택에 저장된 값 이외에는 재사용X
 * 	=> 탑 정보를 따로 저장할 필요 없이 입력값을 바로 사용 가능
 */

import java.io.*;
import java.util.*;

public class Main_BJ_2493_탑_윤정인_deque을stack으로활용 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 탑의 개수

		Deque<int[]> stack = new ArrayDeque<>(); // 0:번호 , 1:높이

		// 탑의 개수 만큼 반복
		StringTokenizer st = new StringTokenizer(br.readLine());
		A: for (int number = 1; number <= N; number++) {
			int height = Integer.parseInt(st.nextToken()); // 현재 탑 높이

			while (!stack.isEmpty()) {
				int[] top = stack.peek(); // 비교할 탑 정보

				// 현재 탑 높이 > 비교할 탑 높이 ( 필요없는 정보 버림)
				if (height > top[1])
					stack.pop();
				// 현재 탑 높이 < 비교할 탑 높이 ( 수신할 탑 찾음 )
				else {
					stack.push(new int[] { number, height }); // 수신 탑 번호 출력
					sb.append(top[0]).append(' '); // 현재 탑 정보 저장
					continue A; // 다음 탑 탐색하러 가기
				}
			}

			// 고려할 탑정보가 없음
			// 스택에 현재 탑 번호,높이 저장 후, 0 출력
			stack.push(new int[] { number, height });
			sb.append(0).append(' ');
		}

		System.out.println(sb);
	}

}

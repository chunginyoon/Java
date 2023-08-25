package test;

/*
 * 업무가 주어졌는지 아닌지, 업무의 점수와 걸리는 시간을 저장할 Work 클래스 생성
 * 입력을 줄 단위로 받을 때
 * 	-첫 번째 토큰 값이 1이면 객체에 true, 점수와 시간을 저장한다
 * 	-첫 번째 토큰 값이ㅏ 0이면 객체에 false, 0,0 을 저장한다
 * 
 * <총 N번 반복>
 * 1. 현재 하고있는 업무의 위치: index
 * 2. 객체의 isFull이 true이면 새로운 업무가 주어진 것이므로 index를 갱신한다
 * 3. work[index].T의 값 -1
 * 4. work[index].T의 값이 0이 되면 이전에 마치지 못한 업무를 탐색해 업무를 수행한다
 */
import java.io.*;
import java.util.*;



public class Algo2_대전_06반_윤정인_Stack사용 {
	
	private static class Work { // 업무의 점수, 걸리는 시간을 저장하는 클래스
		int A; // 업무의 점수
		int T; // 걸리는 시간

		Work(int A, int T) {
			this.A = A;
			this.T = T;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int score = 0; // 얻을 수 있는 총 점수
		Stack<Work> work = new Stack<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			if (st.nextToken().charAt(0) == '1') { // 새로운 업무가 주어진 경우
				int A = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				if (T == 1)
					score += A;
				else
					work.push(new Work(A, T - 1));
			}

			else if (!work.isEmpty()) {// 새로운 업무가 주어지지 않은 경우
				Work temp = work.pop();
				temp.T--;

				if (temp.T == 0)
					score += temp.A;
				else
					work.push(temp);
			}
		}
		// 출력
		System.out.print(score);
	}
}
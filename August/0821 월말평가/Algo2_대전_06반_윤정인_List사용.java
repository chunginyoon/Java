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

class Work { // 업무가 비어있는지 아닌지, 업무의 점수, 걸리는 시간을 저장하는 클래스
	boolean isFull; // isFull이 true이면 업무가 주어진 것이다
	int A; // 업무의 점수
	int T; // 걸리는 시간

	Work(boolean isFull, int A, int T) {
		this.isFull = isFull;
		this.A = A;
		this.T = T;
	}
}

public class Algo2_대전_06반_윤정인_List사용 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Work[] work = new Work[N]; // N개만큼 객체 생성
		int score = 0; // 얻을 수 있는 총 점수
		List<Integer> index = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int isFull = Integer.parseInt(st.nextToken());

			if (isFull == 1) { // 새로운 업무가 주어진 경우
				int A = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				work[i] = new Work(true, A, T);
			}

			else // 새로운 업무가 주어지지 않은 경우
				work[i] = new Work(false, 0, 0);
		}

		int current = 0;

		for (int time = 0; time < N; time++) {

			if (work[time].isFull) {// 새로운 업무가 주어진 경우 index 값 갱신
				index.add(time);
				current = index.get(index.size() - 1);
			}
            else if(index.size()>0) {
            	work[current].T--; // 업무 수행
            	
            	if (work[current].T == 0) { // 현재 업무를 완료했을 때

    				score += work[current].A; // 점수를 더해주고
    				index.remove(current);

    				if (index.size() == 0) // 모든 업무를 완료한 것이므로 반복문 종료
    					break;
    				else // 아직 남은 업무가 있는 경우
    					current = index.get(index.size() - 1);
    			}
            }
		}

		// 출력
		System.out.print(score);
	}
}
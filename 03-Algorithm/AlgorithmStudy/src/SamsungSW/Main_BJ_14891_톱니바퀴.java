package SamsungSW;

/*
 * <비교해야 할 톱니>
 * 1번 톱니바퀴의 2번 톱니/2번 톱니바퀴의 6번 톱니 비교
 * 2번 톱니바퀴의 2번 톱니/3번 톱니바퀴의 6번 톱니 비교
 * 3번 톱니바퀴의 2번 톱니/4번 톱니바퀴의 6번 톱니 비교
 * 
 * <탐색>
 * 1번 톱니 바퀴를 회전 하는 경우, 오른쪽 방향으로만 탐색한다
 * 2번 톱니 바퀴를 회전 하는 경우, 왼쪽 방향으로만 탐색한다
 * 3, 4번 톱니 바퀴를 회전 하는 경우, 양방향으로 탐색한다
 * 연속하는 톱니바퀴의 톱니가 같으면 탐색을 멈춘다
 * 연속하는 톱니바퀴의 톱니가 다르면 서로 반대방향으로 회전한다
 * 
 * <문제 해결 과정>
 * 1. 각 톱니바퀴의 톱니를 입력 받아 리스트에 저장한다(12시 방향을 0번 인덱스로 한다)
 * 2. 회전하는 톱니바퀴를 기준으로 탐색 방향(왼쪽, 오른쪽, 양쪽)을 정하고 연속하는 톱니바퀴의 날을 비교한다
 * 3. 연속하는 톱니바퀴의 날이 같을 때까지 탐색을 진행하여 각 톱니바퀴가 회전하는 방향을 저장한다
 * 4. 각 톱니바퀴를 회전시켜 리스트를 갱신한다
 * 	4-1. 톱니를 반시계 방향으로 회전 => 리스트의 첫 번째 값을 제거하고 마지막 값으로 추가한다
 * 	4-2. 톱니를 시계 방향으로 회전 => 리스트의 마지막 값을 제거하고 첫 번째 값으로 추가한다
 *  
 */
import java.io.*;
import java.util.*;

public class Main_BJ_14891_톱니바퀴 {

	static List<Integer>[] gear = new ArrayList[5];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int score = 0; //점수 저장

		//톱니 상태 입력
		for (int i = 1; i <= 4; i++) {
			String str = br.readLine();
			gear[i] = new ArrayList<Integer>();
			for (int j = 0; j < 8; j++)
				gear[i].add(str.charAt(j) - '0');
		}
		//K 입력
		int K = Integer.parseInt(br.readLine());
		
		//K만큼 회전 실행
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); //회전시킬 톱니바퀴
			int d = Integer.parseInt(st.nextToken()); //방향
			move(num, d); //회전
		}

		//결과
		for (int i = 1; i <= 4; i++)
			if (gear[i].get(0) == 1)
				score += (int) Math.pow(2, i - 1);

		//출력
		System.out.print(score);
	}

	//각 톱니바퀴의 방향대로 리스트를 갱신하는 메소드
	private static void rotate(int[] direction) {
		for (int i = 1; i <= 4; i++) {
			if (direction[i] == 1) { //시계 방향인 경우
				int n = gear[i].get(7);
				gear[i].remove(7);
				gear[i].add(0, n);
			}

			else if (direction[i] == -1) { //반시계 방향인 경우
				int n = gear[i].get(0);
				gear[i].remove(0);
				gear[i].add(n);
			}
		}
	}

	//각 톱니바퀴의 회전 방향을 정하는 메소드
	private static void move(int num, int d) { //회전을 시작하는 톱니바퀴

		int[] direction = new int[5];
		direction[num] = d;

		if (num != 1) { //1번 톱니바퀴가 아닌 경우 왼쪽으로 탐색
			for (int i = num; i >= 2; i--) {
				if (gear[i].get(6) != gear[i - 1].get(2))
					direction[i - 1] = -direction[i];

				else //회전하지 않는 톱니바퀴의 값은 0이 됨
					break;
			}
		}
		if (num != 4) { //4번 톱니바퀴가 아닌 경우 오른쪽으로 탐색
			for (int i = num; i <= 3; i++) {
				if (gear[i].get(2) != gear[i + 1].get(6))
					direction[i + 1] = -direction[i];

				else //회전하지 않는 톱니바퀴의 값은 0이 됨
					break;
			}
		}
		//2,3번 톱니바퀴는 양방향으로 탐색을 하게 됨
		rotate(direction); //회전 시켜서 리스트 갱신
	}
}
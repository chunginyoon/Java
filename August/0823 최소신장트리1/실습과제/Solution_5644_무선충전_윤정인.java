package test;

/* A,B는 매 초마다 이동하므로 매 초마다의 이동한 위치를 계산해서 저장한다
 * 충전기는 파워를 기준으로 오름차순으로 정렬한다
 * 매초마다 사용자와 각 충전기와의 거리를 구한다
 * 거리가 충전기의 영역 내에 포함되면 사용자의 스택에 충전기의 인덱스 값을 추가한다
 * 충전기의 파워가 작은 순서대로 탐색하게 되므로 스택에 나중에 쌓이는 값일수록 우선 순위가 높은 충전기이다
 * 1. A, B 둘 다 스택 값이 존재하지 않는 경우 => 계산X
 * 2. A, B 중 하나만 스택 값이 존재하는 경우 => 결과값에 더한다
 * 3. A, B 둘 다 스택 값이 존재하는 경우 => 스택에서 각자 값을 하나 꺼내 A와 B의 1순위 충전기의 인덱스를 비교한다
 * 	3-1. 1순위 충전기가 서로 같은 경우
 * 		a. 둘 다 스택 값이 1개만 존재하는 경우 => 1순위 충전기의 파워를 반씩 나눠 가지게 되므로 충전기의 파워를 결과값에 더한다
 * 		b. 둘 중 한 명만 스택 값이 1개인 경우 => 스택 값이 1개인 사용자의 1순위+다른 사용자의 2순위 파워 합을 결과값에 더한다
 * 		c. 둘 다 스택 값이 2개 이상인 경우 => (A의 1순위+B의 2순위), (B의 1순위+A의 2순위)를 비교해서 더 큰 값을 결과값에 더한다
 * 	3-2. 1순위 충전기가 서로 다른 경우 => 각 1순위 충전기들의 파워 합을 구해서 결과값에 더한다
 */
import java.io.*;
import java.util.*;

public class Solution_5644_무선충전_윤정인 {

	private static class User { // 사용자의 위치를 저장하는 클래스
		int x;
		int y;

		private User(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class Charger extends User implements Comparable<Charger> { // 충전기의 위치와 거리 제한, 파워를 저장하는 클래스
		int area;
		int power;

		private Charger(int x, int y, int area, int power) {
			super(x, y);
			this.area = area;
			this.power = power;
		}

		@Override
		public int compareTo(Charger o) { // 정렬 기준은 파워기준 오름차순
			return this.power - o.power;
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static User[] A; // 사용자 A
	static User[] B; // 사용자 B
	static Charger[] charger; // 충전기
	static int totalTime; // 총 시간
	static int BC; // 충전기의 개수

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트케이스 입력
		int T = Integer.parseInt(br.readLine());

		// 테스트케이스만큼 실행
		for (int tc = 1; tc <= T; tc++) {

			// 총 시간과 충전기의 개수 입력
			st = new StringTokenizer(br.readLine());
			totalTime = Integer.parseInt(st.nextToken());
			BC = Integer.parseInt(st.nextToken());

			// 객체 생성
			A = new User[totalTime + 1];
			B = new User[totalTime + 1];
			charger = new Charger[BC];

			// A와 B의 초기 위치 저장
			A[0] = new User(1, 1);
			B[0] = new User(10, 10);

			// 매초마다 이동하는 사용자의 위치 계산
			setUser(A);
			setUser(B);

			// 각 충전기의 정보 저장
			setCharger(charger);

			// 충전기를 파워 기준으로 오름차순 정렬
			Arrays.sort(charger);

			int maxSum = 0; // 최대 합 저장

			// 매초마다 사용자와 각 충전기와의 거리를 구해서 영역에 포함되는지 체크
			for (int time = 0; time <= totalTime; time++) {

				Stack<Integer> chargerA = new Stack<>(); // 사용자가 충전할 수 있는 충전기의 인덱스 번호를 저장할 스택
				checkArea(time, A, chargerA); // 사용자의 위치가 충전기의 영역 안에 포함되면 스택에 충전기 인덱스 추가

				Stack<Integer> chargerB = new Stack<>(); // A,B 모두 시행
				checkArea(time, B, chargerB);

				// A만 충전 가능하고, B는 가능한 충전기가 없을 때
				if (!chargerA.isEmpty() && chargerB.isEmpty()) {
					int idx = chargerA.pop(); // A의 1순위 충전기
					maxSum += charger[idx].power;
				}

				// B만 충전 가능하고, A는 가능한 충전기가 없을 때
				else if (chargerA.isEmpty() && !chargerB.isEmpty()) {
					int idx = chargerB.pop(); // B의 1순위 충전기
					maxSum += charger[idx].power;
				}

				// A,B 모두 충전 가능할 때
				else if (!chargerA.isEmpty() && !chargerB.isEmpty()) {

					if (chargerA.peek() == chargerB.peek()) { // 1. A와 B의 1순위 충전기가 서로 같은 경우

						if (chargerA.size() == 1 && chargerB.size() == 1) { // 1-1. 둘 다 스택 값을 1개만 가지면 파워를 반씩 나눠가진다
							int idx = chargerA.pop();
							maxSum += charger[idx].power;
						}

						else if (chargerA.size() == 1 && chargerB.size() > 1) {// 1-2. A의 스택 값 1개, B는 2개 이상일 때
							// A의 1순위+B의 2순위
							chargerB.pop();
							int idxA = chargerA.pop();
							int idxB = chargerB.pop();
							maxSum += charger[idxA].power + charger[idxB].power;
						}

						else if (chargerA.size() > 1 && chargerB.size() == 1) { // 1-3. B의 스택 값 1개, A는 2개 이상일 때
							// B의 1순위+A의 2순위
							chargerA.pop();
							int idxA = chargerA.pop();
							int idxB = chargerB.pop();
							maxSum += charger[idxA].power + charger[idxB].power;
						}

						else if (chargerA.size() > 1 && chargerB.size() > 1) { // 1-4. A와 B 모두 스택 값 2개 이상일 때
							// (A의 1순위+B의 2순위), (B의 1순위+A의 2순위)비교해서 더 큰 값을 결과값에 더한다
							int idxA1 = chargerA.pop();
							int idxA2 = chargerA.pop();

							int idxB1 = chargerB.pop();
							int idxB2 = chargerB.pop();

							int sum1 = charger[idxA1].power + charger[idxB2].power;
							int sum2 = charger[idxA2].power + charger[idxB1].power;
							maxSum += Math.max(sum1, sum2);
						}
					}

					else { // 2. A와 B의 1순위 충전기가 서로 다른 경우
						int idxA = chargerA.pop();
						int idxB = chargerB.pop();
						maxSum += charger[idxA].power + charger[idxB].power;
					}
				}
			}
			// 결과 저장
			sb.append("#" + tc + " " + maxSum).append('\n');
		}
		// 출력
		System.out.print(sb);
	}

	// 매초마다 사용자의 위치를 계산하여 클래스에 저장하는 메소드
	private static void setUser(User[] user) throws IOException {

		int[][] delta = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= totalTime; i++) {
			int d = Integer.parseInt(st.nextToken());
			int x = user[i - 1].x + delta[d][0];
			int y = user[i - 1].y + delta[d][1];
			user[i] = new User(x, y);
		}
	}

	// 충전기의 정보를 입력받아 저장하는 메소드
	private static void setCharger(Charger[] charger) throws IOException {
		for (int i = 0; i < BC; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int area = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());

			charger[i] = new Charger(x, y, area, power);
		}
	}

	// 매초마다 사용자와 충전기의 거리를 계산하여 영역에 포함되면, 스택에 충전기 인덱스 값을 추가하는 메소드
	private static void checkArea(int time, User[] user, Stack<Integer> chargerU) {

		for (int i = 0; i < BC; i++) {
			int dx = charger[i].x - user[time].x;
			int dy = charger[i].y - user[time].y;
			int distance = Math.abs(dx) + Math.abs(dy);
			if (distance <= charger[i].area)
				chargerU.push(i);
		}
	}
}

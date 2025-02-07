package SamsungSW;

/*
 * 벨트의 내구성과 로봇의 유무를 저장하는 클래스 생성
 * 벨트를 회전시켜야 하므로 리스트 사용
 * 
 */
import java.io.*;
import java.util.*;

public class Main_BJ_20055_컨베이어벨트위의로봇 {

	static class Belt {
		int strength;
		boolean robot;

		private Belt(int strength, boolean robot) {
			this.strength = strength;
			this.robot = robot;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<Belt> belt = new ArrayList<>();

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 2 * N; i++)
			belt.add(new Belt(Integer.parseInt(st.nextToken()), false));

		int cnt = 0;

		while (count(belt) < K) {
			// 1. 벨트 회전
			Belt b = belt.get(belt.size() - 1);
			belt.remove(belt.size() - 1);
			belt.add(0, b);

			if (belt.get(N - 1).robot) // 내리는 위치에 로봇이 있으면 내린다
				belt.get(N - 1).robot = false;

			// 2. 로봇 이동
			for (int i = N - 2; i >= 0; i--)
				if (belt.get(i).robot)
					if (belt.get(i + 1).strength > 0 && !belt.get(i + 1).robot) {
						belt.get(i + 1).strength--;
						belt.get(i + 1).robot = true;
						belt.get(i).robot = false;
					}

			if (belt.get(N - 1).robot) // 내리는 위치에 로봇이 있으면 내린다
				belt.get(N - 1).robot = false;

			// 3. 올리는 위치에 로봇 올리기
			if (belt.get(0).strength > 0 && !belt.get(0).robot) {
				belt.get(0).strength--;
				belt.get(0).robot = true;
			}

			cnt++;
		}

		System.out.print(cnt);
	}

	public static int count(List<Belt> belt) {
		int cnt = 0;

		for (int i = 0; i < belt.size(); i++)
			if (belt.get(i).strength == 0)
				cnt++;

		return cnt;
	}
}
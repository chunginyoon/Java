package SamsungSW;

/*
 * 벨트는 계속해서 움직이므로 고유 번호가 필요하다. 벨트의 현재 위치(가변적), 내구성을 저장하는 클래스를 생성한다. 배열의 인덱스는 벨트의 고유 번호가 된다.
 * 로봇은 먼저 들어온 순서가 중요하다. 로봇이 새로 생길 때마다 리스트에 추가한다. 로봇 리스트의 값은 벨트 배열의 인덱스 값이 된다
 * 	=> 벨트의 위치가 변해도 로봇이 어떤 벨트 위에 있는지 알 수 있다
 * 로봇이 벨트 위에서 이동할 때, 로봇 리스트의 값은 벨트의 인덱스가 되므로 로봇 리스트의 값+1 => 다음 벨트가 된다
 */
import java.io.*;
import java.util.*;

public class Main_BJ_20055_컨베이어벨트위의로봇 {

	static class Belt {
		int strength;
		boolean robot;

		Belt(int strength, boolean robot) {
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
			
			for (int i = 0; i < belt.size(); i++)
				System.out.printf("%d ", belt.get(i).strength);
			System.out.println();

			// 2. 로봇 이동
			for (int i = 0; i < belt.size(); i++) {

				if(!belt.get(i).robot) continue;
				
				if (i == 2 * N - 1) {
					if (!belt.get(0).robot && belt.get(0).strength >= 1) {
						belt.get(0).strength--;
						belt.get(0).robot = true;
					}
				}

				else {
					if (!belt.get(i + 1).robot && belt.get(i + 1).strength >= 1) {
						belt.get(i + 1).strength--;

						if (i + 1 != N - 1)
							belt.get(i + 1).robot = true;
					}
				}
			}
			
			for (int i = 0; i < belt.size(); i++)
				System.out.printf("%d ", belt.get(i).strength);
			System.out.println();
			
			// 3. 올리는 위치에 로봇 올리기
			if (belt.get(0).strength >= 1 && !belt.get(0).robot) {
				belt.get(0).strength--;
				belt.get(0).robot = true;
			}
			
			for (int i = 0; i < belt.size(); i++)
				System.out.printf("%d ", belt.get(i).strength);
			System.out.println();
			cnt++;
		}
		System.out.print(cnt);
	}

	private static int count(List<Belt> belt) {
		int cnt = 0;
		for (int i = 0; i < belt.size(); i++)
			if (belt.get(i).strength == 0)
				cnt++;
		return cnt;
	}
}
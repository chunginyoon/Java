package test;

import java.io.*;
import java.util.*;

public class Main_BJ_1244_스위치켜고끄기_윤정인 {

	static int[] light; //스위치 상태

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); //스위치 개수
		light = new int[N + 1];
		int student; //학생 수
		int gender; //학생의 성별
		int studentsNum; //학생이 가진 숫자

		// 스위치 초기 상태 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			light[i] = Integer.parseInt(st.nextToken());

		//학생 정보 입력
		student = Integer.parseInt(br.readLine());

		for (int i = 0; i < student; i++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken()); // 성별
			studentsNum = Integer.parseInt(st.nextToken()); // 학생이 가진 숫자

			if (N == 1) //N=1이면 학생 수만큼 스위치 상태 변경
				for (int j = 0; j < student; j++)
					switchLight(1);

			else { //N이 1보다 클 때
				if (gender == 1) { // 남학생
					for (int j = studentsNum; j <= N; j++) //남학생이 가진 숫자의 배수이면 스위치 상태 변경
						if (j % studentsNum == 0)
							switchLight(j);
				}

				else { // 여학생
					int index = 0;
					switchLight(studentsNum);

					for (int j = 1; j <= N / 2; j++) { //대칭 찾고 상태 변경
						if (studentsNum - j >= 1 && studentsNum + j <= N) {
							if (light[studentsNum - j] == light[studentsNum + j])
								index = j;
							else
								break;
						}
						else
							break;
					}

					if (index != 0) {
						for (int j = 1; j <= index; j++) {
							switchLight(studentsNum + j);
							switchLight(studentsNum - j);
						}
					}
				}
			}

		}

		// 출력
		for (int i = 1; i <= N; i++) {
			if (i % 20 == 0)
				System.out.printf("%d\n", light[i]);
			else
				System.out.printf("%d ", light[i]);
		}
	}

	private static void switchLight(int index) { //스위치 상태를 바꾸는 함수
		if (light[index] == 0)
			light[index] = 1;

		else
			light[index] = 0;
	}
}
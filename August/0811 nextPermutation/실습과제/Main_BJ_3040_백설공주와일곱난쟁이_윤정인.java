package test_0810;
/*
 * 난쟁이의 모자에 쓰인 숫자는 1~99
 * 조합으로 난쟁이 배열의 인덱스 7개 뽑아서 합 구하기
 * 합이 100이면 출력, 아니면 재귀 계속 실행
 */

import java.io.*;

public class Main_BJ_3040_백설공주와일곱난쟁이_윤정인 {

	static int[] input = new int[9]; //입력
	static int[] dwarf = new int[7]; //난쟁이의 번호 저장
	static int total = 0; //번호의 합

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//입력
		for (int i = 0; i < 9; i++)
			input[i] = Integer.parseInt(br.readLine().trim());

		//9명중 7명 뽑는 조합 실행하고 합이 100이면 출력
		combination(0, 0);
	}

	//9명중 7명 뽑는 조합을 실행하고 합이 100이면 출력하는 메소드
	private static void combination(int start, int depth) {

		if (depth == 7) {
			
			total = 0; //7명 난쟁이들의 숫자 총 합
			
			for (int i = 0; i < 7; i++)
				total += dwarf[i];

			if (total != 100) //합이 100이 아니면 재귀 종료
				return;

			for (int i = 0; i < 7; i++)
				System.out.println(dwarf[i]);
			return;
		}

		for (int i = start; i < 9; i++) {
			dwarf[depth] = input[i];
			combination(i + 1, depth + 1);
		}
	}

}
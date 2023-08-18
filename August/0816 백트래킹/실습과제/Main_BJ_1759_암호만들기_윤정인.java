package test;

/*
 * 1. 문자 입력 받아서 정렬
 * 2. 조합으로 문자 C개 중에서 L개 뽑기
 * 3. 완성된 조합에서 모음과 자음 개수 세기
 * 4. 모음이 1개 이상이면서 자음이 2개 이상인지 체크
 * 5. 조건을 만족하면 출력
 */
import java.io.*;
import java.util.*;

public class Main_BJ_1759_암호만들기_윤정인 {
	static int L;
	static int C;
	static char[] code; // 완성된 암호
	static char[] str; // 입력 받은 알파벳을 저장할 배열
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// L,C 입력
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		str = new char[C];
		code = new char[L];

		// 알파벳 입력 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++)
			str[i] = st.nextToken().charAt(0);

		// 입력 받은 알파벳 오름차순으로 정렬
		Arrays.sort(str);

		// 실행
		combination(0, 0);
	}

	// 입력 받은 L개의 알파벳 중에서 C개를 뽑고, 조건을 만족하는지 체크 후 출력하는 메소드
	private static void combination(int start, int depth) {
		if (depth == L) { // 하나의 조합 완성

			// 조건을 만족하면 암호 출력
			if (checkCode(code)) {
				for (int i = 0; i < L; i++)
					System.out.printf("%c", code[i]);
				System.out.println();
			}
			return;
		}
		// 조합 뽑기
		for (int i = start; i < C; i++) {
			code[depth] = str[i];
			combination(i + 1, depth + 1);
		}
	}

	// 완성된 암호가 모음 1개 이상, 자음 2개 이상을 포함하는지 체크하는 메소드
	private static boolean checkCode(char[] code) {

		int consonant = 0; // 자음 개수
		int vowel = 0; // 모음 개수

		// 모은, 자음 개수 세기
		for (int i = 0; i < L; i++) {
			if (code[i] == 'a' || code[i] == 'e' || code[i] == 'i' || code[i] == 'o' || code[i] == 'u')
				vowel++;
			else
				consonant++;
		}

		// 조건을 만족하면 true 반환
		if (vowel >= 1 && consonant >= 2)
			return true;

		// 조건을 만족하지 않으면 false 반환
		return false;
	}
}
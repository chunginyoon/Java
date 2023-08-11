package test;

/*
 * 1~18까지의 카드 중에서 규영이가 가지지 않은 카드 => 인영이의 카드
 * 인영이의 카드를 9!의 횟수만큼 줄세우기 한다(9 P 9 순열)
 * 각 순열마다 규영이와 인영이의 카드를 비교하여 승패를 가린다
 * 
 * 1. 규영이가 가진 카드를 입력받는다
 * 2. boolean isSelected 배열에서 규영이가 가진 카드를 체크
 * 3. 규영이가 가지지 않은 카드는 인영이의 카드로 저장한다
 * 4. 인영이가 가진 9개의 카드 중 9개를 뽑는 순열을 만든다
 * 5. 각 순열마다 규영이의 카드와 비교하여 각 카드 숫자가 더 큰 사람이 카드 숫자의 합만큼 점수를 가져간다
 * 6. 9개의 카드 비교가 끝나면 총 점수를 비교해 승패를 결정한다
 */
import java.io.*;
import java.util.*;

//규영이와 인영이의 점수, 규영이의 승리, 무승부, 패를 저장할 클래스
class Result {
	int scoreK; //규영이의 점수
	int scoreI; //인영이의 점수
	int win; //규영이가 승리한 횟수
	int lose; //규영이가 진 횟수
	int tie; //무승부

	Result(int score1, int score2, int win, int lose, int even) {
		this.scoreK = score1;
		this.scoreI = score2;
		this.win = win;
		this.lose = lose;
		this.tie = even;
	}
}

public class Solution_6808_규영이와인영이의카드게임_윤정인 {

	static int[] KyuYoung; //규영이가 가진 카드
	static int[] InYoung; //인영이가 가진 카드

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		//테스트 케이스 입력
		int T = Integer.parseInt(br.readLine());
		KyuYoung = new int[9];
		InYoung = new int[9];

		for (int tc = 1; tc <= T; tc++) {

			Result result = new Result(0, 0, 0, 0, 0); //게임의 결과를 저장할 객체 생성
			boolean[] isSelected = new boolean[19]; //1~18번까지의 카드중 규영이가 가진 카드를 체크할 배열
			
			//규영이의 카드 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				KyuYoung[i] = Integer.parseInt(st.nextToken());
				isSelected[KyuYoung[i]] = true; //규영의 카드 체크
			}

			//규영이 가지지 않은 카드 => 인영의 카드
			int cnt = 0;
			for (int i = 1; i <= 18; i++) {
				if (!isSelected[i])
					InYoung[cnt++] = i;
			}

			//인영의 카드 9개중 9개를 뽑는 순열
			int[] mix = new int[9];
			boolean[] visit = new boolean[9];
			permutation(0, result, mix, visit);
			
			//결과 저장
			sb.append("#" + tc + " " + result.win + " " + result.lose);
			sb.append('\n');
		}
		//출력
		System.out.print(sb);
	}

	//9P9를 시행하는 메소드
	//순열이 완성되면 카드 비교를 시행해서 승패를 결정한다
	private static void permutation(int depth, Result result, int[] mix, boolean[] visit) {

		if (depth == 9) { //하나의 순열이 완성되면

			result.scoreK = 0; //규영이와 인영이의 점수 초기화
			result.scoreI = 0;

			for (int i = 0; i < 9; i++) { //9개의 각 카드를 첫 번째부터 비교

				if (KyuYoung[i] > mix[i]) //규영이의 카드 숫자가 인영이의 숫자보다 크면 규영의 점수+
					result.scoreK += (KyuYoung[i] + mix[i]);
				else
					result.scoreI += (KyuYoung[i] + mix[i]);
			}

			//카드 비교가 끝난 후 총 점수를 비교해 승무패를 결정한다
			if (result.scoreK > result.scoreI)
				result.win++;

			else if (result.scoreK == result.scoreI)
				result.tie++;

			else
				result.lose++;

			return;
		}

		for (int i = 0; i < 9; i++) { //재귀로 순열 생성
			if (!visit[i]) {
				visit[i] = true;
				mix[depth] = InYoung[i];
				permutation(depth + 1, result, mix, visit);
				visit[i] = false;
			}
		}
	}
}

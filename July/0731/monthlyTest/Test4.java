package monthlyTest;
import java.util.Scanner;

public class Test4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int[] N = new int[T]; //팀원 수를 저장할 배열
		int cnt; //평균을 초과한 점수를 갖는 팀원 수를 저장할 변수
		double[] result = new double[T]; //결과값을 저장할 배열
		double[] avg = new double[T]; //각 팀의 평균값을 저장할 배열
		int[][] score = new int[T][1000]; //각 팀의 학생들의 점수를 저장할 배열

		// 입력
		for (int testCase = 1; testCase <= T; testCase++) {

			N[testCase - 1] = sc.nextInt();
			for (int i = 0; i < N[testCase - 1]; i++)
				score[testCase - 1][i] = sc.nextInt();
		}

		
		for (int testCase = 1; testCase <= T; testCase++) {
			
			//각 팀 학생들의 점수의 합
			for (int i = 0; i < N[testCase - 1]; i++)
				avg[testCase - 1]+=score[testCase - 1][i];
			
			//각 팀의 평균
			avg[testCase - 1]/=N[testCase - 1];
			
			//각 팀 평균을 초과한 점수를 갖는 팀원 수 초기화
			cnt = 0;
			
			//각 팀의 평균보다 초과된 점수를 갖는 팀원수 체크
			for (int i = 0; i < N[testCase - 1]; i++)
				if(score[testCase - 1][i] > avg[testCase - 1])
					cnt++;
			
			//비율 구하기
			result[testCase - 1] = (double)cnt/N[testCase - 1]*100;
			
			//출력
			System.out.printf("#%d %.3f%%%n", testCase, result[testCase - 1]);
			
		}
	}
}

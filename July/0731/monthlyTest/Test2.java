package monthlyTest;
import java.util.Arrays;

public class Test2 {
	
	//차의 절대값을 구하는 함수
	public static double abs(double totalAvg, double avg) {
		if((totalAvg - avg) < 0 )
			return (avg - totalAvg);
		
		else
			return (totalAvg - avg);
	}
	
	public static void main(String[] args) {

		int[][] jumsu = { { 95, 60, 72, 80 }, { 44, 52, 68, 94 }, { 65, 67, 57, 72 }, { 70, 72, 76, 77 } };
		int sum = 0;
		double[] avg = new double[4]; //과목별 평균
		double totalAvg; //전체 평균
		double max; //전체 평균값과 차가 가장 큰 과목의 평균
		
		
		//전체 평균 구하기
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4; j++)
				sum+=jumsu[i][j];
		
		totalAvg = (double)sum/16;
		
		//과목별 평균 구하기
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++)
				avg[i]+=jumsu[j][i];
			
			avg[i]/=4;
		}
		
		//전체 평균값과 차가 가장 큰 평균을 갖는 과목 구하기
		max = avg[0];
		
		for(int i = 1; i<4; i++)
			if(abs(totalAvg, avg[i]) > abs(totalAvg, max))
				max = avg[i];
		
		System.out.printf("전체 평균 : %.2f 과목 평균: %.2f", totalAvg, max);
			
	}

}

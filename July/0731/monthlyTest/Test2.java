package monthlyTest;
import java.util.Arrays;

public class Test2 {
	
	//���� ���밪�� ���ϴ� �Լ�
	public static double abs(double totalAvg, double avg) {
		if((totalAvg - avg) < 0 )
			return (avg - totalAvg);
		
		else
			return (totalAvg - avg);
	}
	
	public static void main(String[] args) {

		int[][] jumsu = { { 95, 60, 72, 80 }, { 44, 52, 68, 94 }, { 65, 67, 57, 72 }, { 70, 72, 76, 77 } };
		int sum = 0;
		double[] avg = new double[4]; //���� ���
		double totalAvg; //��ü ���
		double max; //��ü ��հ��� ���� ���� ū ������ ���
		
		
		//��ü ��� ���ϱ�
		for(int i = 0; i<4; i++)
			for(int j = 0; j<4; j++)
				sum+=jumsu[i][j];
		
		totalAvg = (double)sum/16;
		
		//���� ��� ���ϱ�
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++)
				avg[i]+=jumsu[j][i];
			
			avg[i]/=4;
		}
		
		//��ü ��հ��� ���� ���� ū ����� ���� ���� ���ϱ�
		max = avg[0];
		
		for(int i = 1; i<4; i++)
			if(abs(totalAvg, avg[i]) > abs(totalAvg, max))
				max = avg[i];
		
		System.out.printf("��ü ��� : %.2f ���� ���: %.2f", totalAvg, max);
			
	}

}

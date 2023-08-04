package Test;
import java.util.Scanner;

public class Bridge {
	public static void main(String[] args) {
Scanner sc =new Scanner(System.in);
		
		int N;
		int length;
		int max=0;
		int arr[][];
		int delta[];
		
		//N입력 받고, 배열 생성
		N = sc.nextInt();
		arr = new int[N][N];
		delta = new int[N-1];
		
		//delta 변수 값은 1~N-1까지 할당
		for(int i = 0; i<N-1; i++)
			delta[i]=i+1;
		
		//행렬 입력 받기
		for(int i =0; i<N; i++)
			for(int j = 0; j<N; j++)
				arr[i][j]=sc.nextInt();
		
		//X값 기준 탐색
		for(int i =0; i<N; i++)
			for(int j = 0; j<N; j++)
				if(arr[i][j] == 1) {
					
					length = 0;
					
					for(int k = 0; k<N-1; k++) {
						int nx = i + delta[k];
						
						if(nx < N) {
							
						    //경계값인 경우
							if(nx == N-1) {
								if(arr[nx][j] == 0) //경계값이 0인 경우 다리를 지을 수 없으므로 다리 길이 zero
									length = 0;
								
								else { //경계값이 1인 경우 다리를 지을 수 있음
									length++;
									max=Math.max(length, max);
								}
							}
							
							//경계값이 아닌 경우 다시 1이 나올때까지 다리 길이 늘리기
							else {
								if(arr[nx][j] == 0)
									length++;
								
								else {
									length++;
									max=Math.max(length, max);
									break;
								}
							}
					}
					
				}
	}
		
		//Y값 기준 탐색
		for(int i =0; i<N; i++)
			for(int j = 0; j<N; j++)
				if(arr[i][j] == 1) {
					
					length = 0;
					
					for(int k = 0; k<N-1; k++) {
						int ny = j + delta[k];
						
						if(ny < N) {
							
							//경계값인 경우
							if(ny == N-1) {
								if(arr[i][ny] == 0) //경계값이 0인 경우 다리를 지을 수 없으므로 다리 길이 zero
									length=0;
								
								else { //경계값이 1인 경우 다리를 지을 수 있음
									length++;
									max=Math.max(length, max);
								}
							}
							
							//경계값이 아닌 경우 다시 1이 나올때까지 다리 길이 늘리기
							else {
								
								if(arr[i][ny] == 0)
									length++;
								
								else {
									length++;
									max=Math.max(length, max);
									break;
								}
							}
					}
					
				}
	}
		
		System.out.println(max);
	}

}

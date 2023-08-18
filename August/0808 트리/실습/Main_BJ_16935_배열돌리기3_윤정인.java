package test;

/*
 * 1번 연산: nextArr[N][M]
 * 		for(int i = 0; i<N; i++) for(int j = 0; j<M; j++) nextArr[N-1-i][j] = originalArr[i][j]
 * 
 * 2번 연산: nextArr[N][M]
 * 		for(int i = 0; i<N; i++) for(int j = 0; j<M; j++) nextArr[i][M-1-j] = originalArr[i][j]
 * 
 * 3번 연산: nextArr[M][N]
 * 		for(int i = 0; i<M; i++) for(int j = 0; j<N; j++) nextArr[i][j] = originalArr[N-1-j][i]
 * 4번 연산: nextArr[M][N]
 * 		for(int i = 0; i<M; i++) for(int j = 0; j<N; j++) nextArr[i][j] = originalArr[j][M-1-i]
 */
import java.io.*;
import java.util.*;

public class Main_BJ_16935_배열돌리기3_윤정인 {
	static int N;
	static int M;
	static int[][] originalMatrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		originalMatrix = new int[N][M];
		int[][] nextMatrix;
		int[] calculate = new int[R];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				originalMatrix[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int tc = 0; tc < R; tc++) {
			calculate[tc] = Integer.parseInt(st.nextToken());

			if (calculate[tc] == 1) { // 1번 연산
				nextMatrix = new int[N][M];
				for (int i = 0; i < N; i++)
					for (int j = 0; j < M; j++)
						nextMatrix[N - 1 - i][j] = originalMatrix[i][j];

				copyMatrix(originalMatrix, nextMatrix);

			}

			else if (calculate[tc] == 2) { // 2번 연산
				nextMatrix = new int[N][M];
				for (int i = 0; i < N; i++)
					for (int j = 0; j < M; j++)
						nextMatrix[i][M - 1 - j] = originalMatrix[i][j];

				copyMatrix(originalMatrix, nextMatrix);

			}

			else if (calculate[tc] == 3) { // 3번 연산
				nextMatrix = new int[M][N];
				for (int i = 0; i < M; i++)
					for (int j = 0; j < N; j++)
						nextMatrix[i][j] = originalMatrix[N - 1 - j][i];
				int temp = N;
				N = M;
				M = temp;
				originalMatrix = new int[N][M];

				copyMatrix(originalMatrix, nextMatrix);
			}

			else if (calculate[tc] == 4) { // 4번 연산
				nextMatrix = new int[M][N];
				for (int i = 0; i < M; i++)
					for (int j = 0; j < N; j++)
						nextMatrix[i][j] = originalMatrix[j][M - 1 - i];
				int temp = N;
				N = M;
				M = temp;
				originalMatrix = new int[N][M];

				copyMatrix(originalMatrix, nextMatrix);
			}

			else if (calculate[tc] == 5) { // 5번 연산
				nextMatrix = new int[N][M];

				for (int i = 0; i < N / 2; i++) // 1번을 2번으로
					for (int j = 0; j < M / 2; j++)
						nextMatrix[i][j + M / 2] = originalMatrix[i][j];

				for (int i = 0; i < N / 2; i++) // 2번을 3번으로
					for (int j = M / 2; j < M; j++)
						nextMatrix[i + N / 2][j] = originalMatrix[i][j];

				for (int i = N / 2; i < N; i++) // 3번을 4번으로
					for (int j = M / 2; j < M; j++)
						nextMatrix[i][j - M / 2] = originalMatrix[i][j];

				for (int i = N / 2; i < N; i++) // 4번을 1번으로
					for (int j = 0; j < M / 2; j++)
						nextMatrix[i - N / 2][j] = originalMatrix[i][j];

				copyMatrix(originalMatrix, nextMatrix);
			}

			else { // 6번 연산
				nextMatrix = new int[N][M];
				for (int i = 0; i < N / 2; i++) // 1번을 4번으로
					for (int j = 0; j < M / 2; j++)
						nextMatrix[i + N / 2][j] = originalMatrix[i][j];

				for (int i = N / 2; i < N; i++) // 4번을 3번으로
					for (int j = 0; j < M / 2; j++)
						nextMatrix[i][j + M / 2] = originalMatrix[i][j];

				for (int i = N / 2; i < N; i++) // 3번을 2번으로
					for (int j = M / 2; j < M; j++)
						nextMatrix[i - N / 2][j] = originalMatrix[i][j];

				for (int i = 0; i < N / 2; i++) // 2번을 1번으로
					for (int j = M / 2; j < M; j++)
						nextMatrix[i][j - M / 2] = originalMatrix[i][j];

				copyMatrix(originalMatrix, nextMatrix);
			}

		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				System.out.printf("%d ", originalMatrix[i][j]);
			System.out.println();
		}

	}

	private static void copyMatrix(int[][] originalMatrix, int[][] nextMatrix) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				originalMatrix[i][j] = nextMatrix[i][j];
	}
}

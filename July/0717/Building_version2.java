package test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 문제 해석
 * 
 * N 3~20
 * NxN 정사각형
 * 
 * B : 빌딩, G: 공원
 * 
 * 인접한 구획 : 8방
 * 
 * 빌딩의 높이
 * - B를 기준으로 인접한 8방이 모두 B인 경우,
 * 	B 기준 가로 세로 축의 모든 B의 개수
 * B를 기준으로 인접한 8방에 G가 있는 경우
 * 	높이 2
 * 
 * 빌딩의 최대 높이 구하기
 * 
 * 
 * 문제 해결 프로세스
 * 1. 'B' 탐색
 * 2. 탐색한 B기준 8방 탐색
 * int[] dr = { -1, 0, 1, 1, 1, 0,-1, -1 }
 * int[] dc = { 1, 1, 1, 0, -1, -1, -1, 0 }
 * 	
 * 3-1. 모두 B인 경우
 * 4. 가로, 세로 축의 B의 개수 구하기(높이 구하기)
 * ( i, j )
 * 가로 탐색
 * - i값 고정
 * - 열 0~N-1
 * for c = 0~N-1:
 * 	 if map[i][c]=='B':
 * 		cnt++
 * 열 탐색
 * - j값 고정
 * - 행 0~N-1
 * for r = 0~N-1:
 * 	if map[r][j]=='B':
 * 		cnt++
 * - 중복 고려해서 cnt-1
 * 
 * 5. 최댓값이면 갱신
 * 
 * 3-2. G를 발견한 경우
 *  - 높이를 2로 설정
 * 
 * 
 * @author SSAFY
 *
 */
public class Building_version2 {

	public static void main(String[] args) {

		int[] dr = { -1, 0, 1, 1, 1, 0,-1, -1 };
		int[] dc = { 1, 1, 1, 0, -1, -1, -1, 0 };
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			int N = sc.nextInt();
			
			char[][] map = new char[N][N];
			
			//각 행과 열의 B의 개수
			int[] rowBCnt = new int[N];
			int[] colBCnt = new int[N];
			
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.next().charAt(0);
					//행,열의 B 개수 cnt
					if(map[i][j]=='B') {
						rowBCnt[i]++;
						colBCnt[j]++;
					}
				}
			}
			
//			System.out.println(Arrays.deepToString(map));
			
			int answer = 0;
			//1. 'B' 탐색
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]=='B') {
//						 2. 탐색한 B기준 8방 탐색
						
						int d;
						for(d=0; d<8; d++) {
							//현재 방향에 대한 다음 좌표값
							int nr = i+dr[d];
							int nc = j+dc[d];
							
							//경계 벗어나는 경우 다음 방향 체크
							if(nr < 0 || nr >= N || nc < 0 || nc>=N) continue;
							
							//G인 경우 탐색 중지
							if(map[nr][nc]=='G') break;
						}
						//3-1. 모두 B인 경우
						int cnt=0;
						if(d==8) {
							cnt = rowBCnt[i] + colBCnt[j]-1;
						}
						//3-2. 'G'를 발견한 경우
						else cnt=2;

						//5. 최댓값 갱신
						answer = Math.max(answer, cnt);
					}
				}
				
			}
			
			System.out.printf("#%d %d%n", tc, answer);
		}
	}
}

import java.util.*;

public class Building_sol {

    public static void main(String[] args) {
        int[] dc = { 1, 1, 1, 0, -1, -1, -1, 0 };
        int[] dr = { -1, 0, 1, 1, 1, 0, -1, -1 };
    	
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();

            //경계 체크를 생략하기 위해 padding 값을 줌
            char[][] map = new char[N + 2][N + 2];
            
            int[] rowBCnt = new int[N + 1];	//각 행의 B의 개수
            int[] colBCnt = new int[N + 1];	//각 열의 B의 개수

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    map[i][j] = sc.next().charAt(0);
                    //입력값을 받음과 동시에 각 행,열의 B의 개수 Cnt
                    if (map[i][j] == 'B') {
                        rowBCnt[i]++;
                        colBCnt[j]++;
                    }
                }
            }
            
            int answer = 0;
            
            //step 1. B 탐색
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (map[r][c] == 'B') {

                        boolean isPark = false;
                        //step 2. 8방에 공원 존재여부 탐색 탐색
                        for (int d = 0; d < 8; d++) {
                        	int nr = r+dr[d];
                            int nc = c+dc[d];
                            if (map[nr][nc] == 'G') {
                                isPark = true;
                                break;
                            }
                        }

                        //step 3.계산해놓은 행, 열 B 개수를 통해 가로,세로축 B의개수 계산
                        int height = isPark ? 2 : rowBCnt[r] + colBCnt[c] - 1;
                        
                        //step 4. 최댓값인 경우 갱신
                        answer = Math.max(height, answer);
                    }
                }
            }
            System.out.printf("#%d %d\n", tc, answer);
        }
        sc.close();
    }
}
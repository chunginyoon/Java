package test;

import java.util.*;
import java.io.*;

public class PowerSet {

    static int N;
    static int[] numbers;
    static boolean[] selected;
    
    //{1, 2, 3}집합의 모든 부분집합 생성
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        numbers = new int[N];
        selected = new boolean[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) numbers[i] = Integer.parseInt(st.nextToken());
        
        powerSet(0);
    }
    
    //idx = 현재 선택할 원소의 인덱스
    static void powerSet(int idx) {
        if(idx == N) {
            //하나의 부분 집합 완성, 출력 -> 선택된 원소들을 출력
            boolean isEmptySet = true;
            for(int i=0; i<N; i++) {
                if(selected[i]) {
                    System.out.print(numbers[i] + " ");
                    isEmptySet = false;
                }
            }
            
            //공집합의 경우 아무것도 출력되지 않으므로 아래 내용 출력
            if(isEmptySet) System.out.print("∅");
            
            System.out.println();
            return;
        }
        
        //선택 X
        selected[idx] = false;
        
        //다음으로 넘김
        powerSet(idx + 1);
        
        //선택
        selected[idx] = true;
        
        //다음으로 넘김
        powerSet(idx + 1);
    }
}

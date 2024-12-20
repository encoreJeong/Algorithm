import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] arr;
    static boolean[] selected;
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        
        selected = new boolean[N];
        if (N % 2 == 0) {
            for (int i = 1; i < N / 2; i++) {
                combination(0, 0, selected, i);
            }
            selected = new boolean[N];
            selected[0] = true;
            combination(1, 1, selected, N / 2);
            
        } else {
            for (int i = 1; i <= N / 2; i++) {
                combination(0, 0, selected, i);
            }
        }
        
        System.out.println(answer);
    }
    
    public static void combination(int idx, int count, boolean[] selected, int target) {
        if (count > 0 && count == target) {
            answer = Math.min(answer, calScore(selected));
        }

        for (int i = idx; i < arr.length; i++) {
            selected[i] = true;
            combination(i + 1, count + 1, selected, target);
            selected[i] = false;
        }
    }
    
    public static int calScore(boolean[] selected) {
        
        int score1 = 0;
        int score2 = 0;

        for (int i = 0; i < N; i++) {
            if (!selected[i]) {
                continue;
            }

            for (int j = i + 1; j < N; j++) {
                if (!selected[j]) {
                    continue;
                }

                score1 += arr[i][j] + arr[j][i];
            }
        }

        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                continue;
            }

            for (int j = i + 1; j < N; j++) {
                if (selected[j]) {
                    continue;
                }

                score2 += arr[i][j] + arr[j][i];
            }
        }

        return Math.abs(score1 - score2);
    }
    
}
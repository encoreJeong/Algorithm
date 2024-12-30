import java.io.*;
import java.util.*;

class Main {

    static int answer;
    static int[][] matrixs;
    static int[][] dp;
    static int N;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        matrixs = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            matrixs[i][0] = Integer.parseInt(input[0]);
            matrixs[i][1] = Integer.parseInt(input[1]);
        }

        dp = new int[N][N];
        for (int len = 1; len < N; len++) {
            for (int i = 0; i + len < N; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(
                            dp[i][j], dp[i][k] + dp[k + 1][j] + matrixs[i][0] * matrixs[k+1][0] * matrixs[j][1]);
                }
            }
        }
        
        System.out.println(dp[0][N - 1]);
    }

}
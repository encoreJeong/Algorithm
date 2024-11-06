import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
    
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = coins.length - 1; i >= 0; --i) {
            for (int j = 0; j <= k; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = dp[j - coins[i]] + dp[j];
                }
            }
        }
        System.out.println(dp[k]);
    }   
}

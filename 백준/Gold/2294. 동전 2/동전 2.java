import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            for (int j = 0; j < k + 1; j++) {
                if (j - coin >= 0) {
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }

        int answer = dp[k] == Integer.MAX_VALUE - 1 ? -1 : dp[k];
        System.out.println(answer);
    }
}

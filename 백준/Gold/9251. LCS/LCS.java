import java.util.*;
import java.io.*;

public class Main {

    public static String str1;
    public static String str2;

    public static Map<Character, List<Integer>> str2Map;


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str1 = br.readLine();
        str2 = br.readLine();

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            for (int j = 0; j < str2.length(); j++) {
                char c2 = str2.charAt(j);

                if (c1 == c2) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    continue;
                }

                dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        
        System.out.println(dp[str1.length()][str2.length()]);
    }

}
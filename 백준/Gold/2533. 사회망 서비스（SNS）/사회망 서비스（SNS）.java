import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> tree;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0; 
        dp[node][1] = 1; 

        for (int child : tree.get(node)) {
            if (!visited[child]) {
                dfs(child);
                dp[node][0] += dp[child][1]; 
                dp[node][1] += Math.min(dp[child][0], dp[child][1]); 
            }
        }
    }
}

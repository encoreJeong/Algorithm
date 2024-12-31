import java.io.*;
import java.util.*;

class Main {

    static int N;
    static List<List<int[]>> tree = new ArrayList<>();
    static int answer = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); 

        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            String[] input = br.readLine().split(" ");

            int parent = Integer.parseInt(input[0]);
            int child = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            tree.get(parent).add(new int[] { child, weight });
            tree.get(child).add(new int[] { parent, weight });
        }

        for (int i = 1; i < N + 1; i++) {
            if (tree.get(i).size() != 1) {
                continue;
            }

            dfs(i, 0, new boolean[N + 1]);
        }

        System.out.println(answer);
    }
    
    public static void dfs(int idx, int sum, boolean[] visited) {

        visited[idx] = true;

        if (sum != 0 && tree.get(idx).size() == 1) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int[] neighbor : tree.get(idx)) {
            if (visited[neighbor[0]]) {
                continue;
            }

            dfs(neighbor[0], sum + neighbor[1], visited);
        }
    }

}
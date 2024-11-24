import java.io.*;
import java.util.*;

public class Main {

    public static int[] parents;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        parents = new int[N + 1];

        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int val = Integer.parseInt(st.nextToken());
            int val2 = Integer.parseInt(st.nextToken());

            tree.get(val).add(val2);
            tree.get(val2).add(val);
        }

        bfs(tree);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N + 1; i++) {
            sb.append(parents[i]).append("\n");
        }

        System.out.println(sb);
    }
    
    public static void bfs(List<List<Integer>> tree) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[tree.size()];

        q.offer(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            
            int size = q.size();

            for (int i = 0; i < size; i++) {

                int curNode = q.poll();
                visited[curNode] = true;

                for (int child : tree.get(curNode)) {
                    
                    if (!visited[child]) {
                        q.offer(child);
                        parents[child] = curNode;
                    }
                }
            } 
        }
    }

}

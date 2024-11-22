import java.util.*;
import java.io.*;

public class Main {
    
    public static List<List<Integer>> results;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] base = new int[n];
        for (int i = 0; i < n; i++) {
            base[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(base);

        results = new ArrayList<>();
        boolean[] visited = new boolean[n];
        combination(visited, base, new ArrayList<>(), m);
    }
    
    public static void combination(boolean[] visited,  int[] base, List<Integer> temp, int m) {
        if (temp.size() == m) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append(temp.get(i)).append(" ");
            }
            System.out.println(sb);
            return;
        }

        for (int i = 0; i < base.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp.add(base[i]);
                combination(visited, base, temp, m);
                temp.remove(temp.size() - 1);
                visited[i] = false;
            }
        }

    }

}
import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] inCnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inCnt = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());

            graph.get(pre).add(post);
            inCnt[post]++;
        }

        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (inCnt[i] == 0) {
                queue.offer(i);
            }
        }

         StringBuilder sb = new StringBuilder();
         while (!queue.isEmpty()) {
             int cur = queue.poll();
             sb.append(cur).append(" ");
 
             for (int next : graph.get(cur)) {
                 if (--inCnt[next] == 0) {
                     queue.offer(next);
                 }
             }
         }
 
         System.out.println(sb);
    }
}
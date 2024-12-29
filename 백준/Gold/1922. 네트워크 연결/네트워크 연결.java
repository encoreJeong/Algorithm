import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static Map<Integer, List<int[]>> graph = new HashMap<>();
    static int[] uf;
    static int answer = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.putIfAbsent(weight, new ArrayList<>());
            graph.get(weight).add(new int[] { from, to });
        }

        uf = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            uf[i] = i;
        }

        List<Integer> keyList = new ArrayList<>(graph.keySet());
        keyList.sort(Comparator.naturalOrder());

        for (int weight : keyList) {
            for (int[] edge : graph.get(weight)) {
                if (find(edge[0]) == find(edge[1])) {
                    continue;
                }

                union(edge[0], edge[1]);
                answer += weight;
            }
        }

        System.out.println(answer);
    }
    
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            uf[rootA] = rootB;
        }
    }

    static int find(int a) {
        if (uf[a] != a) {
            uf[a] = find(uf[a]);
        }

        return uf[a];
    }

}
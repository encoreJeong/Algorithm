import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static boolean cycleFlag = false;
    public static boolean[] visited;
    public static int V;
    public static int E;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        Map<Integer, ArrayList<int[]>> graph = new HashMap<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());

            int[] connection = { nodeA, nodeB };

            graph.putIfAbsent(edge, new ArrayList<>());
            graph.get(edge).add(connection);
        }

        ArrayList<Integer> keys = new ArrayList<>(graph.keySet());
        Collections.sort(keys);

        int answer = 0;
        int[] root = new int[V + 1];
        for (int i = 0; i < V + 1; i++) {
            root[i] = i;
        }

        for (Integer key : keys) {
            for (int[] connection : graph.get(key)) {
                
                if (!union(root, connection[0], connection[1])) {
                    continue;
                }

                answer += key;
            }
        }

        System.out.println(answer);

    }
    
    public static boolean union(int[] root, int a, int b) {
        
        int rootA = find(root, a);
        int rootB = find(root, b);

        if (rootA == rootB) {
            return false;
        }

        root[rootA] = rootB;
        return true;
    }

    public static int find(int[] root, int a) {

        if (root[a] != a) {
            root[a] = find(root, root[a]);
        }

        return root[a];
    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] sets = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            sets[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 0) {
                if (a == b) {
                    continue;
                }

                int rootA = findRoot(sets, a);
                int rootB = findRoot(sets, b);

                if (rootA != rootB) {
                    sets[rootA] = rootB;
                }

                continue;
            }

            if (findRoot(sets, a) == findRoot(sets, b)) {
                sb.append("YES\n");
                continue;
            }

            sb.append("NO\n");
        }

        System.out.println(sb);
    }
    
    public static int findRoot(int[] sets, int value) {
        if (sets[value] != value) {
            sets[value] = findRoot(sets, sets[value]); 
        }
        return sets[value];
    }
}

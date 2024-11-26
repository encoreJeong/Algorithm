import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree.get(from).add(to);
            tree.get(to).add(from);
        }

        int leafCnt = 0;
        for (int i = 2; i < N + 1; i++) {
            if (tree.get(i).size() == 1) {
                leafCnt++;
            }
        }
        
        System.out.printf("%.6f\n", W / (double) leafCnt);
    }
}


import java.io.*;
import java.util.*;

public class Main {

    static int travelCount = -1;
    static int nodeCount = 0;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] tree = new int[N + 1][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int leftChild = Integer.parseInt(st.nextToken());
            int rightChild = Integer.parseInt(st.nextToken());

            tree[parent][0] = leftChild;
            tree[parent][1] = rightChild;
        }

        inOrderTravel(tree, 1, N);

        System.out.println(travelCount);
    }
    
    public static void inOrderTravel(int[][] tree, int nodeIdx, int N) {

        if (nodeIdx == -1) {
            return;
        }

        inOrderTravel(tree, tree[nodeIdx][0], N);

        //if (nodeCount == N) {
         //   return;
        //}

        travelCount++;
        nodeCount++;

        inOrderTravel(tree, tree[nodeIdx][1], N);
        if (nodeCount == N) {
            return;
        }

        travelCount++;
    }
}

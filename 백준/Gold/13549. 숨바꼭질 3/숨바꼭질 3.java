import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N, K));
    }

    public static int bfs(int start, int target) {
        Queue<Integer> queue = new LinkedList<>();
        int[] visitedTime = new int[100001]; 

        Arrays.fill(visitedTime, -1); 
        visitedTime[start] = 0; 

        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentTime = visitedTime[current];

            if (current == target) {
                return currentTime;
            }

            if (current * 2 <= 100000 && visitedTime[current * 2] == -1) {
                visitedTime[current * 2] = currentTime;
                queue.offer(current * 2);
            }

            if (current - 1 >= 0 && visitedTime[current - 1] == -1) {
                visitedTime[current - 1] = currentTime + 1;
                queue.offer(current - 1);
            }

            if (current + 1 <= 100000 && visitedTime[current + 1] == -1) {
                visitedTime[current + 1] = currentTime + 1;
                queue.offer(current + 1);
            }
        }

        return -1;
    }
}
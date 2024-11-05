import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M;
    public static int T;
    public static int[] answer = { Integer.MAX_VALUE, Integer.MAX_VALUE };
    public static int tempAnswer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int[][] field = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] startPos = { 0, 0 };
        bfs(field, startPos, false);
        
        System.out
                .println(Math.min(answer[0], answer[1]) == Integer.MAX_VALUE ? "Fail" : Math.min(answer[0], answer[1]));
    }

    public static void bfs(int[][] field, int[] startPos, boolean hasPower) {

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(startPos);

        visited[startPos[0]][startPos[1]] = true;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] curPos = queue.poll();
                List<int[]> neighbors = getNeighbors(curPos);

                if (curPos[0] == N - 1 && curPos[1] == M - 1) {

                    if (tempAnswer <= T) {
                        answer[0] = tempAnswer;
                    }
                    
                    return;
                }

                for (int[] e : neighbors) {

                    if (!visited[e[0]][e[1]]) {

                        if (field[e[0]][e[1]] == 1) {
                            continue;
                        }

                        if (field[e[0]][e[1]] == 2) {

                            visited[e[0]][e[1]] = true;

                            int totalTime = 1 + tempAnswer + N - 1 - e[0] + M - 1 - e[1];

                            if(totalTime <= T)
                                answer[1] = totalTime;

                            continue;
                        }

                        visited[e[0]][e[1]] = true;
                        queue.offer(e);
                    }
                }
            }

            tempAnswer++;
        }
    }

    private static List<int[]> getNeighbors(int[] curPos) {

        List<int[]> neighbors = new ArrayList<>();

        if (curPos[0] - 1 >= 0) {
            int[] neighbor = { curPos[0] - 1, curPos[1] };
            neighbors.add(neighbor);
        }

        if (curPos[0] + 1 < N) {
            int[] neighbor = { curPos[0] + 1, curPos[1] };
            neighbors.add(neighbor);
        }

        if (curPos[1] - 1 >= 0) {
            int[] neighbor = { curPos[0], curPos[1] - 1 };
            neighbors.add(neighbor);
        }

        if (curPos[1] + 1 < M) {
            int[] neighbor = { curPos[0], curPos[1] + 1 };
            neighbors.add(neighbor);
        }

        return neighbors;
    }

}

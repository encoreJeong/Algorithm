import java.util.*;
import java.io.*;

public class Main {

    public static int R;
    public static int C;
   
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] cave = new char[R][C];

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                cave[i][j] = row.charAt(j);
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boolean leftTurn = true;
        while (N-- > 0) {
            int height = Integer.parseInt(st.nextToken());
            int targetRow = R - height;
            int targetCol = C;

            //벽 부수기
            if (leftTurn) {
                for (int c = 0; c < C; c++) {
                    if (cave[targetRow][c] == 'x') {
                        cave[targetRow][c] = '.';
                        targetCol = c;
                        break;
                    }
                }
            } else {
                for (int c = C - 1; c >= 0; c--) {
                    if (cave[targetRow][c] == 'x') {
                        cave[targetRow][c] = '.';
                        targetCol = c;
                        break;
                    }
                }
            }

            leftTurn = !leftTurn;

            if (targetCol == C) {
                continue;
            }

            updateCave(cave, findCluster(cave));
        }

        print(cave);   
    }
    

    public static boolean[][] findCluster(char[][] cave) {

        int row = R - 1;
        int col = -1;
        for (int c = 0; c < C; c++) {
            if (cave[R - 1][c] == 'x') {
                row = R - 1;
                col = c;
            }
        }

        //지면에 붙어있는 클러스터가 없는 경우
        if (col == -1) {
            boolean[][] cluster = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (cave[i][j] == 'x') {
                        cluster[i][j] = true;
                    }
                }
            }
            return cluster;
        }

        //지면에 붙어있는 클러스터가 있는 경우
        boolean[][] groundCluster = new boolean[R][C];
        for (int c = 0; c < C; c++) {
            bfs(cave, groundCluster, R - 1, c);
        }

        
        boolean[][] cluster = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (cave[r][c] == 'x' && !groundCluster[r][c]) {
                    cluster[r][c] = true;
                }
            }
        }

        return cluster;
    }
    
    public static void bfs(char[][] cave, boolean[][] cluster, int row, int col) {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[] { row, col });
        cluster[row][col] = true;

        List<int[]> neighbors = new ArrayList<>();

        while (!q.isEmpty()) {

            int[] curNode = q.poll();
            neighbors = getNeighbors(cave, curNode[0], curNode[1]);

            for (int[] neighbor : neighbors) {
                if (!cluster[neighbor[0]][neighbor[1]]) {
                    q.offer(neighbor);
                    cluster[neighbor[0]][neighbor[1]] = true;
                }
            }
        }
    }

    public static List<int[]> getNeighbors(char[][] cave, int row, int col) {
        List<int[]> neighbors = new ArrayList<>();

        //상
        if (row - 1 >= 0 && cave[row - 1][col] == 'x') {
            neighbors.add(new int[]{row - 1, col});
        }
        //하
        if (row + 1 < R && cave[row + 1][col] == 'x') {
            neighbors.add(new int[]{row + 1, col});
        }
        //좌
        if (col - 1 >= 0 && cave[row][col - 1] == 'x') {
            neighbors.add(new int[]{row, col - 1});
        }
        //우
        if (col + 1 < C && cave[row][col + 1] == 'x') {
            neighbors.add(new int[]{row, col + 1});
        }

        return neighbors;
    }

    public static void updateCave(char[][] cave, boolean[][] cluster) {
        
        //내려갈 높이 찾기
        int height = Integer.MAX_VALUE;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!cluster[i][j])
                    continue;

                int tempHeight = 0;
                boolean nextIsAlsoCluster = false;

                for (int r = i + 1; r < R; r++) {
                    if (cluster[r][j]) {
                        nextIsAlsoCluster = true;
                        break;
                    }

                    if (cave[r][j] == 'x') {
                        break;
                    }

                    tempHeight++;
                }

                if (!nextIsAlsoCluster) {
                    height = Math.min(height, tempHeight);
                }

            }

        }

        if (height == Integer.MAX_VALUE) {
            return;
        }

        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (!cluster[i][j])
                    continue;
                
                cave[i][j] = '.';
                cave[i + height][j] = 'x';
            }
        }
    }
    
    public static void print(char[][] cave) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            sb.append(cave[i]);
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
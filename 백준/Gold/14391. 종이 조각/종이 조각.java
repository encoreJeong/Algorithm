import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int M;
    static int[][] arr;
    static int answer = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }

        dfs(0, new boolean[N + 1][M + 1], new int[] { 0, 0 });
        
        System.out.println(answer);
    }
    
    static void dfs(int sum, boolean[][] visited, int[] cur) {
        
        if (cur[0] >= N || cur[1] >= M) {
            answer = Math.max(answer, sum);
            return;
        }

        List<List<int[]>> combs = possibleComb(cur, visited);

        for (List<int[]> comb : combs) {
            int tempValue = 0;
            int listSize = comb.size() - 1;
            for (int[] pos : comb) {
                visited[pos[0]][pos[1]] = true;
                tempValue += Math.pow(10, listSize--) * arr[pos[0]][pos[1]];
            }
            
            //다음 탐색위치 탐색
            int[] nextPos = nextPos(cur[0], cur[1]);
            while (visited[nextPos[0]][nextPos[1]]) {
                nextPos = nextPos(nextPos[0], nextPos[1]);
            }

            dfs(sum + tempValue, visited, nextPos);

            for (int[] pos : comb) {
                visited[pos[0]][pos[1]] = false;
            }
        }
    }
    
    static int[] nextPos(int row, int col) {
        if (col + 1 >= M) {
            int nextRow = row + 1;
            int nextCol = 0;
            return new int[] { nextRow, nextCol };
        } else {
            int nextCol = col + 1;
            return new int[] { row, nextCol };
        }
    }
    
    static List<List<int[]>> possibleComb(int[] pos, boolean[][] visited) {
        List<List<int[]>> result = new ArrayList<>();

        result.add(List.of(new int[] { pos[0], pos[1] }));

        List<int[]> temp = new ArrayList<>();
        temp.add(new int[] { pos[0], pos[1] });
        for (int i = pos[0] + 1; i < N; i++) { //세로 조합
            if (visited[i][pos[1]]) {
                break;
            }

            temp.add(new int[] { i, pos[1] });
            result.add(new ArrayList<>(temp));
        }

        temp.clear();
        temp.add(new int[] { pos[0], pos[1] });
        for (int i = pos[1] + 1; i < M; i++) { //가로 조합
            if (visited[pos[0]][i]) {
                break;
            }

            temp.add(new int[] { pos[0], i });
            result.add(new ArrayList<>(temp));
        }

        return result;
    }

}
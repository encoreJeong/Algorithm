import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int[] time;
    static int[] inCount;
    static List<List<Integer>> graph = new ArrayList<>();
    static Queue<Integer> que = new ArrayDeque<>();
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        time = new int[N + 1];
        inCount = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            time[i] = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                graph.get(Integer.parseInt(st.nextToken())).add(i);
            }
            inCount[i] = count;
        }

        for (int i = 1; i < N + 1; i++) {
            if (inCount[i] == 0) {
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            int size = que.size();

            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                temp.add(que.poll());
            }

            int tempMinTime = Integer.MAX_VALUE;
            for (int target : temp) {
                tempMinTime = Math.min(tempMinTime, time[target]);
            }

            for (int target : temp) {
                time[target] -= tempMinTime;

                if (time[target] != 0) {
                    que.offer(target);
                }

                if (time[target] == 0) {
                    for (int work : graph.get(target)) {
                        if (--inCount[work] == 0) {
                            que.offer(work);
                        }
                    }
                }
            }

            answer += tempMinTime;
        }

        System.out.println(answer);
    }
}
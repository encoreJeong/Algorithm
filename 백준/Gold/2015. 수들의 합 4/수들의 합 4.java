import java.util.*;
import java.io.*;

public class Main {

    public static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 1; i < N + 1; i++) {
            answer += map.getOrDefault(arr[i] - K, 0);
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
    
        System.out.println(answer);
    }
}

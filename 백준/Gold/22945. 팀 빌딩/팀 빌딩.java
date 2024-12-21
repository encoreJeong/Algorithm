import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static int[] arr;
    static int answer = Integer.MIN_VALUE;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N - 1;
        while (start < end) {
            int synergy = calculate(start, end);
            answer = Math.max(answer, synergy);

            if (arr[start] < arr[end]) {
                start++;
            } else {
                end--;
            }
        }
        
        System.out.println(answer);
    }

    static int calculate(int idx1, int idx2) {
        return (idx2 - idx1 - 1) * Math.min(arr[idx1], arr[idx2]);
    }

}
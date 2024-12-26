import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static int d;
    static int k;
    static int c;
    static int[] arr;
    static Set<Integer> set;
    static boolean containC;
    static int answer = 0;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int various = 0;
        int[] freq = new int[d + 1];
        for (int i = 0; i < k; i++) {
            freq[arr[i]]++;
            if (freq[arr[i]] == 1) {
                various++;
            }
        }
        
        for (int i = 0; i < N + 1; i++) {

            if (freq[c] == 0) {
                answer = Math.max(various + 1, answer);
            } else {
                answer = Math.max(various, answer);
            }

            if (i == N) {
                break;
            }

            if (--freq[arr[i]] == 0) {
                various--;
            }

            if (++freq[arr[(i + k) % N]] == 1) {
                various++;
            }
        }
        
        System.out.println(answer);
    }
}
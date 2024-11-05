import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        long maxTime = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            maxTime = Math.max(maxTime, arr[i]);
        }

        long left = 1;
        long right = maxTime * M; 
        long result = right;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            long people = 0;
            for (int i = 0; i < N; i++) {
                people += mid / arr[i];
                if (people >= M) {
                    break; 
                }
            }
            
            if (people >= M) {
                result = mid; 
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        System.out.println(result);
    }
}

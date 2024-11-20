import java.util.*;
import java.io.*;

public class Main {

    public static int[] numbers;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            min = Math.min(numbers[i], min);
            max = Math.max(numbers[i], max);
        }

        int left = 0;
        int right = max - min;
        int answer = right;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (canDivide(M, mid)) {
                right = mid - 1;
                answer = mid;
                continue;
            }

            left = mid + 1;
        }

        System.out.println(answer);
    }

    public static boolean canDivide(int M, int target) {
        int currentMin = numbers[0];
        int currentMax = numbers[0];
        int groupCnt = 1;

        for (int i = 1; i < numbers.length; i++) {
            currentMin = Math.min(currentMin, numbers[i]);
            currentMax = Math.max(currentMax, numbers[i]);

            if (currentMax - currentMin > target) {
                groupCnt++;

                currentMin = numbers[i];
                currentMax = numbers[i];
            }
        }

        return groupCnt <= M;
    }

}
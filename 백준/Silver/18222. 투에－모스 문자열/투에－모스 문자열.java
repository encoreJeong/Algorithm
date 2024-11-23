import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long k = Long.parseLong(br.readLine());
        
        System.out.println(findKthChar(k));
    }

    // 재귀적으로 k번째 문자 찾기
    public static int findKthChar(long k) {
        if (k == 1) {
            return 0; // X_1의 첫 번째 문자는 항상 0
        }

        // 가장 가까운 2의 제곱수 찾기
        long length = 1; // 초기 길이
        while (2 * length < k) {
            length *= 2;
        }

        // k가 첫 번째 절반에 속하면 그대로 탐색
        if (k <= length) {
            return findKthChar(k);
        }

        // k가 두 번째 절반에 속하면 반전된 값 탐색
        return 1 - findKthChar(k - length);
    }
}

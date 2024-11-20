import java.util.*;
import java.io.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int prev = Integer.parseInt(st.nextToken());
        for (int i = 1; i < 8; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (Math.abs(cur - prev) != 1) {
                System.out.println("mixed");
                return;
            }
            prev = cur;
        }

        if (prev == 8) {
            System.out.println("ascending");
            return;
        }

        System.out.println("descending");

    }
    
}
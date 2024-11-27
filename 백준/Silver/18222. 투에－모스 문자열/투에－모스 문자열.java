import java.util.*;
import java.io.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long k = Long.parseLong(br.readLine());

        int nearestTwo = 0;
        for (int i = 0;; i++) {
            if (Math.pow(2, i) >= k) {
                break;
            }
            nearestTwo = i;
        }

        long lastHalfCount = 0;
        long newIdx = k - 1;

        for (int i = nearestTwo; i >= 0; i--) {

            if (newIdx >= (long) (Math.pow(2, i + 1) + Math.pow(2, i)) / 2) {
                lastHalfCount++;
                newIdx = newIdx - ((long) (Math.pow(2, i + 1) + (long) Math.pow(2, i)) / 2)
                        + (long) Math.pow(2, i - 1);

                continue;
            }

            newIdx = newIdx - (long) Math.pow(2, i) + (long) Math.pow(2, i - 1);
        }
        
        if (lastHalfCount % 2 == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(1);
        return;

    }
}
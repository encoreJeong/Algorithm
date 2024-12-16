import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cash = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] values = new int[14];
        for (int i = 0; i < 14; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        int jh = calculateJH(cash, values);
        int sm = calculateSM(cash, values);

        if (jh == sm) {
            System.out.println("SAMESAME");
            return;
        }

        if (jh > sm) {
            System.out.println("BNP");
            return;
        }

        System.out.println("TIMING");
    }
    
    public static int calculateJH(int cash, int[] values) {
        int stockCount = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] <= cash) {
                stockCount += cash / values[i];
                cash -= values[i] * (cash / values[i]);
            }
        }
        return cash + values[values.length - 1] * stockCount;
    }
    
    public static int calculateSM(int cash, int[] values) {
        int stockCount = 0;

        int upCount = 0;
        int downCount = 0;
        for (int i = 1; i < values.length; i++) {

            if (values[i] > values[i - 1]) {
                upCount++;
                downCount = 0;

            } else if (values[i] < values[i - 1]) {
                downCount++;
                upCount = 0;

            } else {
                upCount = 0;
                downCount = 0;
            }

            if (upCount == 3) {
                cash += stockCount * values[i];
                stockCount = 0;
                upCount--;
                continue;
            }

            if (downCount == 3) {
                if (cash >= values[i]) {
                    stockCount += (cash / values[i]);
                    cash -= values[i] * (cash / values[i]);
                }
                downCount--;
                continue;
            }
        }
        
        return cash + values[values.length - 1] * stockCount;
    }
}
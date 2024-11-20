import java.util.*;
import java.io.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int value = 0;
        int value2 = 0;
        
        String first = br.readLine();
        String second = br.readLine();
        String third = br.readLine();

        value = Integer.parseInt(first) + Integer.parseInt(second) - Integer.parseInt(third);
        value2 = Integer.parseInt(first + second) - Integer.parseInt(third);

        sb.append(value).append("\n").append(value2);

        System.out.println(sb);
    }
    
}
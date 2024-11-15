import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());
            Set<String> phoneNums = new HashSet<>();
            Set<Integer> prefixLengths = new HashSet<>();
            for (int i = 0; i < n; i++) {
                String phoneNum = br.readLine();
                phoneNums.add(phoneNum);
                prefixLengths.add(phoneNum.length());
            }
            
            boolean flag = false;
            for (String phoneNum : phoneNums) {
                for (int length : prefixLengths) {
                    if (length < phoneNum.length()) {
                        if (phoneNums.contains(phoneNum.substring(0, length))) {
                            sb.append("NO\n");
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    break;
                }
            }

            if (!flag) {
                sb.append("YES\n");
            }
        }
    
        System.out.println(sb);
    }
}

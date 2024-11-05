import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    public static void main(String args[]) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        Set<String> result = new LinkedHashSet<>();
        for (int i = 0; i < N; i++) {
            int[] charCnt = new int[26];

            for (int j = 0; j < arr[i].length(); j++) {
                charCnt[arr[i].charAt(j) - 'a']++;
            }

            char[] str = new char[arr[i].length()];

            dfs(charCnt, 0, str, result);
        }

        result.stream().forEach(System.out::println);
    }
    
    public static void dfs(int[] charCnt, int index, char[] str, Set<String> result) {

        if (index == str.length) {
            result.add(new String(str));
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (charCnt[i] > 0) {
                charCnt[i]--;
                str[index] = (char) ((char) i + 'a');
                dfs(charCnt, index + 1, str, result);
                charCnt[i]++;
            }
        }
    }
    
}

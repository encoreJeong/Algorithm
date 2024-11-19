import java.util.*;
import java.io.*;
import java.util.function.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());
            Node root = new Node();
            int leafCnt = 0;

            List<String> strs = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                strs.add(br.readLine());
            }

            strs.sort((str1, str2) -> str1.length() - str2.length());

            for (int i = 0; i < n; i++) {
                String str = strs.get(i);
                Node current = root;

                for (int j = 0; j < str.length(); j++) {
                    current.children.putIfAbsent(str.charAt(j), new Node());
                    if (current.isLeaf) {
                        leafCnt--;
                    }
                    current = current.children.get(str.charAt(j));
                }

                current.isLeaf = true;
                leafCnt++;
            }

            if (leafCnt == n) {
                sb.append("YES\n");
                continue;
            }
            
            sb.append("NO\n");

        }

        System.out.println(sb);
    }
    
    static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean isLeaf;
    }
    
}

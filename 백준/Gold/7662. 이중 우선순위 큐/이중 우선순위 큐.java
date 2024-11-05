import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {

            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if (command.equals("I")) {
                    map.put(value, map.getOrDefault(value, 0) + 1);
                } else if (command.equals("D")) {
                    if (map.isEmpty()) continue;
                    
                    if (value == 1) { 
                        int maxKey = map.lastKey();
                        if (map.get(maxKey) == 1) {
                            map.remove(maxKey);
                        } else {
                            map.put(maxKey, map.get(maxKey) - 1);
                        }
                    } else { 
                        int minKey = map.firstKey();
                        if (map.get(minKey) == 1) {
                            map.remove(minKey);
                        } else {
                            map.put(minKey, map.get(minKey) - 1);
                        }
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());

        TreeMap<Integer, TreeMap<Integer, Integer>> treeMap = new TreeMap<>();
        TreeMap<Integer, Integer> probDifficultyTree = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int probNum = Integer.parseInt(st.nextToken());
            int probDifficulty = Integer.parseInt(st.nextToken());
            
            if (probDifficultyTree.containsKey(probNum)) {
                int probDifficulty2 = probDifficultyTree.get(probNum);
                treeMap.get(probDifficulty2).remove(probNum);
                if (treeMap.get(probDifficulty2).isEmpty()) {
                    treeMap.remove(probDifficulty2);
                }
            }
            
            probDifficultyTree.put(probNum, probDifficulty);

            if (!treeMap.containsKey(probDifficulty)) {
                treeMap.put(probDifficulty, new TreeMap<>(Map.of(probNum, probNum)));
            } else {
                treeMap.get(probDifficulty).put(probNum, probNum);
            }
        }
        
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int firstValue = Integer.parseInt(st.nextToken());
            
            switch (command) {
                case "recommend":
                    if (firstValue > 0) {
                        sb.append(treeMap.get(treeMap.lastKey()).lastKey()).append("\n");
                    } else {
                        sb.append(treeMap.get(treeMap.firstKey()).firstKey()).append("\n");
                    }
                    break;

                case "add":
                    int secondValue = Integer.parseInt(st.nextToken());

                    if (probDifficultyTree.containsKey(firstValue)) {
                        int probDifficulty = probDifficultyTree.get(firstValue);
                        treeMap.get(probDifficulty).remove(firstValue);
                        if (treeMap.get(probDifficulty).isEmpty()) {
                            treeMap.remove(probDifficulty);
                        }
                    }
                    
                    probDifficultyTree.put(firstValue, secondValue);

                    if (!treeMap.containsKey(secondValue)) {
                        treeMap.put(secondValue, new TreeMap<>(Map.of(firstValue, firstValue)));
                    } else {
                        treeMap.get(secondValue).put(firstValue, firstValue);
                    }
                    break;

                case "solved":
                    
                    if (!probDifficultyTree.containsKey(firstValue)) {
                        break;
                    }

                    int probDifficulty = probDifficultyTree.get(firstValue);
                    probDifficultyTree.remove(firstValue);

                    if (!treeMap.containsKey(probDifficulty)) {
                        break;
                    }
                    
                    treeMap.get(probDifficulty).remove(firstValue);

                    if (treeMap.get(probDifficulty).isEmpty()) {
                        treeMap.remove(probDifficulty);
                    }
                
            }
        }
        
        System.out.println(sb);
    }
    
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

    
}

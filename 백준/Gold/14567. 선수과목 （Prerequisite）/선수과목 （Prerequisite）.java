import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> courses = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            courses.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            courses.get(b).add(a);
        }

        int[] fastest = new int[N + 1];
        
        for (int i = 1; i < N + 1; i++) {
            findFastest(courses, fastest, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            sb.append(fastest[i]).append(" ");
        }

        System.out.println(sb);
    }
    
    public static void findFastest(ArrayList<ArrayList<Integer>> courses, int[] fastest, int courseNum) {
        
        if (courses.get(courseNum).isEmpty()) {
            fastest[courseNum] = 1;
            return;
        }
        
        int max = Integer.MIN_VALUE;
        for (int preCourse : courses.get(courseNum)) {
            if (fastest[preCourse] == 0) {
                findFastest(courses, fastest, preCourse);
            }

            max = Math.max(fastest[preCourse], max);
        }
        
        fastest[courseNum] = max + 1;
    }
}
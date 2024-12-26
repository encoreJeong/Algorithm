import java.util.*;
import java.io.*;

class Main {

    static int T;
    static int n;
    static List<Integer> preOrder;
    static List<Integer> inOrder;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());

            preOrder = new ArrayList<>();
            inOrder = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preOrder.add(Integer.parseInt(st.nextToken()));
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inOrder.add(Integer.parseInt(st.nextToken()));
            }

            Node rootNode = makeBT(preOrder, inOrder);
            StringBuilder sb = new StringBuilder();
            
            rootNode.postOrderTravel(sb);
            answer.append(sb).append("\n");
        }

        System.out.println(answer);
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public void postOrderTravel(StringBuilder sb) {
            if(left != null) {
                left.postOrderTravel(sb);
            }
            
            if(right != null) {
                right.postOrderTravel(sb);
            }
            
            sb.append(value).append(" ");
        }
    }
    
    public static Node makeBT(List<Integer> preOrder, List<Integer> inOrder) {

        if (preOrder.isEmpty()) {
            return null;
        }

        int value = preOrder.get(0);
        Node parent = new Node();
        parent.value = value;

        int parentIdx = inOrder.indexOf(value); 
        parent.left = makeBT(new ArrayList<Integer>(preOrder.subList(1, parentIdx + 1)), new ArrayList<Integer>(inOrder.subList(0, parentIdx)));
        parent.right = makeBT(new ArrayList<Integer>(preOrder.subList(parentIdx + 1, preOrder.size())), new ArrayList<Integer>(inOrder.subList(parentIdx + 1, preOrder.size())));

        return parent;
    }
}

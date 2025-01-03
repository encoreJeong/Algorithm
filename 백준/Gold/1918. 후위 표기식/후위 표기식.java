import java.io.*;
import java.util.*;

class Main {
    static String exp;
    static int idx = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        exp = br.readLine();

        String answer = recursive(new StringBuilder());

        System.out.println(answer);
    }
    
    static String recursive(StringBuilder tempBuilder) {
        
        ArrayDeque<String> valStack = new ArrayDeque<>();
        ArrayDeque<String> opStack = new ArrayDeque<>();

        for (; idx < exp.length();) {

            if (exp.charAt(idx) == '(') {
                idx++;
                String recursiveStr = recursive(new StringBuilder());
                valStack.offerFirst(recursiveStr);

                if (opStack.peek() != null && (opStack.peek().equals("*") || opStack.peek().equals("/"))) {

                    String v2 = valStack.pollFirst();
                    String op = opStack.pollFirst();
                    String v1 = valStack.pollFirst();

                    valStack.offerFirst(v1 + v2 + op);
                }
                
                continue;
            }

            if (exp.charAt(idx) == ')') {
                idx++;

                if (valStack.isEmpty()) {
                    return "";
                }

                tempBuilder.append(valStack.pollLast());
        
                while (!valStack.isEmpty()) {
                    tempBuilder.append(valStack.pollLast());

                    if(!opStack.isEmpty()) {
                        tempBuilder.append(opStack.pollLast());
                    }
                }
                
                return tempBuilder.toString();
            }

            if (Character.isAlphabetic(exp.charAt(idx))) {

                valStack.offerFirst(String.valueOf(exp.charAt(idx)));

                if (opStack.peek() != null && (opStack.peek().equals("*") || opStack.peek().equals("/"))) {

                    String v2 = valStack.pollFirst();
                    String op = opStack.pollFirst();
                    String v1 = valStack.pollFirst();

                    valStack.offerFirst(v1 + v2 + op);
                }
                
                idx++;
                continue;
            }

            opStack.offerFirst(String.valueOf(exp.charAt(idx)));
            idx++;
        }

        tempBuilder.append(valStack.pollLast());

        while (!valStack.isEmpty()) {
            tempBuilder.append(valStack.pollLast());

            if(!opStack.isEmpty()) {
                tempBuilder.append(opStack.pollLast());
            }
        }
        
        return tempBuilder.toString();
    } 
}
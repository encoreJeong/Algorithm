import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
                
        Set<Integer> lenSet = new LinkedHashSet<>();
        Map<String, ArrayList<String>> map = new HashMap<>();
        
        for(int i = 0; i < phone_book.length; i++) {
            lenSet.add(phone_book[i].length());
        }
        
        for(int i = 0; i < phone_book.length; i++) {
            map.putIfAbsent(phone_book[i], new ArrayList<>());
            for(Integer e : lenSet) {
                if(phone_book[i].length() >= e) {
                    String curStr = phone_book[i].substring(0, e);
                    if(curStr.length() != phone_book[i].length())
                        map.get(phone_book[i]).add(curStr);
                }
            }
        }
        
        // map.keySet().forEach(key -> {
        //     System.out.printf("key %s: ", key);
        //     map.get(key).forEach(str -> {
        //         System.out.printf(" %s ", str);
        //     });
        //     System.out.println();
        // });
        
        
        for(List<String> values : map.values()) {
            for(String value : values) {
                if(map.get(value) != null) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
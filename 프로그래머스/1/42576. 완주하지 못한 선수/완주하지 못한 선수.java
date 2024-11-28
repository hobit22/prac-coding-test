import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> map = new HashMap<>();
        for (String name : participant) {
            map.putIfAbsent(name, 0);
            map.put(name, map.get(name) + 1);
        }
        
        for (String name : completion) {
            int v = map.get(name) - 1;
            map.put(name, v);
            if (v == 0) map.remove(name);
            
        }
        
        return map.keySet().iterator().next();
    }
}
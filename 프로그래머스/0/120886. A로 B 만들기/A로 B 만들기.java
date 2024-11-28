import java.util.*;

class Solution {
    public int solution(String before, String after) {
        return makeMap(before).equals(makeMap(after)) ? 1 : 0;
    }
    
    public Map<Character, Integer> makeMap(String str) {
        
        Map<Character, Integer> map = new HashMap<>();
        
        for (char c : str.toCharArray()) {
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }
        
        return map;
        
    }
}
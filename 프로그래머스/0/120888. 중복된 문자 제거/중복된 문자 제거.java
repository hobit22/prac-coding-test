import java.util.*;

class Solution {
    public String solution(String my_string) {
        String answer = "";
        Set<Character> set = new HashSet<>();
        
        StringBuilder sb = new StringBuilder();
        
        for (char c : my_string.toCharArray()) {
            if (set.contains(c)) continue;
            set.add(c);
            sb.append(c);
        }
        
        return sb.toString();
    }
}
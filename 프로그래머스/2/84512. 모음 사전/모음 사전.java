import java.util.*;

class Solution {
    private static final char[] CHARS = "AEIOU".toCharArray();
    
    public int solution(String word) {
        int answer = 0;
        return gen("").indexOf(word);
    }
    
    private List<String> gen(String word) {
        List<String> words = new ArrayList<>();
        words.add(word);
        
        if (word.length() == 5) return words;
        
        for (char c : CHARS) {
            words.addAll(gen(word + c));
        }
        
        return words;
    }
}
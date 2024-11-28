import java.util.*;
import java.util.Collections;
import java.util.function.Consumer;

class Solution {
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> scoreMap = makeMap(info);
        
        int[] answer = new int[query.length];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = count(query[i], scoreMap);
        }
        
        return answer;
    }
    
    public int count(String query, Map<String, List<Integer>> scoreMap) {
        String[] tokens = query.split(" (and )?");
        
        String key = String.join("", Arrays.copyOf(tokens, tokens.length - 1));
        if (!scoreMap.containsKey(key)) return 0;
        
        List<Integer> scores = scoreMap.get(key);
        
        int score = Integer.parseInt(tokens[tokens.length - 1]);
        
        return scores.size() - binarySearch(score, scores);
    }
    
    public int binarySearch(int score, List<Integer> scores) {
        int start = 0;
        int end = scores.size() - 1;
        
        while (end > start) {
            int mid = (start + end) / 2;
            
            if (scores.get(mid) >= score) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        
        if (scores.get(start) < score) {
            return scores.size();
        }
        return start;
    }
    
    public Map<String, List<Integer>> makeMap(String[] info) {
        
        Map<String, List<Integer>> scoreMap = new TreeMap<>();
        
        for (String s : info) {
            String[] tokens = s.split(" ");
            int score = Integer.parseInt(tokens[tokens.length - 1]);
            
            makeKey(0, "", tokens, key-> {
                scoreMap.putIfAbsent(key, new ArrayList<>());
                scoreMap.get(key).add(score);
            });
            
        }
        for(List<Integer> list : scoreMap.values()) {
            Collections.sort(list);
        }
        
        // scoreMap.forEach((key, value) -> {
        //     System.out.printf("%s %s\n", key, value.toString());
        // });
        
        return scoreMap;
    }
    
    public void makeKey(int index, String prefix, String[] tokens, Consumer<String> action) {
        if (index == tokens.length -1) {
            action.accept(prefix);
            return;
        }
        
        makeKey(index + 1, prefix + tokens[index], tokens, action);
        makeKey(index + 1, prefix + "-", tokens, action);
        
    }
    
}
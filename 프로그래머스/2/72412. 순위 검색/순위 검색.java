import java.util.*;
import java.util.Collections;
import java.util.function.Consumer;


class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        Map<String, List<Integer>> map = createScoreMap(info);
        
        for (int i = 0; i < answer.length ; i++) {
            answer[i] = count(query[i], map);
        }
        
        
        return answer;
    }
    
    public Map<String, List<Integer>> createScoreMap(String[] info) {
        Map<String, List<Integer>> map = new HashMap<>();
        
        for (String str : info) {
            String[] tokens = str.split(" ");
            int score = Integer.parseInt(tokens[tokens.length - 1]);
            
            // tokens 로 query 조건 만들기
            makeQuery(0, "", tokens, key -> {
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(score);
            });
        }
        
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }
        
        return map;   
    }
    
    private void makeQuery(int index, String prefix, String[] tokens, Consumer<String> action) {
        if (index == tokens.length - 1) {
            action.accept(prefix);
            return;
        }
        
        makeQuery(index + 1, prefix + tokens[index], tokens, action);
        makeQuery(index + 1, prefix + "-", tokens, action);
    }
    
    private int count(String query, Map<String, List<Integer>> map) {
        String[] tokens = query.split(" (and )?");
        String key = String.join("", Arrays.copyOf(tokens, tokens.length - 1));
        
        if (!map.containsKey(key)) return 0;
        
        List<Integer> scores = map.get(key);
        
        int score = Integer.parseInt(tokens[tokens.length - 1]);
        
        int result = binarySearch(score, scores);
        
        return scores.size() - result;
    }
    
    private int binarySearch(int score, List<Integer> scores) {
        
        // 이진탐색
        int start = 0; // 포함
        int end = scores.size() - 1; // 포함
        
        while (start < end) {
            int mid = (start + end) / 2;
            
            if (scores.get(mid) >= score) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        
        if (scores.get(start) < score) return scores.size();
        
        return start;
        
    }
}
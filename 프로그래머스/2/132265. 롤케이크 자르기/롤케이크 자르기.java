import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Map<Integer, Integer> rightMap = new HashMap<>();
        Set<Integer> leftSet = new HashSet<>();
        
        // 오른쪽: 처음에 전체 다 넣기
        for (int t : topping) {
            rightMap.put(t, rightMap.getOrDefault(t, 0) + 1);
        }
        
        for (int t : topping) {
            // 왼쪽에 추가
            leftSet.add(t);
            
            // 오른쪽에서 하나 빼기
            rightMap.put(t, rightMap.get(t) - 1);
            if (rightMap.get(t) == 0) {
                rightMap.remove(t);
            }
            
            // 종류 수 비교
            if (leftSet.size() == rightMap.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}

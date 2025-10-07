import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        
        boolean[] visited = new boolean[cards.length];
        
        List<Integer> boxCountList = new ArrayList<>();
        
        for (int i = 0; i < cards.length; i++) {
            if (visited[i]) continue;
            Queue<Integer> queue = new LinkedList<>();
            visited[i] = true;
            queue.add(i);
            int count = 1;
            
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int next = cards[cur] - 1;

                if (visited[next]) continue;
                count++;
                visited[next] = true;
                queue.add(next);
            }
            
            boxCountList.add(count);
        }
        
        if (boxCountList.size() == 1) return 0;
        
        return boxCountList.stream()
            .sorted(Comparator.reverseOrder())
            .limit(2)
            .reduce(1, (a, b) -> a * b);
    }
}
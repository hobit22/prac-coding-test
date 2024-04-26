import java.util.*;

class Solution {
    static int[] cost;
    List<Integer>[] graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new List[n + 1];
        
        for(int i = 1; i <= n; i++)
            graph[i] = new ArrayList();
        
        for (int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        cost = new int[n + 1];
        
        Arrays.fill(cost, -1);
        
        bfs(destination);
        
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++)
            answer[i] = cost[sources[i]];
        
        return answer;
    }
    
    public void bfs(int start) {
        Queue<Integer> queue = new LinkedList();
        
        queue.add(start);
        cost[start] = 0;
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int i = 0; i < graph[now].size(); i++) {
                int next = graph[now].get(i);
                if (cost[next] == -1) {
                    cost[next] = cost[now] + 1;
                    queue.add(next);
                }
            }
        }
    }
}
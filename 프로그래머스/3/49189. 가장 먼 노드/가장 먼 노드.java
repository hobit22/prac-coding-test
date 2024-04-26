import java.util.*;

class Solution {
    static int[] cost;
    List<Integer>[] graph;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        graph = new List[n+1];
        for (int i = 1; i <=n; i++) 
            graph[i] = new ArrayList();
        
        for (int[] node: edge) {
            graph[node[0]].add(node[1]);
            graph[node[1]].add(node[0]);
        }
        
        cost = new int[n + 1];
        Arrays.fill(cost, 0);
        
        bfs(1);
        
        int max = 0;
        for(int a : cost) {
            max = Math.max(a, max);
        }
        
        for(int a : cost) {
            if (a == max) answer++;
        }
        
        
        return answer;
    }
    
    public void bfs(int start) {
        Queue<Integer> q = new LinkedList();
        
        q.add(start);
        cost[start] = 1;
        
        
        while (!q.isEmpty()) {
            int now = q.poll();
            
            for (int i = 0; i < graph[now].size(); i++) {
                int next = graph[now].get(i);
                if (cost[next] == 0) {
                    cost[next] = cost[now] + 1;
                    q.add(next);
                }
            }
        }
    }
}


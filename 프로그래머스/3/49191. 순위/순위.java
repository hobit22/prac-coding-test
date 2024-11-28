class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        boolean[][] graph = new boolean[n][n];
        
        for(int[] result: results) {
            int win = result[0] - 1;
            int lose = result[1] - 1;
            
            graph[win][lose] = true;
        }
        
        int count = 0;
        for (int u = 0; u < n; u++) {
            int wins = countF(u, graph, new boolean[n]) - 1;
            int loses = countB(u, graph, new boolean[n]) - 1;
            
            if (wins + loses + 1 == n) {
                count++;
            }
        }
        
        return count;
    }
    
    public int countF(int u, boolean[][] graph, boolean[] visited) {
        int count = 1;
        
        for (int v = 0; v < graph[u].length; v++) {
            if (!graph[u][v] || visited[v]) continue;
            visited[v] = true;
            count += countF(v, graph, visited);
        }
        
        return count;
    }
    
    public int countB(int u, boolean[][] graph, boolean[] visited) {
        int count = 1;
        
        for (int v = 0; v < graph.length; v++) {
            if (!graph[v][u] || visited[v]) continue;
            visited[v] = true;
            count += countB(v, graph, visited);
        }    
        return count;
    }
}
import java.util.*;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        List<Node>[] list = new ArrayList[n + 1];
        // init
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        // setting
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int cost = fare[2];
            
            list[u].add(new Node(v, cost));
            list[v].add(new Node(u, cost));
        }
        
        int[] sArr = dijkstra(s, n, list);
        int[] aArr = dijkstra(a, n, list);
        int[] bArr = dijkstra(b, n, list);
        
        int min = Integer.MAX_VALUE;
        
        for (int i = 1; i <= n; i++) {
            int sum = sArr[i] + aArr[i] + bArr[i];
            if (min > sum) {
                min = sum;
            }
        }
        
        return min;
    }
    
    public void printArr(int[] arr) {
        for (int num : arr) {
            System.out.printf("%d ", num);
        }
        System.out.println();
        
    }
    
    public static int[] dijkstra(int start, int n, List<Node>[] list) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[start] = 0;
        q.add(new Node(start, 0));
        
        while(!q.isEmpty()) {
            Node now = q.poll();
            
            if (visited[now.to]) continue;
            visited[now.to] = true;
            
            for (Node next : list[now.to]) {
                if (visited[next.to] || dist[next.to] <= now.cost + next.cost) continue;
                
                dist[next.to] = now.cost + next.cost;
                q.add(new Node(next.to, now.cost + next.cost));
            }
        }
        
        return dist;
    }
    
    static class Node implements Comparable<Node> {
        int to;
        int cost;
        
        Node (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
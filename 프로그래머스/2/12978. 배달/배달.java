import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        List<List<Node>> graph = new ArrayList<>();
        
        for (int i = 0; i <= N; i ++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        
        // 거리 배열 초기화 (무한대)
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0; // 시작 마을(1번)

        // 우선순위 큐 (거리 기준 오름차순)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int current = now.index;
            int cost = now.cost;

            if (dist[current] < cost) continue;

            for (Node next : graph.get(current)) {
                int newCost = cost + next.cost;
                if (newCost < dist[next.index]) {
                    dist[next.index] = newCost;
                    pq.offer(new Node(next.index, newCost));
                }
            }
        }

        // K 이하인 마을 개수 세기
        int answer = 0;
        for (int d : dist) {
            if (d <= K) answer++;
        }

        return answer;
    }
    
    static class Node implements Comparable<Node> {
        int index, cost;
        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
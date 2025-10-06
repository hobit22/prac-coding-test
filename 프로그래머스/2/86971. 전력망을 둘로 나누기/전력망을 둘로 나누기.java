import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        // 1. 그래프 구성
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] w : wires) {
            int a = w[0];
            int b = w[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int minDiff = Integer.MAX_VALUE;

        // 2. 각 간선을 하나씩 끊어서 탐색
        for (int[] w : wires) {
            int a = w[0];
            int b = w[1];

            // a ↔ b 연결을 끊은 뒤 a 쪽 서브트리 크기 구하기
            int count = bfsCount(a, a, b, graph, n);

            // 두 전력망의 송전탑 차이
            int diff = Math.abs((n - count) - count);
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;
    }

    // 3. BFS로 송전탑 개수 세기
    private int bfsCount(int start, int cutA, int cutB, List<List<Integer>> graph, int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        int count = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                // 끊은 간선은 무시
                if ((now == cutA && next == cutB) || (now == cutB && next == cutA)) continue;
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    count++;
                }
            }
        }

        return count;
    }
}
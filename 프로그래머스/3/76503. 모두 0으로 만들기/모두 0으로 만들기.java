import java.util.*;

class Solution {
    long total = 0;
    long answer = 0;
    boolean[] visited;
    List<Integer>[] graph;
    int[] a;

    public long solution(int[] a, int[][] edges) {
        this.a = a;

        // 1. 합이 0이 아니면 불가능
        long sum = 0;
        for (int val : a) sum += val;
        if (sum != 0) return -1;

        int n = a.length;
        visited = new boolean[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        dfs(0);

        return answer;
    }

    // DFS 후위 순회
    private long dfs(int node) {
        visited[node] = true;
        long weight = a[node];

        for (int next : graph[node]) {
            if (!visited[next]) {
                weight += dfs(next); // 자식 노드의 가중치를 부모로 끌어올림
            }
        }

        answer += Math.abs(weight); // 연산 횟수는 절댓값만큼 필요
        return weight;
    }
}
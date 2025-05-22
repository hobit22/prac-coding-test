import java.util.*;

class Solution {
    int maxSheep = 0;
    List<Integer>[] graph;

    public int solution(int[] info, int[][] edges) {
        // 1. 그래프 구성
        graph = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        // 2. DFS 탐색 시작
        dfs(0, 0, 0, new ArrayList<>(), info);

        return maxSheep;
    }

    private void dfs(int node, int sheep, int wolf, List<Integer> nextNodes, int[] info) {
        // 현재 노드 정보 반영
        if (info[node] == 0) sheep++;
        else wolf++;

        // 양이 늑대보다 적거나 같으면 종료
        if (wolf >= sheep) return;

        // 최대 양 수 업데이트
        maxSheep = Math.max(maxSheep, sheep);

        // 방문 가능한 노드 갱신
        List<Integer> candidates = new ArrayList<>(nextNodes);
        candidates.addAll(graph[node]);
        candidates.remove((Integer) node); // 현재 노드는 제거

        // 다음 가능한 노드로 DFS
        for (int next : candidates) {
            dfs(next, sheep, wolf, candidates, info);
        }
    }
}
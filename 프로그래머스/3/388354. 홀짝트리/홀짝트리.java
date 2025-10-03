import java.util.*;

class Solution {
    static Map<Integer, List<Integer>> graph;
    static int[] parityChild;
    static Set<Integer> treeNodes;
    static int badCountOddEven, badCountRevOddEven;
    static boolean canOddEvenTree, canRevOddEvenTree;

    public int[] solution(int[] nodes, int[][] edges) {
        graph = new HashMap<>();
        for (int node : nodes) graph.put(node, new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        Set<Integer> visited = new HashSet<>();
        int oddEvenCount = 0;
        int revOddEvenCount = 0;

        for (int node : nodes) {
            if (!visited.contains(node)) {
                // 연결 요소 = 트리 하나
                treeNodes = new HashSet<>();
                buildTree(node, -1, visited);

                parityChild = new int[1_000_001];
                dfs1(node, -1);

                // 초기 badCount 계산
                badCountOddEven = 0;
                badCountRevOddEven = 0;
                for (int nd : treeNodes) {
                    int np = nd % 2;
                    int cp = parityChild[nd];
                    if (np != cp) badCountOddEven++;
                    if (np == cp) badCountRevOddEven++;
                }

                canOddEvenTree = false;
                canRevOddEvenTree = false;

                dfs2(node, -1);

                if (canOddEvenTree) oddEvenCount++;
                if (canRevOddEvenTree) revOddEvenCount++;
            }
        }

        return new int[]{oddEvenCount, revOddEvenCount};
    }

    // 연결 요소 탐색
    private void buildTree(int cur, int parent, Set<Integer> visited) {
        visited.add(cur);
        treeNodes.add(cur);
        for (int nxt : graph.get(cur)) {
            if (nxt != parent && !visited.contains(nxt)) {
                buildTree(nxt, cur, visited);
            }
        }
    }

    // 자식 개수 홀짝 계산
    private void dfs1(int cur, int parent) {
        int cnt = 0;
        for (int nxt : graph.get(cur)) {
            if (nxt != parent && treeNodes.contains(nxt)) {
                dfs1(nxt, cur);
                cnt++;
            }
        }
        parityChild[cur] = cnt % 2;
    }

    // rerooting
    private void dfs2(int cur, int parent) {
        // 현재 루트일 때 판정
        if (badCountOddEven == 0) canOddEvenTree = true;
        if (badCountRevOddEven == 0) canRevOddEvenTree = true;

        for (int nxt : graph.get(cur)) {
            if (nxt != parent && treeNodes.contains(nxt)) {
                // --- cur, nxt parity 반전 전 상태 기억 ---
                int curBefore = parityChild[cur];
                int nxtBefore = parityChild[nxt];

                // badCount 업데이트 (기존 상태 제거)
                int npCur = cur % 2, npNxt = nxt % 2;
                if (npCur != parityChild[cur]) badCountOddEven--;
                else badCountRevOddEven--;
                if (npNxt != parityChild[nxt]) badCountOddEven--;
                else badCountRevOddEven--;

                // parity 반전
                parityChild[cur] ^= 1;
                parityChild[nxt] ^= 1;

                // badCount 업데이트 (새 상태 반영)
                if (npCur != parityChild[cur]) badCountOddEven++;
                else badCountRevOddEven++;
                if (npNxt != parityChild[nxt]) badCountOddEven++;
                else badCountRevOddEven++;

                dfs2(nxt, cur);

                // --- 원상 복구 ---
                // 새 상태 제거
                if (npCur != parityChild[cur]) badCountOddEven--;
                else badCountRevOddEven--;
                if (npNxt != parityChild[nxt]) badCountOddEven--;
                else badCountRevOddEven--;

                // parity 복구
                parityChild[cur] = curBefore;
                parityChild[nxt] = nxtBefore;

                // 옛 상태 복구
                if (npCur != parityChild[cur]) badCountOddEven++;
                else badCountRevOddEven++;
                if (npNxt != parityChild[nxt]) badCountOddEven++;
                else badCountRevOddEven++;
            }
        }
    }
}
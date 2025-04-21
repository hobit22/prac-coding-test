import java.util.Arrays;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int k = info.length;
        int INF = 1000000;

        // dp[i][a] = i번째까지 고려했을 때 A의 흔적이 a일 경우 B의 최소 흔적
        int[][] dp = new int[k + 1][n];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0; // 초기 상태: 물건 0개, A와 B 모두 흔적 0

        for (int i = 0; i < k; i++) {
            int aTrace = info[i][0];
            int bTrace = info[i][1];

            for (int a = 0; a < n; a++) {
                if (dp[i][a] == INF) continue;

                // A가 i번째 물건을 훔치는 경우
                if (a + aTrace < n) {
                    dp[i + 1][a + aTrace] = Math.min(dp[i + 1][a + aTrace], dp[i][a]);
                }

                // B가 i번째 물건을 훔치는 경우
                if (dp[i][a] + bTrace < m) {
                    dp[i + 1][a] = Math.min(dp[i + 1][a], dp[i][a] + bTrace);
                }
            }
        }

        // 마지막 dp[k][a] 중에서 B가 m 미만일 때 A의 흔적 최소 찾기
        int minA = -1;
        for (int a = 0; a < n; a++) {
            if (dp[k][a] < m) {
                if (minA == -1 || a < minA) {
                    minA = a;
                }
            }
        }

        return minA;
    }
}

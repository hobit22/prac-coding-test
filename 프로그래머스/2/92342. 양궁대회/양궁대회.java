class Solution {
    int maxDiff = 0;
    int[] answer = {-1};

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[11];
        dfs(0, n, info, ryan);
        return answer;
    }

    // DFS 탐색
    public void dfs(int idx, int arrowsLeft, int[] info, int[] ryan) {
        if (idx == 11) {
            if (arrowsLeft > 0)
                ryan[10] += arrowsLeft; // 남은 화살은 0점에 몰빵

            int diff = calculateDiff(info, ryan);

            if (diff > 0) {
                if (diff > maxDiff) {
                    maxDiff = diff;
                    answer = ryan.clone();
                } else if (diff == maxDiff) {
                    // 낮은 점수를 더 많이 맞힌 경우로 갱신
                    for (int i = 10; i >= 0; i--) {
                        if (ryan[i] > answer[i]) {
                            answer = ryan.clone();
                            break;
                        } else if (ryan[i] < answer[i]) {
                            break;
                        }
                    }
                }
            }

            if (arrowsLeft > 0)
                ryan[10] -= arrowsLeft; // 원복

            return;
        }

        // (1) 어피치를 이기기 위해 (info[idx] + 1) 발 쏘기
        if (arrowsLeft > info[idx]) {
            ryan[idx] = info[idx] + 1;
            dfs(idx + 1, arrowsLeft - (info[idx] + 1), info, ryan);
            ryan[idx] = 0; // 원복
        }

        // (2) 포기 (0발)
        dfs(idx + 1, arrowsLeft, info, ryan);
    }

    // 점수 차이 계산
    public int calculateDiff(int[] info, int[] ryan) {
        int aScore = 0, rScore = 0;

        for (int i = 0; i < 11; i++) {
            int score = 10 - i;
            if (info[i] == 0 && ryan[i] == 0) continue;

            if (ryan[i] > info[i])
                rScore += score;
            else
                aScore += score;
        }

        return rScore - aScore;
    }
}
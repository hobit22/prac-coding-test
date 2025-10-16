class Solution {
    int N;
    int[][] board;
    int minOps = Integer.MAX_VALUE;
    int[][] dirs = {{0,0},{1,0},{-1,0},{0,1},{0,-1}};
    
    public int solution(int[][] clockHands) {
        N = clockHands.length;
        board = clockHands;
        dfsFirstRow(0, new int[N]);
        return minOps;
    }

    // 첫 번째 행의 조작 패턴 완전탐색
    void dfsFirstRow(int col, int[] firstOps) {
        if (col == N) {
            simulate(firstOps);
            return;
        }
        for (int i = 0; i < 4; i++) {
            firstOps[col] = i;
            dfsFirstRow(col + 1, firstOps);
        }
    }

    // 주어진 첫 행 조작 패턴으로 시뮬레이션
    void simulate(int[] firstOps) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) 
                tmp[i][j] = board[i][j];
        
        int ops = 0;

        // 첫 행 조작
        for (int j = 0; j < N; j++) {
            ops += firstOps[j];
            rotate(tmp, 0, j, firstOps[j]);
        }

        // 두 번째 행부터 자동 결정
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int need = (4 - tmp[i - 1][j]) % 4;
                ops += need;
                rotate(tmp, i, j, need);
            }
        }

        // 마지막 행 검사
        for (int j = 0; j < N; j++) {
            if (tmp[N - 1][j] != 0) return;
        }

        minOps = Math.min(minOps, ops);
    }

    void rotate(int[][] tmp, int x, int y, int cnt) {
        for (int[] d : dirs) {
            int nx = x + d[0], ny = y + d[1];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            tmp[nx][ny] = (tmp[nx][ny] + cnt) % 4;
        }
    }
}
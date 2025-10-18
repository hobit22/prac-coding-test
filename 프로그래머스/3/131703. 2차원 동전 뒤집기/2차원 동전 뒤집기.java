class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int n = beginning.length;    // 행의 개수
        int m = beginning[0].length; // 열의 개수
        int minFlips = Integer.MAX_VALUE;
        
        // 행을 뒤집는 모든 경우의 수를 비트마스크로 표현 (2^n 가지)
        for (int rowMask = 0; rowMask < (1 << n); rowMask++) {
            // 현재 행 뒤집기 조합을 적용한 중간 상태
            int[][] current = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    current[i][j] = beginning[i][j];
                    // i번째 행을 뒤집는 경우
                    if ((rowMask & (1 << i)) != 0) {
                        current[i][j] = 1 - current[i][j];
                    }
                }
            }
            
            // 각 열에 대해 뒤집어야 하는지 확인
            int colFlips = 0;
            boolean possible = true;
            
            for (int j = 0; j < m; j++) {
                // j번째 열의 첫 번째 원소로 뒤집기 여부 결정
                boolean needFlip = (current[0][j] != target[0][j]);
                
                // 이 결정이 해당 열의 모든 원소에 일관되게 적용되는지 확인
                for (int i = 0; i < n; i++) {
                    int expected = needFlip ? (1 - current[i][j]) : current[i][j];
                    if (expected != target[i][j]) {
                        possible = false;
                        break;
                    }
                }
                
                if (!possible) break;
                if (needFlip) colFlips++;
            }
            
            if (possible) {
                // 행 뒤집기 횟수 계산
                int rowFlips = Integer.bitCount(rowMask);
                minFlips = Math.min(minFlips, rowFlips + colFlips);
            }
        }
        
        return minFlips == Integer.MAX_VALUE ? -1 : minFlips;
    }
}
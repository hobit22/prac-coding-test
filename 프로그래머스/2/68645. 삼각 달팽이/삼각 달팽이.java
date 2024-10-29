class Solution {
    private static final int[] dx = {0, 1, -1};
    private static final int[] dy = {1, 0, -1};
    
    public int[] solution(int n) {
        int[][] tower = new int[n][n];
        
        int direction = 0;
        int x = 0;
        int y = 0;
        int num = 1;
        
        while (true) {
            tower[y][x] = num++;
            
            int nextX = x + dx[direction];
            int nextY = y + dy[direction];
            
            // 끝인지 확인
            if (nextX == n || nextY == n || nextX == -1 || nextY == -1 || tower[nextY][nextX] != 0) {
                
                // 방향 바꿈
                direction = (direction + 1) % 3;
                nextX = x + dx[direction];
                nextY = y + dy[direction];
                
                // 종료 조건
                if (nextX == n || nextY == n || nextX == -1 || nextY == -1 || tower[nextY][nextX] != 0) break;
            }
            
            x = nextX;
            y = nextY;
        }
        
        int[] answer = new int[num - 1];
        
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[index++] = tower[i][j];
            }
        }
        
        return answer;
    }
}
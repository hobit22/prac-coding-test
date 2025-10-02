import java.util.*;

class Solution {
    
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int[][] board) {
        int n = board.length;
        int[][][] cost = new int[n][n][4];
        
        for (int[][] arr : cost) {
            for (int[] a : arr) {
                Arrays.fill(a, Integer.MAX_VALUE);
            }
        }
        
        Queue<int[]> q = new LinkedList<>();

        if (board[0][1] == 0) {
            cost[0][1][1] = 100;
            q.add(new int[]{0,1,1,100});
        }
        if (board[1][0] == 0) {
            cost[1][0][2] = 100;
            q.add(new int[]{1,0,2,100});
        }
        
        int answer = Integer.MAX_VALUE;
        
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int dir = current[2];
            int c = current[3];
            
            if (x == n - 1 && y == n - 1) { // 도착
                answer = Math.min(answer, c);
                continue; // 최솟값이라는 보장이 없으니 계속 loop 
            }
            
            for (int nd = 0; nd < 4; nd++) {
                int nx = x + dx[nd];
                int ny = y + dy[nd];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (board[nx][ny] == 1) continue;
                
                int nc = c + (dir == nd ? 100 : 600);
                
                if (cost[nx][ny][nd] > nc) {
                    cost[nx][ny][nd] = nc;
                    q.add(new int[]{nx, ny, nd, nc});
                }
            }
        }   
        
        return answer;
    }
}
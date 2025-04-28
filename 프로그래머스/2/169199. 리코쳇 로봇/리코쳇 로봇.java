import java.util.*;

class Solution {
    static class Point {
        int x, y, moves;
        
        Point(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }

    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        boolean[][] visited = new boolean[n][m];
        
        int startX = 0, startY = 0;
        
        // 1. 시작 위치(R) 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    startX = i;
                    startY = j;
                    break;
                }
            }
        }
        
        // 2. BFS 준비
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;
        
        // 상, 하, 좌, 우 방향
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            
            // 목표지점(G) 도착
            if (board[current.x].charAt(current.y) == 'G') {
                return current.moves;
            }
            
            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nx = current.x;
                int ny = current.y;
                
                // 미끄러지기 시작
                while (true) {
                    int nextX = nx + dx[dir];
                    int nextY = ny + dy[dir];
                    
                    // 게임판 범위 밖이거나 장애물(D) 만나면 멈춘다
                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || board[nextX].charAt(nextY) == 'D') {
                        break;
                    }
                    
                    // 계속 이동
                    nx = nextX;
                    ny = nextY;
                }
                
                // 미끄러진 후 최종 위치(nx, ny)
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny, current.moves + 1));
                }
            }
        }
        
        // 목표지점에 도달할 수 없는 경우
        return -1;
    }
}

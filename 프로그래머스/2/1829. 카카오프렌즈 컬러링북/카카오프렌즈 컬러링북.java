import java.util.*;

class Solution {
    
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 색이 0이거나 이미 방문했으면 건너뜀
                if (picture[i][j] == 0 || visited[i][j]) continue;
                
                // 새로운 영역 발견
                numberOfArea++;
                int size = bfs(i, j, picture, visited, m, n);
                
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
            }
        }
        
        return new int[]{numberOfArea, maxSizeOfOneArea};
    }
    
    private int bfs(int x, int y, int[][] picture, boolean[][] visited, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int color = picture[x][y];
        int count = 1;
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int cx = now[0];
            int cy = now[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                // 경계 체크
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                
                // 방문 여부 및 색상 체크
                if (visited[nx][ny]) continue;
                if (picture[nx][ny] != color) continue;
                
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
                count++;
            }
        }
        
        return count;
    }
}
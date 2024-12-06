import java.util.*;

class Solution {
    
    public int solution(int[][] maps) {
        int answer = -1;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        int maxR = maps.length;
        int maxC = maps[0].length;
        boolean[][] visited = new boolean[maxR][maxC];        
        Queue<Point> q = new LinkedList<>();
        
        q.add(new Point(0, 0, 1));
        
        while(!q.isEmpty()) {
            Point now = q.poll();
            
            if (now.isEnd(maxR - 1, maxC -1)) {
                answer = now.depth;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];
                
                if (nextR < 0 || nextC < 0 || nextR >= maxR || nextC >= maxC) continue;
                
                if (maps[nextR][nextC] == 1 && !visited[nextR][nextC]) {
                    q.add(new Point(nextR, nextC, now.depth + 1));
                    visited[nextR][nextC] = true;
                }
            }
            
        }
        
        return answer;
    }
    
    private class Point {
        public int r;
        public int c;
        public int depth;
        
        Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
        
        boolean isEnd(int endR, int endC) {
            if (r == endR && c == endC) return true;
            return false;
        }
    }
    
}
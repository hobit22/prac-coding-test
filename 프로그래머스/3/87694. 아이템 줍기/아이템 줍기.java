import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
        
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        Boolean[][] map = new Boolean[102][102];
        
        characterX = characterX * 2;
        characterY = characterY * 2;
        
        itemX = itemX * 2;
        itemY = itemY * 2;
        
        for(int i = 0; i < rectangle.length; i++) {
            int[] now = rectangle[i];
            
            for(int j = 0; j < 4; j++) now[j] *= 2;
            
            for(int x = now[0]; x <= now[2]; x++) {
                for(int y = now[1]; y <= now[3]; y++) {
                    map[x][y] = (x == now[0] || x == now[2] || y == now[1] || y == now[3]) && map[x][y] != Boolean.FALSE;   
                }
            }            
        }
            
        answer = bfs(map, characterX, characterY, itemX, itemY);
        
        return answer;
    }
        
    public int bfs(Boolean[][] map, int characterX, int characterY, int itemX, int itemY) {
        Queue<int[]> q = new LinkedList<>();
        map[characterX][characterY] = false;
        q.offer(new int[]{characterX, characterY, 0});
        
        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            int nowx = now[0];
            int nowy = now[1];
            int nowCount = now[2];
                        
            if(nowx == itemX && nowy == itemY && min > nowCount) {
                min = nowCount;
                continue;
            }
            
            for(int i = 0; i < 4; i++) {
                int nextx = nowx + dx[i];
                int nexty = nowy + dy[i];
                if(nextx < 2 || nexty < 2 || nextx > 100 || nexty > 100) continue;
                if(map[nextx][nexty] != Boolean.TRUE) continue;
            
                map[nextx][nexty] = false;
                q.offer(new int[]{nextx, nexty, nowCount + 1});
            }
        }
        return min / 2;
    }
}
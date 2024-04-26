import java.util.*;

class Solution {
    
    static String[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        map = new String[maps.length][maps[0].length()];
        int[] start = new int[2];
        int[] labor = new int[2];
        
        for(int i = 0; i<maps.length; i++) {
            String[] tmp = maps[i].split("");
            
            for(int j = 0; j<tmp.length; j++) {
                map[i][j] = tmp[j];
                
                if (map[i][j].equals("S")) {
                    start = new int[]{i, j};
                }
    
                if (map[i][j].equals("L")) {
                    labor = new int[]{i, j};
                }
            }
        }
        
        int result = bfs(start, "L");
        int result2 = bfs(labor, "E");
    
        if (result == -1 || result2 == -1)
            return -1;
        
        return result + result2;
    }
    
    public int bfs(int[] start, String target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 0});
        
        boolean[][] visited = new boolean[map.length][map[0].length];
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            int nowx = now[0];
            int nowy = now[1];
            int count = now[2];
            
            
            visited[nowx][nowy] = true;
            
            if (map[nowx][nowy].equals(target)) {
                return count;
            }
            
            for (int i = 0; i < 4; i++) {
                int nextx = nowx + dx[i];
                int nexty = nowy + dy[i];
                
                boolean check = checkRange(nextx, nexty);

                
                if(check && !visited[nextx][nexty]) {
                    if (!map[nextx][nexty].equals("X")) {
                        visited[nextx][nexty] = true;
                        queue.add(new int[]{nextx, nexty, count+1});
                    }
                }
            }
        }
        
        return -1;
    }
    
    public boolean checkRange(int x, int y) {
        if (x < 0) return false;
        if (y < 0) return false;
        if (x >= map.length) return false;
        if (y >= map[0].length) return false;
        return true;
    }
}
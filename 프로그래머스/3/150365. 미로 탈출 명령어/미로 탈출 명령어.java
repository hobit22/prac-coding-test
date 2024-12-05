import java.util.Arrays;

class Solution {
    private final static char[] dir = {'d', 'l', 'r', 'u'};
    private final static int[] dr = {1, 0, 0, -1};
    private final static int[] dc = {0, -1, 1, 0};
    
    private int maxRow;
    private int maxCol;
    private int goalRow;
    private int goalCol;
    private String route;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        maxRow = n;
        maxCol = m;
        goalRow = r;
        goalCol = c;
        
        StringBuilder sb = new StringBuilder();
        int length = calculateDist(x, y, r, c);
        
        if (length > k || (k - length) % 2 == 1) return "impossible";
        
        dfs(x, y, 0, k, sb);
        
        return route;

    }
    
    private int calculateDist(int x, int y, int r, int c) {
        return Math.abs(x - r) + Math.abs(y - c);
    }
    
    private void dfs(int r, int c, int depth, int k, StringBuilder sb) {
        if (route != null) return;
        if (depth + calculateDist(r, c, goalRow, goalCol) > k) return; 
        if (k == depth) {
            route = sb.toString();
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];
            
            if (nextR > 0 && nextC > 0 && nextR <= maxRow && nextC <= maxCol) {
                sb.append(dir[i]);
                dfs(nextR, nextC, depth + 1, k, sb);
                sb.delete(depth, depth + 1);
            }
        }
        
        

    }
}
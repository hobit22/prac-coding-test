import java.util.*;

class Solution {
    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        
        char[][] grid = new char[n][m];
        // storage -> grid 로 복사
        for (int i = 0; i < n; i++) {
            grid[i] = storage[i].toCharArray();
        }
        
        for (String request : requests) {
            if (request.length() == 1) {
                command1(grid, request.charAt(0));
            } else {
                command2(grid, request.charAt(0));
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '.') {
                    answer++;
                }
            }
        }
        return answer;
    }
    
    void command1(char[][] grid, char target) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();

        // 현재 상태를 복사해서 판단용으로 사용
        char[][] snapshot = new char[n][m];
        for (int i = 0; i < n; i++) {
            snapshot[i] = Arrays.copyOf(grid[i], m);
        }

        // 외곽에서 시작 (상하좌우 테두리)
        for (int i = 0; i < n; i++) {
            if (snapshot[i][0] == '.' || snapshot[i][0] == target)
                queue.offer(new int[]{i, 0});
            if (snapshot[i][m - 1] == '.' || snapshot[i][m - 1] == target)
                queue.offer(new int[]{i, m - 1});
        }
        for (int j = 0; j < m; j++) {
            if (snapshot[0][j] == '.' || snapshot[0][j] == target)
                queue.offer(new int[]{0, j});
            if (snapshot[n - 1][j] == '.' || snapshot[n - 1][j] == target)
                queue.offer(new int[]{n - 1, j});
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            if (x < 0 || x >= n || y < 0 || y >= m) continue;
            if (visited[x][y]) continue;

            visited[x][y] = true;

            if (snapshot[x][y] == target) {
                grid[x][y] = '.'; // 실제 제거
            }

            // '.'인 경우만 더 진행, target인 경우는 현재 위치만 제거하고 멈춤
            if (snapshot[x][y] != '.') continue;

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                    !visited[nx][ny] &&
                    (snapshot[nx][ny] == '.' || snapshot[nx][ny] == target)) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }


    
    void command2(char[][] grid, char target) {
        int n = grid.length;
        int m = grid[0].length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == target) {
                    grid[i][j] = '.';
                }
            }
        }
    }

    void printMap(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }
}
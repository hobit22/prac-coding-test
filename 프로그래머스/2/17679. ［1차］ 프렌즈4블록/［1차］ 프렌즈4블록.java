class Solution {
    public int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        int answer = 0;

        while (true) {
            boolean[][] toRemove = new boolean[m][n];
            int count = 0;

            // 2x2 블록 찾기
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char ch = map[i][j];
                    if (ch == ' ') continue;
                    if (map[i][j + 1] == ch && map[i + 1][j] == ch && map[i + 1][j + 1] == ch) {
                        toRemove[i][j] = toRemove[i][j + 1] = true;
                        toRemove[i + 1][j] = toRemove[i + 1][j + 1] = true;
                    }
                }
            }

            // 지워질 블록 제거
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (toRemove[i][j]) {
                        map[i][j] = ' ';
                        count++;
                    }
                }
            }

            // 지워진 블록이 없다면 종료
            if (count == 0) break;
            answer += count;

            // 블록 떨어뜨리기
            for (int j = 0; j < n; j++) {
                int emptyRow = m - 1; // 밑에서부터 채움
                for (int i = m - 1; i >= 0; i--) {
                    if (map[i][j] != ' ') {
                        char temp = map[i][j];
                        map[i][j] = ' ';
                        map[emptyRow][j] = temp;
                        emptyRow--;
                    }
                }
            }
        }

        return answer;
    }
}
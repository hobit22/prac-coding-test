class Solution
{
    public int solution(int [][]board)
    {
        int rows = board.length;
        int cols = board[0].length;
        
        int max = 0;
        
        int[][] dp = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 0) continue;
                
                
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(
                        Math.min(dp[i-1][j], dp[i][j-1]),
                        dp[i-1][j-1]
                    ) + 1;
                }
                
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}
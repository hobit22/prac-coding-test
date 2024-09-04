class Solution {
    public int solution(int sticker[]) {
        if(sticker.length == 1)
            return sticker[0];

        int[] dp = new int[sticker.length + 2];

        for (int i = 3; i < dp.length; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i - 2]);

        int secondMax = dp[dp.length - 1];

        for (int i = 2; i < dp.length; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sticker[i - 2]);

        int firstMax = dp[dp.length - 2];

        return Math.max(firstMax, secondMax);
    }
}
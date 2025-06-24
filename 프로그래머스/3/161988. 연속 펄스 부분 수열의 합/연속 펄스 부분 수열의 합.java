class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        long max1 = Long.MIN_VALUE;  // 펄스 시작이 1일 때
        long max2 = Long.MIN_VALUE;  // 펄스 시작이 -1일 때

        long sum1 = 0;  // 펄스 시작이 1일 때 누적합
        long sum2 = 0;  // 펄스 시작이 -1일 때 누적합

        for (int i = 0; i < n; i++) {
            int pulse1 = (i % 2 == 0) ? 1 : -1;
            int pulse2 = -pulse1;

            long val1 = sequence[i] * pulse1;
            long val2 = sequence[i] * pulse2;

            sum1 = Math.max(val1, sum1 + val1);
            sum2 = Math.max(val2, sum2 + val2);

            max1 = Math.max(max1, sum1);
            max2 = Math.max(max2, sum2);
        }

        return Math.max(max1, max2);
    }
}
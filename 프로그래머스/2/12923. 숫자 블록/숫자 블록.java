class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin + 1)];

        for (long i = begin; i <= end; i++) {
            answer[(int)(i - begin)] = getBlockNumber(i);
        }

        return answer;
    }

    public int getBlockNumber(long n) {
        if (n == 1) return 0;
        int max = 1;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                long pair = n / i;
                if (pair <= 10_000_000) return (int) pair;
                if (i <= 10_000_000) max = (int) i;
            }
        }
        return max;
    }
}
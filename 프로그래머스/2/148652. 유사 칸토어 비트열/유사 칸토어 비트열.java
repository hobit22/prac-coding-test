class Solution {
    public int solution(int n, long l, long r) {
        int count = 0;
        for (long i = l - 1; i < r; i++) {
            if (isOne(i, n)) count++;
        }
        return count;
    }

    private boolean isOne(long idx, int level) {
        if (level == 0) return true;

        long len = (long) Math.pow(5, level - 1);
        long part = idx / len;

        if (part == 2) return false;
        return isOne(idx % len, level - 1);
    }
}
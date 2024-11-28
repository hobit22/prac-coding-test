class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long start = 0;
        long end = 1_000_000_000_000_000L;
        
        while(end > start) {
            long t = (start + end) / 2;
            if (isValid(t, n, times)) {
                end = t;
            } else {
                start = t + 1;
            }
        }
        
        return start;
    }
    
    public boolean isValid(long t, int n, int[] times) {
        long c = 0;
        for (int time : times) {
            c += t /time;
        }
        return c >= n;
    }
}
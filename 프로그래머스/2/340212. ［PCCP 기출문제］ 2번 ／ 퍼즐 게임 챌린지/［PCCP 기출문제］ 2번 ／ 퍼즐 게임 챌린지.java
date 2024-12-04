class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int start = 1;
        int end = 100_000;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            long time = calculate(mid, diffs, times);
            
            if (time <= limit) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
         
        return start;
    }
    
    public long calculate(int level, int[] diffs, int[] times) {
        long sum = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];

            int time_prev = i == 0 ? 0 : times[i - 1];
            int time_cur = times[i];

            if (diff <= level) {
                sum += time_cur;
            } else {
                sum += (long) (diff - level) * time_cur + (long) (diff - level) * time_prev + time_cur;
            }
        }       
        return sum;
    }
}
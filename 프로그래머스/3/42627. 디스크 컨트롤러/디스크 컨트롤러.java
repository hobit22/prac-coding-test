import java.util.*;

class Solution {
    public int solution(int[][] jobs) {

        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int index = 0;
        int count = 0;
        int total = 0;
        int end = 0;
        while(count < jobs.length) {

            while(index < jobs.length && jobs[index][0] <= end) {
                queue.add(jobs[index++]);
            }

            if(queue.isEmpty()) {
                end = jobs[index][0];
            } else {
                int[] current = queue.poll();
                total += current[1] + end - current[0];
                end += current[1];
                count++;
            }
        }
        return total / jobs.length;
    }
}
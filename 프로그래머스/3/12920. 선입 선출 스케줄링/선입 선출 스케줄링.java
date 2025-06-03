class Solution {
    public int solution(int n, int[] cores) {
        if (n <= cores.length) return n;

        int left = 0;
        int right = 50000 * 10000;

        while (left < right) {
            int mid = (left + right) / 2;
            long jobs = countJobs(mid, cores);

            if (jobs >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long worked = countJobs(left - 1, cores);
        for (int i = 0; i < cores.length; i++) {
            if (left % cores[i] == 0) {
                worked++;
                if (worked == n) return i + 1;
            }
        }

        return -1;
    }

    private long countJobs(int time, int[] cores) {
        long count = cores.length;
        for (int core : cores) {
            count += time / core;
        }
        return count;
    }
}
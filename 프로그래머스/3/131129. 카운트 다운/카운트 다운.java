import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[] dpDart = new int[target + 1];
        int[] dpSingleBull = new int[target + 1];
        Arrays.fill(dpDart, Integer.MAX_VALUE);
        dpDart[0] = 0;
        dpSingleBull[0] = 0;

        List<int[]> scores = new ArrayList<>();
        
        for (int i = 1; i <= 20; i++) {
            scores.add(new int[]{i, 1});
        }
        
        for (int i = 1; i <= 20; i++) {
            scores.add(new int[]{i * 2, 0});
        }
        
        for (int i = 1; i <= 20; i++) {
            scores.add(new int[]{i * 3, 0});
        }
        
        scores.add(new int[]{50, 1});

        for (int i = 1; i <= target; i++) {
            for (int[] score : scores) {
                int s = score[0];
                int singleBull = score[1];
                if (i >= s) {
                    if (dpDart[i - s] + 1 < dpDart[i]) {
                        dpDart[i] = dpDart[i - s] + 1;
                        dpSingleBull[i] = dpSingleBull[i - s] + singleBull;
                    } else if (dpDart[i - s] + 1 == dpDart[i]) {
                        dpSingleBull[i] = Math.max(dpSingleBull[i], dpSingleBull[i - s] + singleBull);
                    }
                }
            }
        }

        return new int[]{dpDart[target], dpSingleBull[target]};
    }
}
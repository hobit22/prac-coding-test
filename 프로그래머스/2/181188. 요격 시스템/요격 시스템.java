import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        // e 기준 오름차순 정렬
        Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));

        int count = 0;
        int lastIntercept = -1;

        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];

            // 마지막 요격 지점이 현재 구간을 커버하지 못하면 새로운 요격 필요
            if (lastIntercept <= start) {
                lastIntercept = end; // 이 미사일의 끝 바로 전에 요격
                count++;
            }
        }

        return count;
    }
}

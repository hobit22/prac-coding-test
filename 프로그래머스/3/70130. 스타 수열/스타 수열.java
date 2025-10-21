import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        if (n < 2) return 0;

        int[] count = new int[n];  // 값의 범위: 0 ~ n-1
        for (int num : a) {
            count[num]++;
        }

        int maxLen = 0;

        for (int v = 0; v < n; v++) {
            if (count[v] * 2 <= maxLen) continue; // 이미 불가능한 경우 skip

            int pairCount = 0;
            int i = 0;

            while (i < n - 1) {
                // 조건: 서로 다르고, 둘 중 하나는 v여야 함
                if ((a[i] == v || a[i + 1] == v) && a[i] != a[i + 1]) {
                    pairCount++;
                    i += 2; // 이 쌍은 사용했으니 다음으로 건너뜀
                } else {
                    i++;
                }
            }

            maxLen = Math.max(maxLen, pairCount * 2);
        }

        return maxLen;
    }
}
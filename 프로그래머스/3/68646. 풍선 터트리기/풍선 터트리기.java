class Solution {
    public int solution(int[] a) {
        int n = a.length;
        if (n <= 2) return n;  // 2개 이하는 항상 생존 가능

        int[] leftMin = new int[n];
        int[] rightMin = new int[n];

        // 왼쪽 최소값 누적
        leftMin[0] = a[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], a[i]);
        }

        // 오른쪽 최소값 누적
        rightMin[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            // 좌우 한쪽이라도 작으면 살 수 있음
            if (i == 0 || i == n - 1 || a[i] <= leftMin[i - 1] || a[i] <= rightMin[i + 1]) {
                count++;
            }
        }

        return count;
    }
}
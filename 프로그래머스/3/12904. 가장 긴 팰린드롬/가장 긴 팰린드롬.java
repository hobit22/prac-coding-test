class Solution {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char c : s.toCharArray()) {
            sb.append(c).append('#');
        }
        String t = sb.toString();
        int n = t.length();

        int[] p = new int[n];
        int center = 0, right = 0;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;

            if (i < right) {
                p[i] = Math.min(right - i, p[mirror]);
            }

            int a = i + p[i] + 1;
            int b = i - p[i] - 1;
            while (a < n && b >= 0 && t.charAt(a) == t.charAt(b)) {
                p[i]++;
                a++;
                b--;
            }

            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }

            maxLen = Math.max(maxLen, p[i]);
        }

        return maxLen;
    }
}
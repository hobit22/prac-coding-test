import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> sumSet = new HashSet<>();
        int n = elements.length;
        
        // 원형 수열을 위해 배열을 두 배로 확장
        int[] extended = new int[n * 2];
        for (int i = 0; i < n * 2; i++) {
            extended[i] = elements[i % n];
        }

        // 부분 수열 길이 1부터 n까지
        for (int len = 1; len <= n; len++) {
            for (int start = 0; start < n; start++) {
                int sum = 0;
                for (int k = 0; k < len; k++) {
                    sum += extended[start + k];
                }
                sumSet.add(sum);
            }
        }

        return sumSet.size();
    }
}
import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        // Step 1: 정렬
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] != b[col - 1]) {
                return Integer.compare(a[col - 1], b[col - 1]); // col 기준 오름차순
            } else {
                return Integer.compare(b[0], a[0]); // 기본키 기준 내림차순
            }
        });
        
        // Step 2 & 3: S_i 계산 및 XOR 누적
        for (int i = row_begin - 1; i < row_end; i++) {
            int sum = 0;
            for (int val : data[i]) {
                sum += val % (i + 1);
            }
            answer ^= sum; // 누적 XOR
        }

        
        return answer;
    }
}
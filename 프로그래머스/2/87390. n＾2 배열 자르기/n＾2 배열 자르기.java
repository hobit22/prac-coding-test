class Solution {
    public int[] solution(int n, long left, long right) {
        int length = (int) (right - left + 1);
        int[] answer = new int[length];
        
        for (long i = left; i <= right; i++) {
            long row = i / n;   // 행 인덱스
            long col = i % n;   // 열 인덱스
            int value = (int)(Math.max(row, col) + 1);
            answer[(int)(i - left)] = value;
        }
        
        return answer;
    }
}
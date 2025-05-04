class Solution {
    public int[] solution(int n, int s) {
        // s가 n보다 작으면 자연수로 분할이 불가능하므로 -1 반환
        if (s < n) {
            return new int[]{-1};
        }

        int base = s / n;       // 기본값
        int remainder = s % n;  // 나머지

        int[] answer = new int[n];
        
        // 일단 모두 base로 채우기
        for (int i = 0; i < n; i++) {
            answer[i] = base;
        }

        // 뒤에서부터 remainder만큼 +1씩 더해주기
        for (int i = n - 1; i >= n - remainder; i--) {
            answer[i]++;
        }

        return answer;
    }
}

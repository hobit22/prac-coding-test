class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            long x = numbers[i];
            
            if (x % 2 == 0) { 
                // 짝수라면 > 맨 뒷 비트 0을 1로 수정
                answer[i] = x + 1;
            } else {
                // 홀수라면 > 처음 등장하는 0을 1로 수정, 다음 나타나는 1을 0으로 수정
                long bitDiff = ((x ^ (x + 1)) >> 2);
                answer[i] = x + 1 + bitDiff;
            }
        }
        
        return answer;
    }
}
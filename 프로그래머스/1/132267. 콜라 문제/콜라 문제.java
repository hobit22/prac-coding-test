class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        int count = 0;
        answer = calculate(a, b, n, count);
        return answer;
    }
    public int calculate(int a, int b, int n, int count) {
        if (n < a) return count;
        
        int change = n / a * b;
        
        n = (n % a) + change;
        
        return calculate(a, b, n, count + change);
    }
}
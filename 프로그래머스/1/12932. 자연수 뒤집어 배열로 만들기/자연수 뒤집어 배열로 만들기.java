class Solution {
    public int[] solution(long n) {
        
        String str = Long.toString(n);
        
        String reversedStr = new StringBuilder(str).reverse().toString();
        
        char[] arr = reversedStr.toCharArray();
        
        int[] answer = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            answer[i] = arr[i] - '0';
        }
        
        return answer;
    }
}
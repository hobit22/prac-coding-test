import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();

        for (char c : number.toCharArray()) {
            // 현재 숫자가 더 크면, 이전 숫자 제거
            while (!stack.isEmpty() && k > 0 && stack.peek() < c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        // 아직 안 지운 숫자 있으면 뒤에서 제거
        while (k > 0) {
            stack.pop();
            k--;
        }

        // 스택을 문자열로 변환
        StringBuilder sb = new StringBuilder();
        for (char c : stack)
            sb.append(c);

        return sb.toString();
    }
}
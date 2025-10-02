import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] result = new String[s.length];
        
        for (int idx = 0; idx < s.length; idx++) {
            String x = s[idx];
            
            // 1. "110" 제거 및 개수 세기
            int count110 = 0;
            StringBuilder stack = new StringBuilder();
            
            for (char c : x.toCharArray()) {
                stack.append(c);
                int len = stack.length();
                if (len >= 3 && 
                    stack.charAt(len - 3) == '1' && 
                    stack.charAt(len - 2) == '1' && 
                    stack.charAt(len - 1) == '0') {
                    count110++;
                    stack.delete(len - 3, len);
                }
            }
            
            // 2. 마지막 0의 위치 찾기
            int insertPos = 0;
            for (int j = 0; j < stack.length(); j++) {
                if (stack.charAt(j) == '0') {
                    insertPos = j + 1;
                }
            }
            
            // 3. 모든 110을 한 번에 삽입
            StringBuilder resultStr = new StringBuilder();
            resultStr.append(stack.substring(0, insertPos));
            // 모든 110을 연속으로 붙임
            for (int i = 0; i < count110; i++) {
                resultStr.append("110");
            }
            resultStr.append(stack.substring(insertPos));
            
            result[idx] = resultStr.toString();
        }
        
        return result;   
    }
}
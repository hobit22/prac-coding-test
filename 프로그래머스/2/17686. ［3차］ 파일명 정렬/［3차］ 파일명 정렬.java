import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        List<FileName> list = new ArrayList<>();
        
        for (int i = 0; i < files.length; i++) {
            list.add(
                new FileName(files[i], i)
            );
        }
        
        list.sort((a, b) -> {
            // 1. HEAD 비교 (대소문자 무시)
            int headCompare = a.head.toLowerCase().compareTo(b.head.toLowerCase());
            if (headCompare != 0) return headCompare;

            // 2. NUMBER 비교 (숫자로 변환)
            int numA = Integer.parseInt(a.number);
            int numB = Integer.parseInt(b.number);
            if (numA != numB) return numA - numB;

            // 3. 입력 순서 유지
            return a.originalIdx - b.originalIdx;
        });
        
        String[] answer = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            answer[i] = list.get(i).originalName;
        }
        return answer;
    }
    
    class FileName {
        String originalName;
        String head;
        String number;
        String tail;
        int originalIdx;
        
        public FileName(String str, int idx) {
            this.originalName = str;
            this.originalIdx = idx;

            int i = 0;
            // HEAD 추출
            while (i < str.length() && !Character.isDigit(str.charAt(i))) i++;
            int headEnd = i;

            // NUMBER 추출
            int numStart = i;
            while (i < str.length() && Character.isDigit(str.charAt(i))) i++;
            int numEnd = i;

            this.head = str.substring(0, headEnd);
            this.number = str.substring(numStart, numEnd);
            this.tail = (numEnd < str.length()) ? str.substring(numEnd) : "";
        }
    }
}
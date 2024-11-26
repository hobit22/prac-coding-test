import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            int startIdx = commands[i][0];
            int endIdx = commands[i][1];
            
            int[] subArr = new int[endIdx - startIdx + 1];
            
            for (int idx = 0; idx < endIdx - startIdx + 1; idx++) {
                // System.out.println(array[idx + startIdx - 1]);
                subArr[idx] = array[idx + startIdx - 1];
            }
            
            Arrays.sort(subArr);
            
            answer[i] = subArr[commands[i][2] - 1];
        }
        return answer;
    }
}
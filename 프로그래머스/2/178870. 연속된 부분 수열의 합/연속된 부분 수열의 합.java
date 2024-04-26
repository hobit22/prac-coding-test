import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        List<int[]> list = new ArrayList<>();
        
        int start = 0;
        int end = 0;
        int sum = 0;
        
        while(start < sequence.length) {
            if(sum > k || end == sequence.length) {
                sum -= sequence[start++];
            } else {
                sum += sequence[end++];
            }
            
            if(sum == k) {
                list.add(new int[]{start, end - 1});
            }
        }
        
        list.sort((o1, o2) -> {
            int size1 = o1[1] - o1[0];
            int size2 = o2[1] - o2[0];
            if(size1 == size2) {
                return o1[0] - o2[0];
            } else {
                return size1 - size2;
            }
        });
        
        answer = list.get(0);
        return answer;
    }
}
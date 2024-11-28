import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        int answer = -1;
        Set<Integer> set = new HashSet<>();
        
        for (int number : numbers) {
            set.add(number);
        }
        
        int sum = 0;
        for (int i = 0; i <= 9; i++) {
            if (set.contains(i)) continue;
            sum += i;
        }
        
        return sum;
    }
}
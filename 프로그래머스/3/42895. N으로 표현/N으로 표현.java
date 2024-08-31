import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<HashSet<Integer>> list = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            list.add(new HashSet<>());
        }
        
        if (N == number) return 1;

        list.get(1).add(N);

        for (int i = 2; i < 9; i++) {
            HashSet<Integer> total = list.get(i);

            for (int j = 1; j < i; j++) {
                HashSet<Integer> setA = list.get(j);
                HashSet<Integer> setB = list.get(i - j);

                for (Integer a : setA) {
                    for (Integer b : setB) {
                        total.add(a + b);
                        total.add(a - b);
                        total.add(a * b);
                        if (a * b != 0) total.add(a / b);
                    }
                }
                
                total.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }
            
            if (total.contains(number)) return i;
        }

        return -1;
    }
}
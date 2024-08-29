import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> map = new HashMap<>();
        
        for (String[] clothe : clothes) {
            String name = clothe[0];
            String category = clothe[1];
            Clothe clothe1 = new Clothe(name, category);
            
            Integer count = map.getOrDefault(category, 0);

            map.put(category, ++count);
        }

        for (Integer value : map.values()) {
            answer = answer * (value + 1);
        }
        
        return answer - 1;
    }
    
    class Clothe {
        String name;
        String category;

        public Clothe(String name, String category) {
            this.name = name;
            this.category = category;
        }
    }
}
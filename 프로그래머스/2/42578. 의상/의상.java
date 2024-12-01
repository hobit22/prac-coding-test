import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String[] clothe : clothes) {
            String item = clothe[0];
            String category = clothe[1];
            
            map.putIfAbsent(category, new ArrayList<String>());
            List<String> itemList = map.get(category);
            itemList.add(item);
            map.put(category, itemList);            
        }
        
        
        for (List<String> itemList : map.values()) {
            answer = answer * (itemList.size() + 1);
        }
        
        return answer - 1;
    }
}
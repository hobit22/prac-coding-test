import java.util .*;

class Solution {
    public int solution(String s1) {
        ArrayList<Character> s = new ArrayList<>();
        for (int k = 0; k < s1.length(); k++) {
            s.add(s1.charAt(k));
        }

        int result = 0;
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        
        for (int i = 0; i < s.size(); i++) {
            Stack<Character> stack = new Stack<>();
            for (int j = 0; j < s.size(); j++) {
                if (stack.isEmpty()) {
                    stack.push(s.get(j));
                } else if (stack.peek() == map.get(s.get(j))) {
                    stack.pop();
                } else {
                    stack.push(s.get(j));
                }
            }
            if (stack.size() == 0) result++;
            char temp = s.get(0);
            s.remove(0);
            s.add(temp);
        }
        return result;
    }
}
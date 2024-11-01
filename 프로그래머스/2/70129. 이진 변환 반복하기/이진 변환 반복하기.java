class Solution {
    public int[] solution(String s) {
        int loop = 0;
        int removedZero = 0;
        
        while (!s.equals("1")) {
            int zeroCnt = calculateZero(s);
            
            removedZero += zeroCnt;
            loop++;
            
            int oneCnt = s.length() - zeroCnt;
            
            s = Integer.toString(oneCnt, 2);
        }
        
        return new int[]{loop, removedZero};
    }
    
    private int calculateZero(String s) {
        int zeroCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeroCnt++;
        }
        
        return zeroCnt;
    }
}
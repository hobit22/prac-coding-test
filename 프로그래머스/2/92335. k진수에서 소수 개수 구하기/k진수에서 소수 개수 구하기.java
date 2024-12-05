import java.util.*;

class Solution {
    public List<Long> primeList = new ArrayList<>();
    public int solution(int n, int k) {
        int answer = 0;
        String temp = "";
        while (n != 0) {
            temp = n % k + temp;
            n = n / k;
        }
        
        String [] tokens = temp.split("0");
        
        for (String token : tokens) { 
            if (token.equals("")) continue;
            // System.out.println(token);
            long target = Long.parseLong(token);
            if (isPrime(target)) answer++;
        }
            
        return answer;
    }
    
    private boolean isPrime(long num) {
        if (num < 2) return false;
        if (primeList.contains(num)) return true;
        
        for(long i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                return false;
            }
        }
        
        primeList.add(num);
        return true;
    }
}
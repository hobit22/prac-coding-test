import java.util.*;

class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        int[] rates = new int[emoticons.length];
        
        List<List<Emoticon>> list = new ArrayList<>();
        
        dfs(0, rates, users, emoticons ,list);
        
        int maxPrice = 0;
        int maxUserCount = 0;
        
        for (List<Emoticon> l : list) {
            int totalPrice = 0;
            int userCount = 0;
            
            for (int[] user : users) {
                int cutRate = user[0];
                int cutPrice = user[1];
                
                int buyPrice = l.stream() 
                    .filter((e) -> e.rate >= cutRate)
                    .mapToInt((e) -> e.discountedPrice)
                    .sum();
                
                if (buyPrice >= cutPrice) {
                    userCount++;
                } else {
                    totalPrice += buyPrice;
                }
            }
            
            if (maxUserCount < userCount) {
                maxUserCount = userCount;
                maxPrice = totalPrice;
            } else if (maxUserCount == userCount) {
                maxPrice = Math.max(maxPrice, totalPrice);
            }
        }
        
        answer[0] = maxUserCount;
        answer[1] = maxPrice;
        return answer;
    }
    
    public void dfs(int start, int[] rates, int[][] users, int[] emoticons, List<List<Emoticon>> list) {
        if (start == rates.length) {
            List<Emoticon> comb = new ArrayList<>();
            for (int i = 0; i < rates.length; i++) {
                comb.add(new Emoticon(rates[i], emoticons[i]));
            }
            
            list.add(comb);
            
            return;
        }
        
        for (int rate = 10; rate <= 40; rate +=10) {
            rates[start] = rate;
            dfs(start + 1, rates, users, emoticons, list);
        }
    }
    
    private class Emoticon {
        public int rate;
        public int price;
        public int discountedPrice;
        
        public Emoticon (int rate, int price) {
            this.rate = rate;
            this.price = price;
            this. discountedPrice = (100 - rate) * price / 100;
        }
        
        @Override
        public String toString(){
            return String.format("%d %d", rate, price);
        }
        
     }
}
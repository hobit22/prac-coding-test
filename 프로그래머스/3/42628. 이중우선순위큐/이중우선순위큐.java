import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Queue<Integer> minQ = new PriorityQueue<>();
        Queue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());

        for (String operation : operations) {
            String[] split = operation.split(" ");
            int num = Integer.parseInt(split[1]);
            switch (split[0]) {
                case "I":
                    maxQ.add(num);
                    minQ.add(num);
                    break;
                case "D":
                    if(maxQ.isEmpty()) break;

                    if (num == 1) {
                        // remove Max value
                        int target = maxQ.poll();
                        minQ.remove(target);
                    } else if (num == -1) {
                        // remove Min value
                        int target = minQ.poll();
                        maxQ.remove(target);
                    }
            }
        }


        if(maxQ.isEmpty())
            return new int[] {0, 0};

        return new int[] {maxQ.peek(), minQ.peek()};
    }
}
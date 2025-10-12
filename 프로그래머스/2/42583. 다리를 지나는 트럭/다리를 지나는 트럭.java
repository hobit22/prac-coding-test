import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>(); // 다리 위의 트럭들
        int time = 0;
        int currentWeight = 0;
        int truckIndex = 0;
        
        // 다리를 빈 공간으로 초기화
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }
        
        while (truckIndex < truck_weights.length) {
            time++;
            
            // 다리 맨 앞의 트럭(또는 빈 공간) 제거
            currentWeight -= bridge.poll();
            
            // 새 트럭을 다리에 올릴 수 있는지 확인
            if (currentWeight + truck_weights[truckIndex] <= weight) {
                bridge.add(truck_weights[truckIndex]);
                currentWeight += truck_weights[truckIndex];
                truckIndex++;
            } else {
                // 올릴 수 없으면 빈 공간 추가
                bridge.add(0);
            }
        }
        
        // 마지막 트럭이 다리를 완전히 건너는 시간 추가
        time += bridge_length;
        
        return time;
    }
}
import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;             // 증설 횟수
        int totalServerCount = 0;   // 증설된 서버 총 수
        int runningServers = 1;     // 현재 시간에 운영 중인 서버 수

        Queue<Server> queue = new LinkedList<>();

        for (int i = 0; i < 24; i++) {
            // 1. 만료된 서버 제거
            while (!queue.isEmpty() && queue.peek().endTime == i) {
                Server expired = queue.poll();
                runningServers -= expired.count;
            }

            // 2. 필요한 서버 수 계산
            int need = (players[i] + m) / m ;

            int added = 0; // 이번 시간에 증설한 서버 수

            if (need > runningServers) {
                added = need - runningServers;
                runningServers += added;
                totalServerCount += added;
                answer += 1;

                queue.offer(new Server(i + k, added));
            }

            // 3. 상태 출력
            System.out.printf("시간: %02d시 ~ %02d시 | 이용자: %d명 | 운영 중인 서버: %d대 | 증설: %d대\n",
                i, i + 1, players[i], runningServers, added);
        }

        System.out.println("==================================");
        System.out.println("최소 증설 횟수: " + answer);
        System.out.println("총 증설된 서버 수: " + totalServerCount);
        return totalServerCount;
    }
    
    class Server {
        int endTime; // 종료 시각
        int count;   // 해당 시각에 종료될 서버 수

        Server(int endTime, int count) {
            this.endTime = endTime;
            this.count = count;
        }
    }
}
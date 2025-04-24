import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        List<Plan> planList = new ArrayList<>();
        
        for (String[] plan : plans) {
            planList.add(new Plan(plan));
        }
        
        planList.sort(Comparator.comparingInt(p -> p.start));
        
        Stack<Plan> paused = new Stack<>();
        int currentTime = planList.get(0).start;

        for (int i = 0; i < planList.size(); i++) {
            Plan current = planList.get(i);
            currentTime = current.start;

            // 다음 과제와의 시간 차이 계산
            int timeAvailable = (i < planList.size() - 1)
                    ? planList.get(i + 1).start - currentTime
                    : Integer.MAX_VALUE;

            if (timeAvailable >= current.playTime) {
                // 현재 과제 완료 가능
                currentTime += current.playTime;
                answer.add(current.name);

                timeAvailable -= current.playTime;

                // 멈춘 과제 이어서 하기
                while (timeAvailable > 0 && !paused.isEmpty()) {
                    Plan pausedPlan = paused.pop();
                    if (timeAvailable >= pausedPlan.playTime) {
                        timeAvailable -= pausedPlan.playTime;
                        currentTime += pausedPlan.playTime;
                        answer.add(pausedPlan.name);
                    } else {
                        // 끝내지 못하면 다시 멈춤
                        pausedPlan.playTime -= timeAvailable;
                        currentTime += timeAvailable;
                        paused.push(pausedPlan);
                        break;
                    }
                }
            } else {
                // 시간 부족해서 멈춰야 함
                current.playTime -= timeAvailable;
                paused.push(current);
                currentTime += timeAvailable;
            }
        }

        // 멈춰둔 과제들 이어서 처리 (최근 것부터)
        while (!paused.isEmpty()) {
            answer.add(paused.pop().name);
        }

        return answer.toArray(new String[0]);
    }
    
    class Plan {
        String name;
        int start;
        int playTime;
        
        Plan(String[] raw) { 
            String[] time = raw[1].split(":");

            this.name = raw[0];
            this.start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            this.playTime = Integer.valueOf(raw[2]);
        }
        
        @Override
        public String toString() {
            return String.format("%s %3d %3d", name, start, playTime);
        }
    }
}
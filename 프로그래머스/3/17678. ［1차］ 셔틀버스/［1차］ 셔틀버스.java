import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        List<Integer> crewTimes = new ArrayList<>();
        for (String time : timetable) {
            String[] parts = time.split(":");
            int totalMinutes = Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
            crewTimes.add(totalMinutes);
        }
        Collections.sort(crewTimes);

        int shuttleTime = 540;
        int crewIndex = 0;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            int passengers = 0;

            while (crewIndex < crewTimes.size() && crewTimes.get(crewIndex) <= shuttleTime && passengers < m) {
                crewIndex++;
                passengers++;
            }

            if (i == n - 1) {
                if (passengers < m) {
                    answer = shuttleTime;
                } else {
                    answer = crewTimes.get(crewIndex - 1) - 1;
                }
            }

            shuttleTime += t;
        }

        int hour = answer / 60;
        int minute = answer % 60;
        return String.format("%02d:%02d", hour, minute);
    }
}
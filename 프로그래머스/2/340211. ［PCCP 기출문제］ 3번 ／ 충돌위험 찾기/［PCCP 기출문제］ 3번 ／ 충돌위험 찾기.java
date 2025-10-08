import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        // 1. 각 로봇의 전체 이동 경로(시간별 좌표) 생성
        List<List<int[]>> robotPaths = new ArrayList<>();
        int maxTime = 0; // 전체 시뮬레이션 시간의 최댓값

        for (int[] route : routes) {
            List<int[]> path = new ArrayList<>();
            for (int i = 0; i < route.length - 1; i++) {
                int[] start = points[route[i] - 1];
                int[] end = points[route[i + 1] - 1];
                int sr = start[0], sc = start[1];
                int er = end[0], ec = end[1];

                // 첫 시작점 포함 (중복 방지 위해 첫 포인트는 첫 i==0일 때만 추가)
                if (i == 0) path.add(new int[]{sr, sc});

                // r좌표 이동 먼저
                while (sr != er) {
                    if (sr < er) sr++;
                    else sr--;
                    path.add(new int[]{sr, sc});
                }
                // 그 다음 c좌표 이동
                while (sc != ec) {
                    if (sc < ec) sc++;
                    else sc--;
                    path.add(new int[]{sr, sc});
                }
            }
            robotPaths.add(path);
            maxTime = Math.max(maxTime, path.size());
        }

        // 2. 시간별 충돌 상황 계산
        int dangerCount = 0;
        for (int t = 0; t < maxTime; t++) {
            Map<String, Integer> map = new HashMap<>();

            for (List<int[]> path : robotPaths) {
                // 로봇이 이미 끝난 경우 패스
                if (t >= path.size()) continue;

                int[] pos = path.get(t);
                String key = pos[0] + "," + pos[1];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            // 같은 좌표에 2대 이상 있으면 위험 발생
            for (int cnt : map.values()) {
                if (cnt >= 2) dangerCount++;
            }
        }

        return dangerCount;
    }
}
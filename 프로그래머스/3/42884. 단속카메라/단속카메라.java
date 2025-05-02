import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        // 1. 진출 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        int camera = Integer.MIN_VALUE; // 현재 카메라의 설치 위치
        int count = 0;

        // 2. 차량 경로를 순회하면서 카메라 설치 여부 판단
        for (int[] route : routes) {
            int entry = route[0];
            int exit = route[1];

            // 현재 카메라가 차량 경로에 포함되지 않으면 새 카메라 설치
            if (camera < entry) {
                camera = exit; // 이 차량의 진출 지점에 카메라 설치
                count++;
            }
        }

        return count;
    }
}
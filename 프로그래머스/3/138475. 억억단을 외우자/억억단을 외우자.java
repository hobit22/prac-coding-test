import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] count = new int[e + 1];

        // 1. 각 수의 약수 개수 구하기 (등장 횟수)
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                count[j]++;
            }
        }

        // 2. 가장 많이 등장한 수 저장용 배열
        int[] maxNum = new int[e + 1];
        int maxIdx = e;
        maxNum[e] = e;

        for (int i = e - 1; i >= 1; i--) {
            if (count[i] >= count[maxIdx]) {
                maxIdx = i;
            }
            maxNum[i] = maxIdx;
        }

        // 3. 쿼리에 대해 바로 maxNum[s]를 사용
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = maxNum[starts[i]];
        }

        return answer;
    }
}
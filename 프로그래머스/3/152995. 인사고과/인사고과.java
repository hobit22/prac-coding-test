import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            people.add(new Person(i, scores[i]));
        }

        Person wanho = people.get(0);
        int wanhoSum = wanho.sum;

        // 정렬: 근무태도 내림차순, 동료평가 오름차순
        people.sort((a, b) -> {
            if (a.score[0] == b.score[0]) return a.score[1] - b.score[1];
            return b.score[0] - a.score[0];
        });

        int maxPeer = 0; // 현재까지의 최고 동료평가 점수
        List<Integer> validSums = new ArrayList<>();

        // 인센티브 여부 판단
        for (Person p : people) {
            int a = p.score[0];
            int b = p.score[1];

            if (b < maxPeer) {
                p.noIncentive();
                // 완호가 탈락한 경우 즉시 -1
                if (p == wanho) return -1;
            } else {
                maxPeer = Math.max(maxPeer, b);
                validSums.add(p.sum);
            }
        }

        // 인센티브 가능자들의 합 점수로 석차 계산
        validSums.sort(Collections.reverseOrder());

        int rank = 1;
        for (int sum : validSums) {
            if (sum > wanhoSum) rank++;
            else break;
        }

        return rank;
    }

    // Person 객체 정의
    private static class Person {
        int pos;          // 사원 번호
        int[] score;      // [근무태도, 동료평가]
        int sum;          // 두 점수의 합
        boolean incentive = true;

        public Person(int pos, int[] score) {
            this.pos = pos;
            this.score = score;
            this.sum = score[0] + score[1];
        }

        public void noIncentive() {
            this.incentive = false;
        }
    }
}
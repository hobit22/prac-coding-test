import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0;              // 가장 가벼운 사람
        int j = people.length - 1; // 가장 무거운 사람
        int boats = 0;

        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++; // 가벼운 사람 태움
            }
            j--; // 무거운 사람은 항상 태움
            boats++;
        }

        return boats;
    }
}
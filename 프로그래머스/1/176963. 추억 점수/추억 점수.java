import java.util.HashMap;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < name.length; i++) {
            String n = name[i];
            int score = yearning[i];
            map.put(n, score);
        }

        for (int i = 0; i < photo.length; i++) {
            String[] p = photo[i];
            int totalScore = 0;

            for (String n : p) {
                Integer score = map.getOrDefault(n, 0);
                totalScore += score;
            }

            answer[i] = totalScore;
        }

        return answer;
    }
}
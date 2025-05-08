import java.util.*;

class Solution {
    // 8명의 친구 이름 (순서 고정)
    static final char[] FRIENDS = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static boolean[] visited = new boolean[8];
    static String[] conditions;
    static int answer = 0;

    public int solution(int n, String[] data) {
        conditions = data;
        answer = 0;
        permute("", 0);
        return answer;
    }

    // 순열을 만들어가며 검사
    private void permute(String current, int depth) {
        if (depth == 8) {
            if (isValid(current)) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permute(current + FRIENDS[i], depth + 1);
                visited[i] = false;
            }
        }
    }

    // 주어진 조건이 현재 배치에 맞는지 검사
    private boolean isValid(String order) {
        for (String cond : conditions) {
            char a = cond.charAt(0);
            char b = cond.charAt(2);
            char op = cond.charAt(3);
            int dist = cond.charAt(4) - '0';

            int posA = order.indexOf(a);
            int posB = order.indexOf(b);
            int actualDist = Math.abs(posA - posB) - 1;

            if (op == '=' && actualDist != dist) return false;
            if (op == '<' && actualDist >= dist) return false;
            if (op == '>' && actualDist <= dist) return false;
        }
        return true;
    }
}
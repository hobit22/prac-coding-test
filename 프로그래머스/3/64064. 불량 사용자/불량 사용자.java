import java.util.*;

class Solution {
    Set<String> uniqueCases = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        dfs(user_id, banned_id, new boolean[user_id.length], 0, new ArrayList<>());
        return uniqueCases.size();
    }

    private void dfs(String[] user_id, String[] banned_id, boolean[] visited, int depth, List<String> current) {
        if (depth == banned_id.length) {
            List<String> temp = new ArrayList<>(current);
            Collections.sort(temp);
            uniqueCases.add(String.join(",", temp));
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (visited[i]) continue;
            if (isMatch(user_id[i], banned_id[depth])) {
                visited[i] = true;
                current.add(user_id[i]);
                dfs(user_id, banned_id, visited, depth + 1, current);
                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }
    }

    private boolean isMatch(String user, String banned) {
        if (user.length() != banned.length()) return false;

        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) == '*') continue;
            if (user.charAt(i) != banned.charAt(i)) return false;
        }

        return true;
    }
}
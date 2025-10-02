import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = (i + 1 < n) ? i + 1 : -1;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int cur = k;

        for (String c : cmd) {
            char op = c.charAt(0);
            if (op == 'U') {
                int x = Integer.parseInt(c.split(" ")[1]);
                for (int i = 0; i < x; i++) cur = prev[cur];
            } else if (op == 'D') {
                int x = Integer.parseInt(c.split(" ")[1]);
                for (int i = 0; i < x; i++) cur = next[cur];
            } else if (op == 'C') {
                stack.push(cur);
                int p = prev[cur];
                int nx = next[cur];

                if (p != -1) next[p] = nx;
                if (nx != -1) prev[nx] = p;

                if (nx != -1) cur = nx;
                else cur = p;
            } else if (op == 'Z') {
                int node = stack.pop();
                int p = prev[node];
                int nx = next[node];

                if (p != -1) next[p] = node;
                if (nx != -1) prev[nx] = node;
            }
        }

        char[] ans = new char[n];
        Arrays.fill(ans, 'O');
        while (!stack.isEmpty()) {
            ans[stack.pop()] = 'X';
        }

        return new String(ans);
    }
}
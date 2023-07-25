import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static int n, m;
    static ArrayList<Integer>[] graph;
    static int[] color;
    static boolean isPossible;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int tc = sc.nextInt();

        while (tc-- > 0) {
            n = sc.nextInt();
            m = sc.nextInt();

            color = new int[n + 1];
            graph = new ArrayList[n+1];
            isPossible = true;

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                graph[start].add(end);
                graph[end].add(start);
            }

            for (int i = 1; i <= n; i++) {
                if(!isPossible) break;

                if (color[i] == 0) {
                    color[i] = 1;
                    setColor(i);
                }
            }
            sb.append(isPossible ? "possible\n" : "impossible\n");
        }
        System.out.println(sb);
    }

    private static void setColor(int position) {
        if (!isPossible) return;

        for (int i = 0; i < graph[position].size(); i++) {
            int next = graph[position].get(i);
            if (color[next] == 0) {
                color[next] = 3 - color[position];
                setColor(next);
            }

            if (color[next] == color[position]) {
                isPossible = false;
                return;
            }
        }
    }
}

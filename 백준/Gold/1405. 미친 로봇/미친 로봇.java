import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static double[] directions = new double[4];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, - 1};
    static boolean[][] visited = new boolean[30][30];
    static double ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            directions[i] = 0.01 * Integer.parseInt(st.nextToken());
        }

        visited[15][15] = true;

        dfs(15, 15, 1.0);

        System.out.println(ans);

    }

    private static void dfs(int x, int y, double p) {
        if (n == 0) {
            ans += p;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (!visited[nextX][nextY] && directions[i] > 0) {
                n--;
                visited[nextX][nextY] = true;
                dfs(nextX, nextY, p * directions[i]);

                n++;
                visited[nextX][nextY] = false;
            }
        }
    }
}

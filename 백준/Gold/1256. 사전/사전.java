import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        dp = new long[n + 1][m + 1];

        makeDP(n, m, k);

        if (getSize(n, m) < k) {
            System.out.println(-1);
        } else {
            System.out.println(sb.toString());
        }

    }

    private static void makeDP(int a, int z, long k) {
        if (a == 0) {
            for (int i = 0; i < z; i++) {
                sb.append("z");
            }
            return;
        }
        if (z == 0) {
            for (int i = 0; i < a; i++) {
                sb.append("a");
            }
            return;
        }

        long size = getSize(a - 1, z);

        if (k > size) {
            sb.append("z");
            makeDP(a, z - 1, k - size);
        } else {
            sb.append("a");
            makeDP(a - 1, z, k);
        }

    }

    private static long getSize(int a, int z) {
        if (a == 0 || z == 0) {
            return 1;
        } else if (dp[a][z] != 0) {
            return dp[a][z];
        } else {
            return dp[a][z] = Long.min(getSize(a - 1, z) + getSize(a, z - 1), 1000000001);
        }
    }
}
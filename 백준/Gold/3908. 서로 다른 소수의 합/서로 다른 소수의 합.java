import java.io.*;
import java.util.*;

public class Main {

    static final int MAX_N = 1120;
    static final int MAX_K = 14;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> primes = getPrimesUpTo(MAX_N);

        int[][] dp = new int[MAX_N + 1][MAX_K + 1];
        dp[0][0] = 1;

        for (int prime : primes) {
            for (int i = MAX_N; i >= prime; i--) {
                for (int j = MAX_K; j >= 1; j--) {
                    dp[i][j] += dp[i - prime][j - 1];
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String[] parts = br.readLine().split(" ");
            int n = Integer.parseInt(parts[0]);
            int k = Integer.parseInt(parts[1]);

            sb.append(dp[n][k]).append('\n');
        }

        System.out.print(sb);
    }

    private static List<Integer> getPrimesUpTo(int limit) {
        boolean[] isNotPrime = new boolean[limit + 1];
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= limit; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
                for (int j = i * 2; j <= limit; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        return primes;
    }
}
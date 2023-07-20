import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long p = Integer.parseInt(st.nextToken());
            long a = Integer.parseInt(st.nextToken());

            if (p == 0) break;

            if (isPrime(p)) {
                sb.append("no\n");
                continue;
            }

            if (pow(a, p, p) == a) {
                sb.append("yes\n");
            } else {
                sb.append("no\n");
            }
        }
        System.out.println(sb);
    }

    static long pow(long x, long n, long mod) {
        long ret = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ret =  ret * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return ret;
    }

    static boolean isPrime(long n) {
        if (n == 2) return true;
        for (long i = 2; i * i <= n ; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

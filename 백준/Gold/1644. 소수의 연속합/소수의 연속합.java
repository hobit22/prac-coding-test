import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        List<Integer> primes = getPrimes(N);

        int count = 0;
        int left = 0, right = 0, sum = 0;

        while (true) {
            if (sum >= N) {
                sum -= primes.get(left++);
            } else if (right == primes.size()) {
                break;
            } else {
                sum += primes.get(right++);
            }

            if (sum == N) {
                count++;
            }
        }

        System.out.println(count);
    }
    
    private static List<Integer> getPrimes(int n) {
        boolean[] isNotPrime = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();

        if (n >= 2) primes.add(2);
        for (int i = 3; i <= n; i += 2) {
            if (!isNotPrime[i]) {
                primes.add(i);
                for (int j = i * 2; j <= n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
        return primes;
    }
}
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;

        for (int end = 0; end < n; end++) {
            sum += arr[end];

            while (sum >= s) {
                minLength = Math.min(minLength, end - start + 1);
                sum -= arr[start++];
            }
        }

        System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 입력 갯수 n
 * n 1~  100000
 * 입력 숫자 -1000 ~ 1000
 * 연속으로 더했을때 가장 큰 수
 * 한 개 빼거나 안뺄수도 있음
 */
public class Main {

    private static int n;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());

        arr = new int[n];
        dp = new int[n][2];

        st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i][0] = Integer.MIN_VALUE;
            dp[i][1] = Integer.MIN_VALUE;
        }

        dp[0][0] = arr[0];
        dp[0][1] = arr[0];

        int answer = arr[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i -1][0] + arr[i], arr[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);

            answer = Math.max(answer, Math.max(dp[i][0], dp[i][1]));
        }

        System.out.println(answer);

    }


}

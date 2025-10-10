import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] a = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        long[] prefixSum = new long[N + 1];
        long[] prefixSum2 = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
            prefixSum2[i] = prefixSum2[i - 1] + (long) a[i] * a[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            long sum = prefixSum[r] - prefixSum[l - 1];
            long sum2 = prefixSum2[r] - prefixSum2[l - 1];
            long fun = (sum * sum - sum2) / 2;

            sb.append(fun).append("\n");
        }

        System.out.print(sb);
    }
}
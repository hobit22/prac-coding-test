import java.io.*;
import java.util.StringTokenizer;

/**
 * 수의 범위 가 큼 : int -> long
 * 시간 초과 : array -> tree
 * sum, update 를 해야함 : segment tree
 */
public class Main {

    static int N, M, K;

    static long[] arr, tree;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        tree = new long[N * 4];

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long dif = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, dif);
            } else if (a == 2) {
                sb.append(sum(1, N, 1, b, (int) c) + "\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    private static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        // 재귀적으로 두 부분으로 나눈 뒤에 그 합을 자기 자신으로 함.
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    public static long sum(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (left > end || right < start) {
            return 0;
        }

        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return tree[node];
        }

        // 그렇지 않다면, 두 부분으로 나누어 합을 구하기
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    public static void update(int start, int end, int node, int idx, long dif) {
        // 범위 밖에 있는 경우
        if (idx < start || idx > end) {
            return;
        }

        // 범위 안에 있으면 내려가며 다른 원소도 갱신
        tree[node] += dif;
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, dif);
        update(mid + 1, end, node * 2 + 1, idx, dif);
    }
}

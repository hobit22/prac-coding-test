import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] numArr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        
        st = new StringTokenizer(bf.readLine());
        
        numArr = new int[n];
        for (int i = 0; i < n; i ++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j< n; j++) {
                if (numArr[i] > numArr[j]) {
                    int tmp = numArr[i];   
                    numArr[i] = numArr[j];
                    numArr[j] = tmp;
                }
            }
        }

        combination(0, 0);

        System.out.println(sb);
    }

    private static void combination(int depth, int start) {
        if (depth == m) {
            for (int val : arr) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < n; i++) {
            arr[depth] = numArr[i];
            combination(depth + 1, i);
        }
    }
}

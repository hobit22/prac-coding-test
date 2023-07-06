import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /**
     * 1. 숫자 순 정렬
     * 2. 길이 순 정렬
     * 3. 접두사 체크
     */
    static int t, n;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {

            n = Integer.parseInt(br.readLine());
            String[] arr = new String[n];

            String ans = "YES";
            for (int j = 0; j < n; j++) {
                arr[j] = br.readLine();
            }

            Arrays.sort(arr);

            for (int j = 0; j < n - 1; j++) {
                if (arr[j + 1].startsWith(arr[j])) {
                    ans = "NO";
                }
            }

            System.out.println(ans);

        }


    }
}

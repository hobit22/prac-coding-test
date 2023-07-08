import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int now = 100;
    static int goal, n;
    static boolean[] brokenBtns;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        goal = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        if (n != 0) {
            st = new StringTokenizer(br.readLine());

            brokenBtns = new boolean[10];

            for (int i = 0; i < n; i++) {
                int n = Integer.parseInt(st.nextToken());
                brokenBtns[n] = true;
            }
        }

        int result = Math.abs(goal - now);

        // i 부터 999999까지 모두 탐색
        for (int i = 0; i <= 999999; i++) {
            String str = String.valueOf(i);
            int length = str.length();

            boolean isBroken = false;

            for (int j = 0; j < length; j++) {
                char targetChar = str.charAt(j);
                if (n != 0 && brokenBtns[targetChar - '0']) {
                    isBroken = true;
                    break;
                }
            }

            // i 에서 goal 까지의 거리 측정
            if (!isBroken) {
                result = Math.min(Math.abs(goal - i) + length, result);
            }
        }

        System.out.println(result);
    }
}

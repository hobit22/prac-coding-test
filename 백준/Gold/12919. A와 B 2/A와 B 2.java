import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int ans = 0;
    static int sCount, tCount;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();


        check(s, t);

        System.out.println(ans);

    }

    private static void check(String s, String t) {
        if (ans == 1) return;
        
        if (s.length() == t.length()) {
            if (s.equals(t)) {
                ans = 1;
            }
            return;
        }

        if (t.charAt(0) == 'B') {
            String subString = t.substring(1);
            StringBuilder sb = new StringBuilder(subString);
            String string = sb.reverse().toString();
            check(s, string);
        }

        if (t.charAt(t.length() - 1) == 'A') {
            check(s, t.substring(0, t.length() - 1));
        }

    }
}

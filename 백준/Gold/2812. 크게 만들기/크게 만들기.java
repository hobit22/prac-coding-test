import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        String number = br.readLine();

        Deque<Character> stack = new LinkedList<>();
        int toRemove = k;

        for (int i = 0; i < n; i++) {
            char current = number.charAt(i);

            while (!stack.isEmpty() && toRemove > 0 && stack.peekLast() < current) {
                stack.pollLast();
                toRemove--;
            }

            stack.addLast(current);
        }

        while (toRemove-- > 0) {
            stack.pollLast();
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        System.out.println(sb.toString());
    }
}
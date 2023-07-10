import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] word;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            word = br.readLine().toCharArray();
            int left = 0;
            int right = word.length - 1;

            Pos pos = new Pos(left, right);

            if (check(pos)) {
                System.out.println(0);
            } else {
                // 달라진 지점 부터 다시 체크
                if (check(new Pos(pos.left + 1, pos.right)) || check(new Pos(pos.left, pos.right - 1))) {
                    System.out.println(1);
                } else {
                    System.out.println(2);
                }
            }
        }
    }

    static boolean check(Pos pos) {
        while (pos.left <= pos.right) {
            if (word[pos.left] == word[pos.right]) {
                pos.left++;
                pos.right--;
            } else {
                return false;
            }
        }
        return true;
    }

    static class Pos {
        int left;
        int right;

        public Pos(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}

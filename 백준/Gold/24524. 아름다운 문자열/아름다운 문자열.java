import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        char[] t = br.readLine().toCharArray();
        HashMap<Character, Integer> mapping = new HashMap<>();
        for (int i = 0; i < t.length; i++) {
            mapping.put(t[i], i);
        }
        int[] cnt = new int[26];
        for (char c : s) {
            if (mapping.containsKey(c)) {
                int idx = mapping.get(c);
                if (idx == 0 || cnt[t[idx] - 'a'] < cnt[t[idx - 1] - 'a']) {
                    cnt[c - 'a']++;
                }
            }
        }
        System.out.println(cnt[t[t.length - 1] - 'a']);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String originStr = br.readLine();
        String boomStr = br.readLine();
        int boomStrLength = boomStr.length();

        // originStr 의 길이가 최대 1_000_000 이기 때문에 메모리 초과
//        while (originStr.contains(boomStr)) {
//            originStr = originStr.replaceAll(boomStr, "");
//        }

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < originStr.length(); i++) {
            st.push(originStr.charAt(i));

            if (st.size() >= boomStrLength) {
                boolean flag  = true;
                for (int j = 0; j < boomStrLength; j++) {
                    if(st.get(st.size()-boomStrLength+j) != boomStr.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int j = 0; j < boomStrLength; j++) {
                        st.pop();
                    }
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (Character character : st) {
            sb.append(character);
        }
        
        String ans = sb.toString();
        
        if (ans.isEmpty()) {
            System.out.println("FRULA");
        } else {
            System.out.println(ans);
        }
    }
}

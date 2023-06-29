import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int h;
    private static int w;
    private static char[][] map;
    private static Queue<int[]> queue;

    private static int[] dy = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dx = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new char[h][w];
        queue = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            String s = bf.readLine();
            for (int j = 0; j < w; j++) {
                char c = s.charAt(j);
                map[i][j] = c;
                if (c == '.') queue.add(new int[]{i, j});
            }
        }

        int answer = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] now = queue.poll();

                for (int j = 0; j < 8; j++) {
                    int nx = now[0] + dx[j];
                    int ny = now[1] + dy[j];

                    if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                        // 물일 경우
                        if(map[nx][ny] != '.'){
                            // 단단함을 1 감소
                            map[nx][ny]--;
                            // 단단함이 0 이면 물 전환
                            if (map[nx][ny] == '0') {
                                map[nx][ny] = '.';
                                queue.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
            answer++;
        }

        System.out.println(answer - 1);

    }

}

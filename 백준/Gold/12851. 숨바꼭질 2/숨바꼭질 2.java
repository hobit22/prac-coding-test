import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int max = 100_000;
    static boolean[] visited;
    static int n;
    static int k;
    static int answer = Integer.MAX_VALUE;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();


        visited = new boolean[max + 1];
        bfs(new Point(n, 0));

        System.out.println(answer);
        System.out.println(count);

    }

    private static void bfs(Point point) {
        Queue<Point> q = new LinkedList<>();
        q.add(point);
        while (!q.isEmpty()) {
            Point now = q.poll();
            int nowPosition = now.pos;
            int nowTime = now.time;

            visited[nowPosition] = true;

            if (nowPosition == k) {

                if (answer == nowTime) {
                    count++;
                } else if (answer > nowTime){
                    answer = nowTime;
                    count = 1;
                }
            }

            if (nowPosition * 2 <= max && !visited[nowPosition * 2]) {
                q.add(new Point(nowPosition * 2, nowTime + 1));
            }
            if (nowPosition - 1 >= 0 && !visited[nowPosition - 1]) {
                q.add(new Point(nowPosition - 1, nowTime + 1));
            }
            if (nowPosition + 1 <= max && !visited[nowPosition + 1]) {
                q.add(new Point(nowPosition + 1, nowTime + 1));
            }
        }
    }

    private static class Point {
        int pos;
        int time;

        Point(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
}

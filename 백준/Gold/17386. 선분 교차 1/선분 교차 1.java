import java.util.Scanner;

public class Main {

    // ccw: 세 점 (x1, y1), (x2, y2), (x3, y3)의 방향을 판별
    static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long value = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (value > 0) return 1;     // 반시계 방향
        else if (value < 0) return -1; // 시계 방향
        else return 0;               // 일직선 (문제에서 없음)
    }

    static int isIntersect(long x1, long y1, long x2, long y2,
                           long x3, long y3, long x4, long y4) {
        int ccw1 = ccw(x1, y1, x2, y2, x3, y3);
        int ccw2 = ccw(x1, y1, x2, y2, x4, y4);
        int ccw3 = ccw(x3, y3, x4, y4, x1, y1);
        int ccw4 = ccw(x3, y3, x4, y4, x2, y2);

        // 두 선분이 서로 다른 방향에서 만나는 경우
        if (ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0) return 1;
        else return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long x1 = sc.nextLong();
        long y1 = sc.nextLong();
        long x2 = sc.nextLong();
        long y2 = sc.nextLong();

        long x3 = sc.nextLong();
        long y3 = sc.nextLong();
        long x4 = sc.nextLong();
        long y4 = sc.nextLong();

        System.out.println(isIntersect(x1, y1, x2, y2, x3, y3, x4, y4));

        sc.close();
    }
}
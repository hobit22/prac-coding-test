import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        // 1. 교점 찾기
        List<Point> points = new ArrayList<>();
        
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                Point intersection = calculateInterSection(line[i][0], line[i][1], line[i][2], line[j][0], line[j][1], line[j][2]);
                if (intersection != null) {
                    points.add(intersection);
                }
            }
        }
        
        // 2. 모든 교점을 포함한 최소한의 크기로 나타내기
        
        // 2-1. 교점의 최솟값, 최댓값 찾기
        Point minimum = getMinimumPoint(points);
        Point maximum = getMaximumPoint(points);
        
        // 2-2. 그리기
        int width = (int) (maximum.x - minimum.x + 1);
        int height = (int) (maximum.y - minimum.y + 1);

        // 2-2-1. .으로 초기화
        char[][] arr = new char[height][width];
        for (char[] row : arr) {
            Arrays.fill(row, '.');
        }

        // 2-2-2. 교점에 * 그리기
        for (Point p : points) {
            int x = (int) (p.x - minimum.x);
            int y = (int) (maximum.y - p.y);
            arr[y][x] = '*';
        }

        String[] result = new String[arr.length];
        
        for (int i = 0; i < result.length; i++) {
            result[i] = new String(arr[i]);
        }
        
        return result;
    }
    
    private static class Point {
        public final long x, y;

        private Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    private Point calculateInterSection(long a1, long b1, long c1, long a2, long b2, long c2) {
        double x = (double) (b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1);
        double y = (double) (a2 * c1 - a1 * c2) / (a1 * b2 - a2 * b1);

        if (x % 1 != 0 || y % 1 != 0) return null;

        return new Point((long) x, (long) y);
    }


    private Point getMinimumPoint(List<Point> points) {
        long x = Long.MAX_VALUE;
        long y = Long.MAX_VALUE;

        for (Point p : points) {
            if (p.x < x) x = p.x;
            if (p.y < y) y = p.y;
        }

        return new Point(x, y);
    }

    private Point getMaximumPoint(List<Point> points) {
        long x = Long.MIN_VALUE;
        long y = Long.MIN_VALUE;

        for (Point p : points) {
            if (p.x > x) x = p.x;
            if (p.y > y) y = p.y;
        }

        return new Point(x, y);
    }
}
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        long x, y;
        long p, q;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
            this.p = 0;
            this.q = 0;
        }

        @Override
        public int compareTo(Point other) {
            if (this.y == other.y) {
                return Long.compare(this.x, other.x);
            }
            return Long.compare(this.y, other.y);
        }
    }

    static int N;
    static Point[] points;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        points = new Point[N];

        for (int i = 0; i < N; i++) {
            long x = sc.nextLong();
            long y = sc.nextLong();
            points[i] = new Point(x, y);
        }

        System.out.println(convexHull());
    }

    static long ccw(Point p1, Point p2, Point p3) {
        return (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y);
    }

    static long det(Point p1, Point p2) {
        return p1.p * p2.q - p1.q * p2.p;
    }

    static int convexHull() {
        Arrays.sort(points);

        for (int i = 1; i < N; i++) {
            points[i].p = points[i].x - points[0].x;
            points[i].q = points[i].y - points[0].y;
        }

        Arrays.sort(points, 1, N, (p1, p2) -> {
            long detValue = det(p1, p2);
            if (detValue > 0) {
                return -1;
            } else if (detValue == 0) {
                long d1 = p1.p * p1.p + p1.q * p1.q;
                long d2 = p2.p * p2.p + p2.q * p2.q;
                return Long.compare(d1, d2);
            } else {
                return 1;
            }
        });

        Stack<Point> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);

        for (int i = 2; i < N; i++) {
            while (stack.size() >= 2) {
                Point mid = stack.pop();
                Point st = stack.peek();
                if (ccw(st, mid, points[i]) > 0) {
                    stack.push(mid);
                    break;
                }
            }
            stack.push(points[i]);
        }

        return stack.size();
    }
}

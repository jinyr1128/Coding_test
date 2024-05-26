import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Point {
        int x, y, index;

        Point(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        while (T-- > 0) {
            int n = scanner.nextInt();
            List<Point> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                points.add(new Point(x, y, i));
            }

            // Find the starting point, which is the point with the smallest y (and smallest x if tie)
            Point start = Collections.min(points, Comparator.comparing((Point p) -> p.y).thenComparing(p -> p.x));
            points.remove(start);

            // Sort the remaining points by polar angle with the start point
            points.sort((a, b) -> {
                int ccw = ccw(start, a, b);
                if (ccw == 0) {
                    return dist(start, a) - dist(start, b);
                }
                return -ccw;
            });

            // Re-add the starting point to the beginning of the list
            points.add(0, start);

            // Prepare result in correct order for valid polygon
            List<Point> hull = new ArrayList<>(points);
            int m = hull.size();
            int k = m - 1;
            while (k > 1 && ccw(hull.get(0), hull.get(k - 1), hull.get(k)) == 0) k--;
            reverse(hull, k, m - 1);

            // Print the result
            for (Point p : hull) {
                System.out.print(p.index + " ");
            }
            System.out.println();
        }

        scanner.close();
    }

    static int ccw(Point a, Point b, Point c) {
        long result = (long) (b.x - a.x) * (c.y - b.y) - (long) (c.x - b.x) * (b.y - a.y);
        return Long.compare(result, 0);
    }

    static int dist(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    static void reverse(List<Point> list, int start, int end) {
        while (start < end) {
            Point temp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, temp);
            start++;
            end--;
        }
    }
}

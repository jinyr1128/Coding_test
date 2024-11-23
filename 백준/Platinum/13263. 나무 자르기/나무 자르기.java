import java.util.*;

class Main {
    static class Line {
        long a, b; // 기울기(a)와 y절편(b)
        double x;  // 이 직선의 시작점 x좌표
        int index;

        Line(long a, long b, double x, int index) {
            this.a = a;
            this.b = b;
            this.x = x;
            this.index = index;
        }

        // 두 직선의 교점의 x좌표를 계산
        double intersect(Line other) {
            return (double) (other.b - this.b) / (this.a - other.a);
        }
    }

    static List<Line> hull = new ArrayList<>();
    static long[] a, b, dp;

    static void addLine(Line newLine) {
        // Convex Hull Trick에서 새로운 직선을 추가
        while (hull.size() > 1) {
            Line last = hull.get(hull.size() - 1);
            Line secondLast = hull.get(hull.size() - 2);
            if (secondLast.intersect(last) > secondLast.intersect(newLine)) {
                hull.remove(hull.size() - 1);
            } else {
                break;
            }
        }

        if (!hull.isEmpty()) {
            newLine.x = hull.get(hull.size() - 1).intersect(newLine);
        }

        hull.add(newLine);
    }

    static long getMinValue(long x) {
        // 이진 탐색으로 x에 대해 최적의 선을 찾음
        int left = 0, right = hull.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (hull.get(mid + 1).x <= x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        Line bestLine = hull.get(left);
        return bestLine.a * x + bestLine.b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = new long[n + 1];
        b = new long[n + 1];
        dp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextLong();
        }
        for (int i = 1; i <= n; i++) {
            b[i] = sc.nextLong();
        }

        // 첫 번째 직선을 추가
        addLine(new Line(b[1], 0, Double.NEGATIVE_INFINITY, 1));

        // dp 계산
        for (int i = 2; i <= n; i++) {
            dp[i] = getMinValue(a[i]); // a[i]에서의 최소 비용 계산
            addLine(new Line(b[i], dp[i], Double.NEGATIVE_INFINITY, i));
        }

        System.out.println(dp[n]);
    }
}

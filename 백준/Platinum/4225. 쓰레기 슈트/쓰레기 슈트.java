import java.util.*;
import java.io.*;

public class Main {
    static class Dot {
        long x, y;

        Dot(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;

        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;
            solve(sc, n, caseNum++);
        }
    }

    static void solve(Scanner sc, int n, int caseNum) {
        List<Dot> dots = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long x = sc.nextLong();
            long y = sc.nextLong();
            dots.add(new Dot(x, y));
        }

        double minDist = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (i == k) continue;

                double maxDist = 0.0;
                double minDistLocal = 0.0;
                Dot cur = dots.get(i);
                Dot next = dots.get(k);

                for (int j = 0; j < n; j++) {
                    if (j == i || j == k) continue;

                    double p1 = (next.x - cur.x) * (cur.y - dots.get(j).y) - (cur.x - dots.get(j).x) * (next.y - cur.y);
                    double p2 = Math.sqrt((next.x - cur.x) * (next.x - cur.x) + (next.y - cur.y) * (next.y - cur.y));
                    double dist = p1 / p2;

                    maxDist = Math.max(maxDist, dist);
                    minDistLocal = Math.min(minDistLocal, dist);
                }

                minDist = Math.min(minDist, maxDist - minDistLocal);
            }
        }

        System.out.printf("Case %d: %.2f\n", caseNum, Math.ceil(minDist * 100) / 100.0);
    }
}

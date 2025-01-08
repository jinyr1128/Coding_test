import java.util.*;

public class Main {
    static class Line {
        long m, b;
        double x;

        Line(long m, long b, double x) {
            this.m = m;
            this.b = b;
            this.x = x;
        }

        long eval(long x) {
            return m * x + b;
        }
    }

    static List<Line> lines = new ArrayList<>();
    static int ptr = 0;

    static double intersect(Line a, Line b) {
        return (double) (b.b - a.b) / (a.m - b.m);
    }

    static void addLine(long m, long b) {
        Line newLine = new Line(m, b, Double.NEGATIVE_INFINITY);
        while (!lines.isEmpty()) {
            Line last = lines.get(lines.size() - 1);
            double x = intersect(last, newLine);
            if (x <= last.x) {
                lines.remove(lines.size() - 1);
            } else {
                break;
            }
        }
        if (!lines.isEmpty()) {
            newLine.x = intersect(lines.get(lines.size() - 1), newLine);
        }
        lines.add(newLine);
        if (ptr >= lines.size()) ptr = lines.size() - 1;
    }

    static long query(long x) {
        while (ptr < lines.size() - 1 && lines.get(ptr + 1).x < x) {
            ptr++;
        }
        return lines.get(ptr).eval(x);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Pair[] lands = new Pair[N];

        for (int i = 0; i < N; i++) {
            long W = sc.nextLong();
            long H = sc.nextLong();
            lands[i] = new Pair(W, H);
        }

        Arrays.sort(lands, Comparator.comparingLong(p -> p.w));

        Pair[] filtered = new Pair[N];
        int idx = 0;
        for (Pair land : lands) {
            while (idx > 0 && filtered[idx - 1].h <= land.h) {
                idx--;
            }
            filtered[idx++] = land;
        }

        long[] dp = new long[idx];
        addLine(filtered[0].h, 0);

        for (int i = 0; i < idx - 1; i++) {
            dp[i] = query(filtered[i].w);
            addLine(filtered[i + 1].h, dp[i]);
        }

        System.out.println(query(filtered[idx - 1].w));
    }

    static class Pair {
        long w, h;

        Pair(long w, long h) {
            this.w = w;
            this.h = h;
        }
    }
}

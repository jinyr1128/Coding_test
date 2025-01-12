import java.util.*;

public class Main {
    static class Pos implements Comparable<Pos> {
        long x, y, size;
        int index;

        Pos(long x, long y, int index, long size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.index = index;
        }

        Pos moveByPivot(Pos p) {
            if (this.y < p.y) {
                return new Pos(p.x - this.x, p.y - this.y, index, size);
            }
            return new Pos(this.x - p.x, this.y - p.y, index, size);
        }

        long ccw(Pos p) {
            return this.x * p.y - this.y * p.x;
        }

        @Override
        public int compareTo(Pos p) {
            long tmp = this.ccw(p);
            if (tmp == 0) {
                return Long.compare(p.size, this.size);
            }
            return Long.compare(tmp, 0);
        }
    }

    static int n;
    static Pos[] pos;

    static long makePivot(Pos pivot) {
        List<Pos> ps = new ArrayList<>();
        for (Pos p : pos) {
            Pos mp = p.moveByPivot(pivot);
            if (mp.y > 0) {
                ps.add(mp);
            }
        }

        Collections.sort(ps);
        boolean[] met = new boolean[n];

        for (Pos p : ps) {
            if (met[p.index]) {
                p.size *= -1;
            } else {
                met[p.index] = true;
            }
        }

        Collections.sort(ps);

        long ans = pivot.size, sum = pivot.size;
        for (Pos p : ps) {
            sum += p.size;
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        pos = new Pos[n * 2];

        for (int i = 0; i < n; i++) {
            long x0 = sc.nextLong();
            long x1 = sc.nextLong();
            long y = sc.nextLong();
            long size = Math.abs(x1 - x0);
            pos[i * 2] = new Pos(x0, y, i, size);
            pos[i * 2 + 1] = new Pos(x1, y, i, size);
        }

        long result = 0;
        for (Pos p : pos) {
            result = Math.max(result, makePivot(p));
        }
        System.out.println(result);
    }
}

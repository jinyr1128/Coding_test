import java.io.*;
import java.util.*;

public class Main {
    static long N, A, B, C;
    static long[] x, dp, prefixSum;
    static int[] hull;
    static double[] intersections;
    static int hullSize, pointer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // Input reading
        N = Long.parseLong(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        x = new long[(int) N + 1];
        prefixSum = new long[(int) N + 1];
        dp = new long[(int) N + 1];
        hull = new int[(int) N + 1];
        intersections = new double[(int) N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            x[i] = Long.parseLong(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + x[i];
        }

        solve();
        System.out.println(dp[(int) N]);
    }

    static void solve() {
        hullSize = 0;
        pointer = 0;

        for (int n = 1; n <= N; n++) {
            dp[n] = evaluateF(prefixSum[n]);
            if (hullSize > 0) {
                while (pointer < hullSize - 1 && intersections[pointer + 1] < prefixSum[n]) {
                    pointer++;
                }
                int i = hull[pointer];
                dp[n] = Math.max(dp[n], dp[i] + evaluateF(prefixSum[n] - prefixSum[i]));
            }
            addLine(n);
        }
    }

    static long evaluateF(long x) {
        return A * x * x + B * x + C;
    }

    static long slopeA(int idx) {
        return -2 * A * prefixSum[idx];
    }

    static long constantB(int idx) {
        return A * prefixSum[idx] * prefixSum[idx] - B * prefixSum[idx] + dp[idx];
    }

    static double intersection(int i, int j) {
        long a1 = slopeA(i), b1 = constantB(i);
        long a2 = slopeA(j), b2 = constantB(j);
        return (double) (b1 - b2) / (a2 - a1);
    }

    static void addLine(int n) {
        while (hullSize > 1 && intersections[hullSize - 1] >= intersection(hull[hullSize - 1], n)) {
            hullSize--;
        }
        hull[hullSize++] = n;
        if (hullSize > 1) {
            intersections[hullSize - 1] = intersection(hull[hullSize - 2], n);
        } else {
            intersections[hullSize - 1] = Double.NEGATIVE_INFINITY;
        }
        if (pointer >= hullSize) {
            pointer = hullSize - 1;
        }
    }
}

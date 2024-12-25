import java.util.*;

public class Main {
    private static final long INF = Long.MAX_VALUE;
    private static long[] arr, pre;
    private static long[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt(); // 감옥 크기
        int G = sc.nextInt(); // 간수 수

        arr = new long[L + 1];
        pre = new long[L + 1];
        dp = new long[G + 1][L + 1];

        // Input values
        for (int i = 1; i <= L; i++) {
            arr[i] = sc.nextLong();
            pre[i] = pre[i - 1] + arr[i];
        }

        // Base case for 1 guard
        for (int i = 1; i <= L; i++) {
            dp[1][i] = pre[i] * i;
        }

        // DP calculation using divide-and-conquer optimization
        for (int g = 2; g <= G; g++) {
            computeDP(g, 1, L, 0, L - 1);
        }

        // Output result
        System.out.println(dp[G][L]);
    }

    // Cost function to calculate range cost
    private static long cost(int i, int j) {
        return (pre[j] - pre[i]) * (j - i);
    }

    // Compute DP using divide-and-conquer optimization
    private static void computeDP(int g, int left, int right, int optL, int optR) {
        if (left > right) return;

        int mid = (left + right) / 2;
        dp[g][mid] = INF;
        int bestK = -1;

        for (int k = optL; k <= Math.min(optR, mid - 1); k++) {
            long currentCost = dp[g - 1][k] + cost(k, mid);
            if (dp[g][mid] > currentCost) {
                dp[g][mid] = currentCost;
                bestK = k;
            }
        }

        computeDP(g, left, mid - 1, optL, bestK);
        computeDP(g, mid + 1, right, bestK, optR);
    }
}

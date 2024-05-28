import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int K = sc.nextInt();
            int[] files = new int[K];
            for (int i = 0; i < K; i++) {
                files[i] = sc.nextInt();
            }

            System.out.println(minimumCostToMergeFiles(files));
        }
    }

    private static int minimumCostToMergeFiles(int[] files) {
        int n = files.length;
        int[][] dp = new int[n][n];
        int[] sum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + files[i];
        }

        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + sum[j + 1] - sum[i];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n - 1];
    }
}

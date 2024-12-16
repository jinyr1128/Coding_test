import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void solve(int n, int[] v) {
        int[][] dp = new int[n + 1][n + 1];
        int[][] K = new int[n + 1][n + 1];
        int[] s = new int[n + 1];

        // Prefix sum 계산
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + v[i];
        }

        // 초기화
        for (int i = 1; i <= n; i++) {
            dp[i - 1][i] = 0;
            K[i - 1][i] = i;
        }

        // DP와 Knuth Optimization
        for (int m = 2; m <= n; m++) {
            for (int i = 0; i + m <= n; i++) {
                int j = i + m;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = K[i][j - 1]; k <= K[i + 1][j]; k++) {
                    int cost = dp[i][k] + dp[k][j] + s[j] - s[i];
                    if (dp[i][j] > cost) {
                        dp[i][j] = cost;
                        K[i][j] = k;
                    }
                }
            }
        }

        System.out.println(dp[0][n]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] v = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                v[i] = Integer.parseInt(st.nextToken());
            }
            solve(n, v);
        }
    }
}

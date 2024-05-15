import java.io.*;
import java.util.*;

public class Main {
    private static int[][] dp;
    private static int[][] enemy;
    private static int n, w;
    private static int INF = 123456789;
    private static final int TOP = 0, BOTTOM = 1, BOTH = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int caseNum = 0; caseNum < t; caseNum++) {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]) * 2;
            w = Integer.parseInt(input[1]);
            enemy = new int[2][n / 2 + 1];

            // enemy 배열 초기화
            for (int i = 0; i < 2; i++) {
                String[] sections = br.readLine().split(" ");
                for (int j = 0; j < sections.length; j++) {
                    enemy[i][j + 1] = Integer.parseInt(sections[j]);
                }
            }

            if (n == 2) {
                int ret = enemy[TOP][1] + enemy[BOTTOM][1] <= w ? 1 : 2;
                System.out.println(ret);
                continue;
            }

            int ret = INF;

            // 연결되지 않은 경우
            dp = new int[3][n / 2 + 1];
            dp[TOP][1] = 1;
            dp[BOTTOM][1] = 1;
            dp[BOTH][1] = enemy[TOP][1] + enemy[BOTTOM][1] <= w ? 1 : 2;
            solve();
            ret = min(ret, dp[BOTH][n / 2]);

            // 위만 연결된 경우
            if (enemy[TOP][1] + enemy[TOP][n / 2] <= w) {
                dp = new int[3][n / 2 + 1];
                dp[TOP][1] = 1;
                dp[BOTTOM][1] = INF;
                dp[BOTH][1] = 2;
                dp[BOTH][0] = INF;
                solve();
                ret = min(ret, dp[BOTTOM][n / 2]);
            }

            // 아래만 연결된 경우
            if (enemy[BOTTOM][1] + enemy[BOTTOM][n / 2] <= w) {
                dp = new int[3][n / 2 + 1];
                dp[BOTTOM][1] = 1;
                dp[TOP][1] = INF;
                dp[BOTH][1] = 2;
                dp[BOTH][0] = INF;
                solve();
                ret = min(ret, dp[TOP][n / 2]);
            }

            // 둘 다 연결된 경우
            if (enemy[TOP][1] + enemy[TOP][n / 2] <= w && enemy[BOTTOM][1] + enemy[BOTTOM][n / 2] <= w) {
                dp = new int[3][n / 2 + 1];
                dp[TOP][1] = dp[BOTTOM][1] = INF;
                dp[BOTH][1] = 2;
                dp[BOTH][0] = INF;
                solve();
                ret = min(ret, dp[BOTH][n / 2 - 1]);
            }

            System.out.println(ret);
        }
    }

    private static void solve() {
        for (int i = 2; i <= n / 2; i++) {
            // top
            dp[TOP][i] = dp[BOTH][i - 1] + 1;
            if (enemy[TOP][i - 1] + enemy[TOP][i] <= w) {
                dp[TOP][i] = min(dp[BOTTOM][i - 1] + 1, dp[TOP][i]);
            }

            // bottom
            dp[BOTTOM][i] = dp[BOTH][i - 1] + 1;
            if (enemy[BOTTOM][i - 1] + enemy[BOTTOM][i] <= w) {
                dp[BOTTOM][i] = min(dp[TOP][i - 1] + 1, dp[BOTTOM][i]);
            }

            // both
            dp[BOTH][i] = min(dp[TOP][i] + 1, dp[BOTTOM][i] + 1);
            if (enemy[TOP][i] + enemy[BOTTOM][i] <= w) {
                dp[BOTH][i] = min(dp[BOTH][i - 1] + 1, dp[BOTH][i]);
            }
            if (enemy[TOP][i - 1] + enemy[TOP][i] <= w && enemy[BOTTOM][i - 1] + enemy[BOTTOM][i] <= w) {
                dp[BOTH][i] = min(dp[BOTH][i - 2] + 2, dp[BOTH][i]);
            }
        }
    }

    private static int min(int a, int b) {
        return a < b ? a : b;
    }
}

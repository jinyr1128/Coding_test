import java.util.Scanner;

public class Main {
    static final int MOD = 1000000007;
    static long[][][] dp = new long[50][301][301];
    static char[] str = new char[51];
    static char[][] board = new char[301][301];
    static int n, m, l;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 체스판의 행 개수
        m = sc.nextInt(); // 체스판의 열 개수
        l = sc.nextInt(); // 사용되는 알파벳 개수
        String word = sc.next(); // 찾으려는 단어
        str = word.toCharArray();

        // 체스판 입력 받기
        for (int i = 0; i < n; i++) {
            board[i] = sc.next().toCharArray();
        }

        // 첫 번째 글자에 대한 초기화
        for (int j = 1; j <= n; ++j) {
            for (int k = 1; k <= m; ++k) {
                dp[0][j][k] = dp[0][j - 1][k] + dp[0][j][k - 1] - dp[0][j - 1][k - 1];
                if (board[j - 1][k - 1] == str[0]) {
                    dp[0][j][k]++;
                }
            }
        }

        // 두 번째 글자부터 단어 끝까지 DP 수행
        for (int i = 1; i < word.length(); ++i) {
            for (int j = 1; j <= n; ++j) {
                for (int k = 1; k <= m; ++k) {
                    if (board[j - 1][k - 1] == str[i]) {
                        dp[i][j][k] = sum(i - 1, 1, 1, n, m);
                        dp[i][j][k] -= sum(i - 1, 1, k - 1, n, k + 1);
                        dp[i][j][k] -= sum(i - 1, j - 1, 1, j + 1, m);
                        dp[i][j][k] -= sum(i - 1, j - 2, k - 2, j + 2, k + 2);
                        dp[i][j][k] += sum(i - 1, j - 2, k - 1, j + 2, k + 1);
                        dp[i][j][k] += sum(i - 1, j - 1, k - 2, j + 1, k + 2);
                    }
                    dp[i][j][k] += dp[i][j - 1][k];
                    dp[i][j][k] += dp[i][j][k - 1];
                    dp[i][j][k] -= dp[i][j - 1][k - 1];
                    dp[i][j][k] = (dp[i][j][k] + 2L * MOD) % MOD;
                }
            }
        }

        System.out.println(dp[word.length() - 1][n][m]);
        sc.close();
    }

    static int sum(int ind, int a, int b, int c, int d) {
        if (a < 1) a = 1;
        if (b < 1) b = 1;
        if (c > n) c = n;
        if (d > m) d = m;
        long ret = 0;
        ret += dp[ind][c][d];
        ret -= dp[ind][a - 1][d];
        ret -= dp[ind][c][b - 1];
        ret += dp[ind][a - 1][b - 1];
        ret = (ret + 2L * MOD) % MOD;
        return (int) ret;
    }
}

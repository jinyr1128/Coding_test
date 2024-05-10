import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][][] dp;
    static int[][] grid;
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1][3]; // 0: horizontal, 1: vertical, 2: diagonal

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][2][0] = 1; // Start position with horizontal pipe

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                // Skip the wall and the start position
                if (grid[x][y] == 1 || (x == 1 && y == 1)) continue;

                // Horizontal
                if (y > 1 && grid[x][y - 1] == 0) {
                    dp[x][y][0] += dp[x][y - 1][0] + dp[x][y - 1][2];
                }
                // Vertical
                if (x > 1 && grid[x - 1][y] == 0) {
                    dp[x][y][1] += dp[x - 1][y][1] + dp[x - 1][y][2];
                }
                // Diagonal
                if (x > 1 && y > 1 && grid[x - 1][y] == 0 && grid[x][y - 1] == 0 && grid[x - 1][y - 1] == 0) {
                    dp[x][y][2] += dp[x - 1][y - 1][0] + dp[x - 1][y - 1][1] + dp[x - 1][y - 1][2];
                }
            }
        }

        int result = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
        System.out.println(result);
    }
}

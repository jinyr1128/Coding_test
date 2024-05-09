import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[n][n];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기화: 첫 번째 행
        dp[0][0] = triangle[0][0];

        // dp를 채워 나가기
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                // 왼쪽 대각선에서 내려오는 경우
                int left = j > 0 ? dp[i-1][j-1] : 0;
                // 바로 위에서 내려오는 경우
                int up = dp[i-1][j];
                dp[i][j] = Math.max(left, up) + triangle[i][j];
            }
        }

        // 마지막 행에서 최댓값 찾기
        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, dp[n-1][i]);
        }

        System.out.println(maxSum);
    }
}

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[][] cost = new int[N+1][3];
        int[][] dp = new int[N+1][3];

        for (int i = 1; i <= N; i++) {
            String[] line = br.readLine().split(" ");
            cost[i][0] = Integer.parseInt(line[0]);
            cost[i][1] = Integer.parseInt(line[1]);
            cost[i][2] = Integer.parseInt(line[2]);
        }

        dp[1][0] = cost[1][0];
        dp[1][1] = cost[1][1];
        dp[1][2] = cost[1][2];

        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
        }

        int result = Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]);
        System.out.println(result);
    }
}

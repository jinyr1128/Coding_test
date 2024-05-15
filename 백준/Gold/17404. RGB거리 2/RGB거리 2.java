import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3];
        int[][] dp = new int[N][3];
        
        // 비용 입력 받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }
        
        int result = Integer.MAX_VALUE;

        // 첫 집을 각각 빨강, 초록, 파랑으로 칠하는 경우에 대해 각각 최솟값을 계산
        for (int firstColor = 0; firstColor < 3; firstColor++) {
            // dp 배열 초기화
            for (int j = 0; j < 3; j++) {
                if (j == firstColor) {
                    dp[0][j] = cost[0][j];
                } else {
                    dp[0][j] = 1001; // 최대 비용보다 큰 값으로 초기화
                }
            }
            
            // DP 테이블 채우기
            for (int i = 1; i < N; i++) {
                dp[i][0] = cost[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
                dp[i][1] = cost[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
                dp[i][2] = cost[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
            }
            
            // 첫 집의 색깔과 마지막 집의 색깔이 달라야 하므로 이를 고려하여 최솟값 계산
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (firstColor != lastColor) {
                    result = Math.min(result, dp[N-1][lastColor]);
                }
            }
        }
        
        System.out.println(result);
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 목표 고객 수 C, 도시의 개수 N
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 각 도시의 홍보 정보를 저장할 배열 (비용, 고객 수)
        int[][] promotions = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            promotions[i][0] = Integer.parseInt(st.nextToken()); // 비용
            promotions[i][1] = Integer.parseInt(st.nextToken()); // 고객 수
        }

        // DP 배열 정의: dp[i] = i명의 고객을 얻기 위한 최소 비용
        // 최대 고객 수를 100으로 가정하여 C + 100까지 계산
        int[] dp = new int[C + 101];
        
        // DP 배열을 큰 값으로 초기화 (최소값을 찾기 위함)
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        // 0명의 고객을 얻는 비용은 0
        dp[0] = 0;

        // DP 테이블 채우기
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < N; j++) {
                int cost = promotions[j][0];
                int customers = promotions[j][1];

                // 현재 고객 수(i)가 해당 프로모션의 고객 수보다 크거나 같을 때만 갱신 가능
                if (i >= customers) {
                    // (i - customers) 명을 모으는 게 가능했다면
                    if (dp[i - customers] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[i - customers] + cost);
                    }
                }
            }
        }
        
        // C명 이상의 고객을 유치하는 경우 중 최소 비용 찾기
        int minCost = Integer.MAX_VALUE;
        for (int i = C; i < dp.length; i++) {
            minCost = Math.min(minCost, dp[i]);
        }

        System.out.println(minCost);
    }
}
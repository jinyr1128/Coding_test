import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] cost;
    static int[][] dp;
    static final int INF = 1_000_000_000;  // 충분히 큰 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[1 << N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기화
        for (int i = 0; i < (1 << N); i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[1][0] = 0;  // 시작 도시 1번을 0번 인덱스로 가정

        // DP 실행
        for (int mask = 1; mask < (1 << N); mask++) {
            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) == 0) continue;  // i 도시가 포함되지 않은 경우 스킵
                for (int j = 0; j < N; j++) {
                    if ((mask & (1 << j)) != 0 || cost[i][j] == 0) continue;  // j 도시가 이미 포함되었거나 갈 수 없는 경우 스킵
                    int nextMask = mask | (1 << j);
                    dp[nextMask][j] = Math.min(dp[nextMask][j], dp[mask][i] + cost[i][j]);
                }
            }
        }

        // 최소 비용 계산
        int answer = INF;
        for (int i = 1; i < N; i++) {
            if (cost[i][0] != 0) {
                answer = Math.min(answer, dp[(1 << N) - 1][i] + cost[i][0]);
            }
        }

        System.out.println(answer);
    }
}

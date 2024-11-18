import java.util.Scanner;

public class Main {
    static final int MOD = 9901;
    static int N, M;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        N = sc.nextInt();
        M = sc.nextInt();

        int maxState = 1 << M; // M 열에 대한 상태의 최대값
        dp = new int[N * M + 1][maxState];

        // DP 배열 초기화
        for (int i = 0; i <= N * M; i++) {
            for (int j = 0; j < maxState; j++) {
                dp[i][j] = -1;
            }
        }

        // 결과 출력
        System.out.println(solve(0, 0));
    }

    // DP 함수: 현재 인덱스와 상태에서 가능한 방법의 수를 반환
    static int solve(int idx, int status) {
        // 격자판이 끝까지 채워졌는지 확인
        if (idx == N * M) {
            return status == 0 ? 1 : 0;
        }

        // 이미 계산된 값이 있는 경우
        if (dp[idx][status] != -1) {
            return dp[idx][status];
        }

        // 현재 위치와 상태 계산
        int result = 0;

        // 이미 채워져 있는 경우
        if ((status & 1) != 0) {
            result = solve(idx + 1, status >> 1);
        } else {
            // 세로로 채우기
            if (idx + M < N * M) {
                result += solve(idx + 1, (status >> 1) | (1 << (M - 1)));
                result %= MOD;
            }

            // 가로로 채우기
            if (idx % M != M - 1 && (status & 2) == 0) {
                result += solve(idx + 2, status >> 2);
                result %= MOD;
            }
        }

        // 결과 저장
        return dp[idx][status] = result;
    }
}

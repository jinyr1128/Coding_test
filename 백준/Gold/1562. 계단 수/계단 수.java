import java.io.*;

public class Main {
    static final int MOD = 1_000_000_000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        
        if (N == 1) {
            System.out.println(0);
            return;
        }

        long[][][] dp = new long[N + 1][10][1 << 10];
        
        // 초기화: 길이가 1인 계단 수 (0으로 시작할 수 없음)
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        // DP 수행
        for (int length = 1; length < N; length++) {
            for (int digit = 0; digit <= 9; digit++) {
                for (int mask = 0; mask < (1 << 10); mask++) {
                    if (dp[length][digit][mask] == 0) continue;
                    
                    // 다음 자리수를 구성
                    int[] nextDigits = {digit - 1, digit + 1};
                    for (int nextDigit : nextDigits) {
                        if (nextDigit >= 0 && nextDigit <= 9) {
                            int newMask = mask | (1 << nextDigit);
                            dp[length + 1][nextDigit][newMask] += dp[length][digit][mask];
                            dp[length + 1][nextDigit][newMask] %= MOD;
                        }
                    }
                }
            }
        }

        // 모든 숫자를 포함하는 경우를 합산
        long result = 0;
        int fullMask = (1 << 10) - 1; // 모든 숫자를 사용한 상태
        for (int digit = 0; digit <= 9; digit++) {
            result += dp[N][digit][fullMask];
            result %= MOD;
        }

        System.out.println(result);
    }
}

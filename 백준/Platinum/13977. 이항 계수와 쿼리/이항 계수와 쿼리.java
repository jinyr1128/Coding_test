import java.util.Scanner;

public class Main {

    static final long MOD = 1_000_000_007;
    static final int MAX_LEN = 4_000_001;
    static long[] factorial = new long[MAX_LEN];

    // 모듈러 역원을 구하기 위한 거듭제곱 함수
    static long power(long base, long exp) {
        if (exp == 1) return base % MOD;
        long halfPower = power(base, exp / 2);
        halfPower = (halfPower * halfPower) % MOD;
        return (exp % 2 == 0) ? halfPower : (halfPower * base) % MOD;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 팩토리얼 배열 초기화
        factorial[0] = 1;
        for (int i = 1; i < MAX_LEN; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        int M = sc.nextInt();
        StringBuilder result = new StringBuilder();

        // 각 테스트 케이스에 대해 이항 계수를 계산
        for (int i = 0; i < M; i++) {
            int N = sc.nextInt();
            int K = sc.nextInt();

            long numerator = factorial[N];
            long denominator = (factorial[K] * factorial[N - K]) % MOD;
            long denominatorInverse = power(denominator, MOD - 2);

            result.append(numerator * denominatorInverse % MOD).append("\n");
        }

        System.out.print(result);
        sc.close();
    }
}

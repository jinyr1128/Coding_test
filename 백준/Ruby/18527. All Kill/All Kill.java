import java.util.Scanner;

public class Main {
    static final long MOD = 998244353;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 문제의 입력 값 받기
        int n = sc.nextInt();     // 문제의 개수
        long t = sc.nextLong();    // 경연의 시간
        int[] codingTimes = new int[n];

        for (int i = 0; i < n; i++) {
            codingTimes[i] = sc.nextInt();
        }

        // s 및 d 계산
        long s = t + n + 1;
        for (int codingTime : codingTimes) {
            s -= codingTime;
        }
        long d = s;

        // p 초기화 및 계산
        long p = 1;
        for (int codingTime : codingTimes) {
            p = (p * s) % MOD;
            s += codingTime - 1;
        }

        // 최종 p 계산 후 출력
        p = (p * (d - n) % MOD * modPow(d, MOD - 2)) % MOD;
        System.out.println(p);
    }

    // 모듈러 거듭제곱 함수
    public static long modPow(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }
}

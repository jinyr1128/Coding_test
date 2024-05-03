import java.util.Scanner;

public class Main {
    static long[] dp = new long[101]; // N의 최대 값은 100이므로, 인덱스를 1부터 사용하기 위해 101 크기 할당

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 테스트 케이스 개수

        // 파도반 수열 초기화 및 계산
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        if (dp.length > 3) {
            dp[4] = 2;
            dp[5] = 2;
        }
        
        for (int i = 6; i <= 100; i++) {
            dp[i] = dp[i-1] + dp[i-5];
        }

        // 각 테스트 케이스에 대한 결과 출력
        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            System.out.println(dp[N]);
        }
        
        scanner.close();
    }
}
